package com.smartmobs.adaptation.strategies;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import com.smartmobs.adaptation.AdaptationStrategy;
import com.smartmobs.adaptation.StrategyType;

public abstract class AbstractAdaptationStrategy implements AdaptationStrategy {
    
    protected StrategyType type;
    protected int minimumLevel;
    protected int cooldownTicks;
    protected int activeDuration = 0;
    protected int maxActiveDuration = 100;
    
    public AbstractAdaptationStrategy(StrategyType type, int minimumLevel, int cooldownTicks) {
        this.type = type;
        this.minimumLevel = minimumLevel;
        this.cooldownTicks = cooldownTicks;
    }
    
    @Override
    public StrategyType getType() {
        return type;
    }
    
    @Override
    public int getMinimumAdaptationLevel() {
        return minimumLevel;
    }
    
    @Override
    public int getCooldownTicks() {
        return cooldownTicks;
    }
    
    @Override
    public void onComplete(Mob mob) {
        activeDuration = 0;
    }
    
    protected void incrementActiveDuration() {
        activeDuration++;
    }
    
    protected boolean isActiveDurationExceeded() {
        return activeDuration >= maxActiveDuration;
    }
    
    protected boolean canSeeTarget(Mob mob, Player player) {
        return mob.hasLineOfSight(player);
    }
    
    protected double getDistance(Mob mob, Player player) {
        return mob.distanceTo(player);
    }
}
