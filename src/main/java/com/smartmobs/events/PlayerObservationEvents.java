package com.smartmobs.events;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import com.smartmobs.SmartMobs;
import com.smartmobs.memory.ModAttachments;
import com.smartmobs.memory.PlayerBehavior;

@EventBusSubscriber(modid = SmartMobs.MODID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerObservationEvents {
    
    @SubscribeEvent
    public static void onPlayerDamageEntity(LivingDamageEvent.Post event) {
        Entity source = event.getSource().getEntity();
        if (!(source instanceof Player player)) {
            return;
        }
        
        try {
            var behavior = player.getData(ModAttachments.PLAYER_BEHAVIOR);
            if (behavior == null) {
                behavior = new PlayerBehavior();
            }
            
            // Détecter le type d'arme utilisée
            var item = player.getMainHandItem();
            
            if (item.getItem().getClass().getSimpleName().contains("SwordItem")) {
                behavior.increaseMeleeUsage();
            } else if (item.getItem().getClass().getSimpleName().contains("BowItem")) {
                behavior.increaseRangedUsage();
            } else if (item.getItem().getClass().getSimpleName().contains("TridentItem")) {
                behavior.increaseProjectileUsage();
            } else if (item.getItem().getClass().getSimpleName().contains("AxeItem")) {
                behavior.increaseMeleeUsage();
            }
            
            // Détecter la hauteur relative
            if (player.getY() > event.getEntity().getY() + 2.0) {
                behavior.increaseHighGroundUsage();
            }
            
            player.setData(ModAttachments.PLAYER_BEHAVIOR, behavior);
        } catch (Exception e) {
            // Silently fail
        }
    }
    
    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.Place event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        
        try {
            var behavior = player.getData(ModAttachments.PLAYER_BEHAVIOR);
            if (behavior == null) {
                behavior = new PlayerBehavior();
            }
            
            behavior.increaseBuildingUsage();
            player.setData(ModAttachments.PLAYER_BEHAVIOR, behavior);
        } catch (Exception e) {
            // Silently fail
        }
    }
    
    @SubscribeEvent
    public static void onPlayerTick(PlayerEvent.PlayerTickEvent event) {
        if (event.getPhase() != TickEvent.Phase.END) {
            return;
        }
        
        Player player = event.getEntity();
        try {
            var behavior = player.getData(ModAttachments.PLAYER_BEHAVIOR);
            if (behavior == null) {
                behavior = new PlayerBehavior();
            }
            
            // Decay progressif du comportement
            if (player.tickCount % 100 == 0) {
                behavior.decay(1.0f);
            }
            
            player.setData(ModAttachments.PLAYER_BEHAVIOR, behavior);
        } catch (Exception e) {
            // Silently fail
        }
    }
}
