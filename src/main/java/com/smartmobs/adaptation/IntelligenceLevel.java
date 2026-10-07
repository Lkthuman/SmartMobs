package com.smartmobs.adaptation;

public enum IntelligenceLevel {
    LOW(0.5f, 0.3f, 1.5f),
    NORMAL(1.0f, 1.0f, 1.0f),
    SMART(1.5f, 1.5f, 0.7f),
    VERY_SMART(2.0f, 2.0f, 0.5f);

    private final float learningSpeedMultiplier;
    private final float adaptationChanceMultiplier;
    private final float cooldownMultiplier;

    IntelligenceLevel(float learningSpeed, float adaptationChance, float cooldown) {
        this.learningSpeedMultiplier = learningSpeed;
        this.adaptationChanceMultiplier = adaptationChance;
        this.cooldownMultiplier = cooldown;
    }

    public float getLearningSpeedMultiplier() {
        return learningSpeedMultiplier;
    }

    public float getAdaptationChanceMultiplier() {
        return adaptationChanceMultiplier;
    }

    public float getCooldownMultiplier() {
        return cooldownMultiplier;
    }
}
