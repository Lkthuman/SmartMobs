package com.smartmobs.events;

import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;

import com.smartmobs.SmartMobs;

public class MobEventHandler {

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent.Post event) {
        ServerLevel level = event.getServer().overworld();
        
        // Update all mobs
        for (Mob mob : level.getEntitiesOfClass(Mob.class, null)) {
            Player target = mob.getTarget();
            if (target != null) {
                SmartMobs.getAdaptationManager().updateAdaptation(mob, target);
            }
        }
        
        // Update learning system
        SmartMobs.getLearningSystem().update();
    }
}