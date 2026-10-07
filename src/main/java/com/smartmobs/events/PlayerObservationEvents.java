package com.smartmobs.events;

import com.smartmobs.config.SmartMobsConfig;
import com.smartmobs.memory.ModAttachments;
import com.smartmobs.memory.PlayerBehavior;
import com.smartmobs.memory.PlayerBehavior.Stat;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

/** Records player behavior. Only counts actions a nearby mob could plausibly have witnessed. */
public class PlayerObservationEvents {
    private static final float STEP = 0.02f;

    private static float step() { return (float) (STEP * SmartMobsConfig.learningSpeed()); }

    private static boolean witnessed(ServerPlayer p) {
        double r = SmartMobsConfig.observationRange();
        AABB box = p.getBoundingBox().inflate(r);
        return !p.level().getEntitiesOfClass(Mob.class, box, m -> m.isAlive() && m.hasLineOfSight(p)).isEmpty();
    }

    @SubscribeEvent
    public void onDamage(LivingIncomingDamageEvent e) {
        if (!SmartMobsConfig.enabled()) return;
        DamageSource src = e.getSource();
        if (!(e.getEntity() instanceof Mob)) return;
        ServerPlayer player = null;
        boolean ranged = false;
        if (src.getEntity() instanceof ServerPlayer p) {
            player = p;
            ranged = src.getDirectEntity() instanceof Projectile;
        }
        if (player == null || !witnessed(player)) return;
        PlayerBehavior b = player.getData(ModAttachments.BEHAVIOR.get());
        if (ranged) {
            b.observe(Stat.RANGED, step());
        } else {
            ItemStack held = player.getMainHandItem();
            if (held.getItem() instanceof SwordItem || held.getItem() instanceof AxeItem) b.observe(Stat.MELEE, step());
        }
        if (player.getY() - e.getEntity().getY() >= 3.0) b.observe(Stat.HIGH_GROUND, step());
        player.setData(ModAttachments.BEHAVIOR.get(), b);
    }

    @SubscribeEvent
    public void onKill(LivingDeathEvent e) {
        if (!SmartMobsConfig.enabled() || !(e.getEntity() instanceof Mob mob)) return;
        if (e.getSource().getEntity() instanceof ServerPlayer p && mob.getLastHurtByMobTimestamp() >= 0) {
            if (p.getHealth() >= p.getMaxHealth() * 0.8f) {
                PlayerBehavior b = p.getData(ModAttachments.BEHAVIOR.get());
                b.observe(Stat.EASY_KILLS, step());
                p.setData(ModAttachments.BEHAVIOR.get(), b);
            }
        }
    }

    @SubscribeEvent
    public void onPlace(BlockEvent.EntityPlaceEvent e) {
        if (!SmartMobsConfig.enabled() || !(e.getEntity() instanceof ServerPlayer p)) return;
        if (p.tickCount % 5 != 0 && p.getRandom().nextInt(3) != 0) return;
        PlayerBehavior b = p.getData(ModAttachments.BEHAVIOR.get());
        b.observe(Stat.BUILDING, step() * 0.5f);
        p.setData(ModAttachments.BEHAVIOR.get(), b);
    }

    /** Decay once per minute. */
    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent.Post e) {
        if (!(e.getEntity() instanceof ServerPlayer p) || p.tickCount % 1200 != 0) return;
        float factor = 1f - (float) (0.01 * SmartMobsConfig.forgettingSpeed());
        PlayerBehavior b = p.getData(ModAttachments.BEHAVIOR.get());
        b.decay(Math.max(0f, factor));
        p.setData(ModAttachments.BEHAVIOR.get(), b);
    }
}
