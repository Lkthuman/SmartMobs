package com.smartmobs.adaptation;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class MobAdaptation {
    private final LivingEntity mob;
    private int adaptationLevel = 0;
    private int observationTicks = 0;
    private AdaptationStrategy currentStrategy = AdaptationStrategy.NONE;

    public MobAdaptation(LivingEntity mob) {
        this.mob = mob;
    }

    public void update(Player player) {
        observationTicks++;
        
        // Evaluate adaptation level based on player behavior
        if (observationTicks > 100) {
            adaptationLevel = Math.min(adaptationLevel + 1, 3);
            observationTicks = 0;
        }
    }

    public int getAdaptationLevel() {
        return adaptationLevel;
    }

    public void setStrategy(AdaptationStrategy strategy) {
        this.currentStrategy = strategy;
    }

    public AdaptationStrategy getStrategy() {
        return currentStrategy;
    }
}
