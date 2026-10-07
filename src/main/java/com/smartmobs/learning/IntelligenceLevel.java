package com.smartmobs.learning;

/** Mob intelligence presets scaling how fast and how often mobs adapt. */
public enum IntelligenceLevel {
    LOW(0.5, 0.5, 1.5),
    NORMAL(1.0, 1.0, 1.0),
    SMART(1.5, 1.4, 0.75),
    VERY_SMART(2.0, 1.8, 0.5);

    public final double learningMultiplier;
    public final double chanceMultiplier;
    public final double cooldownMultiplier;

    IntelligenceLevel(double learning, double chance, double cooldown) {
        this.learningMultiplier = learning;
        this.chanceMultiplier = chance;
        this.cooldownMultiplier = cooldown;
    }
}
