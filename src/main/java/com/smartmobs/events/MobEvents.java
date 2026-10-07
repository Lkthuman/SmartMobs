package com.smartmobs.events;

import com.smartmobs.adaptation.MobProfiles;
import com.smartmobs.adaptation.Strategy;
import com.smartmobs.ai.AdaptiveGoal;
import com.smartmobs.config.SmartMobsConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

public class MobEvents {
    @SubscribeEvent
    public void onJoin(EntityJoinLevelEvent e) {
        if (e.getLevel().isClientSide() || !SmartMobsConfig.enabled()) return;
        if (!(e.getEntity() instanceof PathfinderMob mob)) return;
        String id = BuiltInRegistries.ENTITY_TYPE.getKey(mob.getType()).toString();
        if (!SmartMobsConfig.affectedMobs().contains(id)) return;
        for (Strategy s : MobProfiles.strategiesFor((Mob) mob)) {
            boolean already = mob.goalSelector.getAvailableGoals().stream()
                    .anyMatch(w -> w.getGoal() instanceof AdaptiveGoal g && g.strategy() == s);
            if (!already) mob.goalSelector.addGoal(3, new AdaptiveGoal(mob, s));
        }
    }
}
