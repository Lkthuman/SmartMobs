package com.smartmobs.events;

import net.neoforged.neoforge.event.entity.player.ProjectileEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.minecraft.world.entity.player.Player;

import com.smartmobs.SmartMobs;
import com.smartmobs.learning.LearningSystem.PlayerAction;

public class PlayerEventHandler {

    @SubscribeEvent
    public static void onProjectileFired(ProjectileEvent.CriticalHit event) {
        if (event.getProjectile() != null && event.getProjectile().getOwner() instanceof Player player) {
            SmartMobs.getLearningSystem().recordPlayerAction(player, new PlayerAction("ranged_attack"));
        }
    }

    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        if (event.getEntity() instanceof Player player) {
            SmartMobs.getLearningSystem().recordPlayerAction(player, new PlayerAction("melee_attack"));
        }
    }
}