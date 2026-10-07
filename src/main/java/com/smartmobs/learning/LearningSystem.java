package com.smartmobs.learning;

import java.util.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Mob;

public class LearningSystem {
    private Map<UUID, PlayerBehaviorProfile> playerProfiles = new HashMap<>();

    public PlayerBehaviorProfile getOrCreateProfile(Player player) {
        return playerProfiles.computeIfAbsent(player.getUUID(), uuid -> new PlayerBehaviorProfile(uuid));
    }

    public void recordPlayerAction(Player player, PlayerAction action) {
        PlayerBehaviorProfile profile = getOrCreateProfile(player);
        profile.recordAction(action);
    }

    public void update() {
        // Periodic update for learning decay and memory management
        playerProfiles.values().forEach(profile -> {
            if (profile.shouldCleanup()) {
                playerProfiles.remove(profile.getPlayerId());
            }
        });
    }

    public static class PlayerBehaviorProfile {
        private final UUID playerId;
        private Map<String, Integer> actionCounts = new HashMap<>();
        private long lastActive = System.currentTimeMillis();
        private static final long TIMEOUT = 30 * 60 * 1000; // 30 minutes

        public PlayerBehaviorProfile(UUID playerId) {
            this.playerId = playerId;
        }

        public void recordAction(PlayerAction action) {
            actionCounts.put(action.getType(), actionCounts.getOrDefault(action.getType(), 0) + 1);
            this.lastActive = System.currentTimeMillis();
        }

        public int getActionCount(String actionType) {
            return actionCounts.getOrDefault(actionType, 0);
        }

        public boolean shouldCleanup() {
            return System.currentTimeMillis() - lastActive > TIMEOUT;
        }

        public UUID getPlayerId() {
            return playerId;
        }
    }

    public static class PlayerAction {
        private final String type;
        private final long timestamp;

        public PlayerAction(String type) {
            this.type = type;
            this.timestamp = System.currentTimeMillis();
        }

        public String getType() {
            return type;
        }
    }
}