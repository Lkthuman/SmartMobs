package com.smartmobs.memory;

import java.util.*;

public class MobMemory {
    private Map<String, Integer> observedBehaviors = new HashMap<>();
    private List<String> failedStrategies = new ArrayList<>();
    private long lastUpdate = System.currentTimeMillis();
    private static final long MEMORY_DECAY_TIME = 5 * 60 * 1000; // 5 minutes

    public void recordObservation(String behavior) {
        observedBehaviors.put(behavior, observedBehaviors.getOrDefault(behavior, 0) + 1);
        this.lastUpdate = System.currentTimeMillis();
    }

    public int getObservationCount(String behavior) {
        return observedBehaviors.getOrDefault(behavior, 0);
    }

    public void recordFailedStrategy(String strategy) {
        failedStrategies.add(strategy);
    }

    public boolean hasFailedStrategy(String strategy) {
        return failedStrategies.contains(strategy);
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - lastUpdate > MEMORY_DECAY_TIME;
    }
}