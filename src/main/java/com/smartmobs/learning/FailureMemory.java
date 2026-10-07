package com.smartmobs.learning;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

/** Per-mob short-lived memory of failed strategies. Holds no entity references. */
public final class FailureMemory {
    private final Map<StrategyType, Integer> failures = new EnumMap<>(StrategyType.class);

    public void recordFailure(StrategyType type) {
        failures.merge(type, 1, Integer::sum);
    }

    public void recordSuccess(StrategyType type) {
        failures.remove(type);
    }

    public int failuresOf(StrategyType type) {
        return failures.getOrDefault(type, 0);
    }

    /** Chance (0..1) the mob picks this strategy again. Never 0, so mobs stay imperfect. */
    public double retryWeight(StrategyType type, Random random) {
        int f = failuresOf(type);
        double base = Math.max(0.15, 1.0 - 0.25 * f);
        double noise = (random.nextDouble() - 0.5) * 0.2;
        return Math.min(1.0, Math.max(0.05, base + noise));
    }
}
