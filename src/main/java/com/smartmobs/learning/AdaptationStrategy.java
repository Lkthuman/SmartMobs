package com.smartmobs.learning;

import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.LivingEntity;

/** A reusable behaviour a mob can adopt once it has learned enough. */
public interface AdaptationStrategy {

    StrategyType type();

    /** Minimum adaptation level (1..3) required. */
    int requiredLevel();

    /** Cooldown in ticks before the strategy can start again. */
    int cooldownTicks();

    boolean canStart(PathfinderMob mob, LivingEntity target, MobLearningState state);

    void start(PathfinderMob mob, LivingEntity target, MobLearningState state);

    /** Called at a throttled rate (every few ticks). */
    void tick(PathfinderMob mob, LivingEntity target, MobLearningState state);

    boolean shouldStop(PathfinderMob mob, LivingEntity target, MobLearningState state);

    void stop(PathfinderMob mob, MobLearningState state);
}
