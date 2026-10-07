package com.smartmobs.adaptation;

import java.util.*;

public class MobMemory {
    private UUID mobId;
    private UUID playerId;
    
    private Map<StrategyType, Integer> strategyFailures = new HashMap<>();
    private Map<StrategyType, Long> strategyCooldowns = new HashMap<>();
    private StrategyType currentStrategy = null;
    private int currentAdaptationLevel = 0;
    private int learningProgress = 0;
    private long lastObservationTime = 0;
    private int failedPathfindingAttempts = 0;
    private boolean isLearning = false;
    
    public MobMemory(UUID mobId, UUID playerId) {
        this.mobId = mobId;
        this.playerId = playerId;
    }
    
    public void recordStrategyFailure(StrategyType strategy) {
        strategyFailures.put(strategy, strategyFailures.getOrDefault(strategy, 0) + 1);
    }
    
    public float getStrategyFailurePenalty(StrategyType strategy) {
        int failures = strategyFailures.getOrDefault(strategy, 0);
        return Math.max(0.1f, 1.0f - (failures * 0.2f));
    }
    
    public boolean isOnStrategyReuse(StrategyType strategy) {
        return strategyFailures.getOrDefault(strategy, 0) > 0;
    }
    
    public void recordPathfindingFailure() {
        failedPathfindingAttempts++;
    }
    
    public void resetPathfindingFailures() {
        failedPathfindingAttempts = 0;
    }
    
    public int getFailedPathfindingAttempts() {
        return failedPathfindingAttempts;
    }
    
    public void increaseLearningProgress(int amount) {
        learningProgress = Math.min(100, learningProgress + amount);
        if (learningProgress >= 100) {
            isLearning = true;
            learningProgress = 0;
        }
    }
    
    public int getLearningProgress() {
        return learningProgress;
    }
    
    public boolean hasLearned() {
        return isLearning;
    }
    
    public void resetLearning() {
        isLearning = false;
    }
    
    public void setCurrentStrategy(StrategyType strategy) {
        this.currentStrategy = strategy;
        this.strategyCooldowns.put(strategy, System.currentTimeMillis());
    }
    
    public StrategyType getCurrentStrategy() {
        return currentStrategy;
    }
    
    public boolean isStrategyCooldown(StrategyType strategy, int cooldownTicks) {
        Long lastUse = strategyCooldowns.get(strategy);
        if (lastUse == null) return false;
        long elapsed = (System.currentTimeMillis() - lastUse) / 50; // ~50ms par tick
        return elapsed < cooldownTicks;
    }
    
    public void setAdaptationLevel(int level) {
        this.currentAdaptationLevel = level;
    }
    
    public int getAdaptationLevel() {
        return currentAdaptationLevel;
    }
    
    public UUID getMobId() {
        return mobId;
    }
    
    public UUID getPlayerId() {
        return playerId;
    }
    
    public void updateLastObservation() {
        this.lastObservationTime = System.currentTimeMillis();
    }
    
    public long getLastObservationTime() {
        return lastObservationTime;
    }
}
