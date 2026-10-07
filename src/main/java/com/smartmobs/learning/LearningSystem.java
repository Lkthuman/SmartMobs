package com.smartmobs.learning;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;

public class LearningSystem {
    private static final Map<Player, PlayerBehaviorData> playerBehaviors = new HashMap<>();

    public static PlayerBehaviorData getPlayerBehavior(Player player) {
        return playerBehaviors.computeIfAbsent(player, p -> new PlayerBehaviorData());
    }

    public static void recordPlayerAction(Player player, PlayerAction action) {
        PlayerBehaviorData data = getPlayerBehavior(player);
        data.recordAction(action);
    }

    public static void updateMobMemory(LivingEntity mob, Player player) {
        // Update mob's understanding of player behavior
    }

    public static void cleanup(Player player) {
        playerBehaviors.remove(player);
    }
}
