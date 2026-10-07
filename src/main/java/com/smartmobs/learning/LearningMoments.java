package com.smartmobs.learning;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;

/** Visible, vanilla-compatible reactions: pause, head turn, look-around, arm swing. */
public final class LearningMoments {
    private LearningMoments() {}

    /** Mob stops and stares at its target. */
    public static void observe(PathfinderMob mob, LivingEntity target) {
        mob.getNavigation().stop();
        mob.getLookControl().setLookAt(target, 30.0F, 30.0F);
    }

    /** Mob scans its surroundings by sweeping its look direction. */
    public static void lookAround(PathfinderMob mob, int ticksElapsed) {
        double angle = Math.toRadians((ticksElapsed * 18) % 360);
        double x = mob.getX() + Math.cos(angle) * 4.0;
        double z = mob.getZ() + Math.sin(angle) * 4.0;
        mob.getLookControl().setLookAt(x, mob.getEyeY() - 0.3, z);
    }

    /** Subtle "realisation" cue: arm swing and a few vanilla particles. */
    public static void realise(PathfinderMob mob, LivingEntity target) {
        mob.getNavigation().stop();
        mob.getLookControl().setLookAt(target, 40.0F, 40.0F);
        mob.swing(InteractionHand.MAIN_HAND);
        if (mob.level() instanceof ServerLevel level) {
            level.sendParticles(ParticleTypes.HAPPY_VILLAGER,
                    mob.getX(), mob.getEyeY() + 0.4, mob.getZ(), 3, 0.2, 0.1, 0.2, 0.0);
        }
    }
}
