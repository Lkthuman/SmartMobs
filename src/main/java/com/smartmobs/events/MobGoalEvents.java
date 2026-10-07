package com.smartmobs.events;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import com.smartmobs.SmartMobs;
import com.smartmobs.ai.AdaptiveGoal;
import com.smartmobs.adaptation.MobProfiles;
import com.smartmobs.config.SmartMobsConfig;

@EventBusSubscriber(modid = SmartMobs.MODID, bus = EventBusSubscriber.Bus.GAME)
public class MobGoalEvents {
    
    @SubscribeEvent
    public static void onMobJoinLevel(EntityJoinLevelEvent event) {
        if (!SmartMobsConfig.COMMON.enabled.get()) {
            return;
        }
        
        if (!(event.getEntity() instanceof Monster mob)) {
            return;
        }
        
        // Vérifier si ce mob est supporté
        if (!isSupportedMob(mob)) {
            return;
        }
        
        // Ajouter le goal d'adaptation avec une priorité moyenne
        GoalSelector goalSelector = mob.goalSelector;
        AdaptiveGoal adaptiveGoal = new AdaptiveGoal(mob);
        
        // Priorité 4 : juste avant le goal d'attaque vanilla (priorité 5)
        goalSelector.addGoal(4, adaptiveGoal);
    }
    
    private static boolean isSupportedMob(Monster mob) {
        return mob instanceof Zombie ||
               mob instanceof Skeleton ||
               mob instanceof Creeper ||
               mob instanceof Spider ||
               mob instanceof Enderman ||
               mob instanceof Drowned ||
               mob instanceof Husk ||
               mob instanceof Stray ||
               mob instanceof Pillager ||
               mob instanceof Vindicator ||
               mob instanceof Witch;
    }
}
