package com.smartmobs.learning;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;

public class LearningMoments {
    
    /**
     * Animation quand le mob observe le joueur
     */
    public static void observationAnimation(Mob mob, Player player) {
        if (mob.level() instanceof ServerLevel serverLevel) {
            mob.getLookControl().setLookAt(
                player.getX(),
                player.getEyeY(),
                player.getZ()
            );
            
            // Petites particules autour de la tête du mob
            for (int i = 0; i < 2; i++) {
                serverLevel.sendParticles(
                    ParticleTypes.SMALL_FLAME,
                    mob.getX() + (Math.random() - 0.5) * 0.5,
                    mob.getEyeY() + (Math.random() - 0.5) * 0.2,
                    mob.getZ() + (Math.random() - 0.5) * 0.5,
                    1, 0, 0, 0, 0.05
                );
            }
        }
    }
    
    /**
     * Animation quand le mob a une idée/adaptation
     */
    public static void adaptationFlashAnimation(Mob mob) {
        if (mob.level() instanceof ServerLevel serverLevel) {
            // Particules autour du mob pour montrer qu'il a compris
            for (int i = 0; i < 5; i++) {
                double angle = (i / 5.0) * Math.PI * 2;
                double x = mob.getX() + Math.cos(angle) * 0.8;
                double z = mob.getZ() + Math.sin(angle) * 0.8;
                
                serverLevel.sendParticles(
                    ParticleTypes.HAPPY_VILLAGER,
                    x, mob.getEyeY(), z,
                    1, 0.2, 0.2, 0.2, 0.1
                );
            }
            
            // Swing du bras/tête du mob
            mob.swing(mob.getUsedItemHand());
        }
    }
    
    /**
     * Animation de recherche/scanning
     */
    public static void searchAnimation(Mob mob) {
        if (mob.level() instanceof ServerLevel serverLevel) {
            // Le mob tourne la tête dans plusieurs directions
            float originalYRot = mob.getYRot();
            for (int i = 0; i < 3; i++) {
                float newRot = originalYRot + (i - 1) * 30f;
                // Particules de recherche
                for (int j = 0; j < 2; j++) {
                    serverLevel.sendParticles(
                        ParticleTypes.MYCELIUM,
                        mob.getX() + Math.cos(Math.toRadians(newRot)) * 1.5,
                        mob.getEyeY(),
                        mob.getZ() + Math.sin(Math.toRadians(newRot)) * 1.5,
                        1, 0.1, 0.1, 0.1, 0.05
                    );
                }
            }
        }
    }
    
    /**
     * Animation d'échec/frustration
     */
    public static void failureAnimation(Mob mob) {
        if (mob.level() instanceof ServerLevel serverLevel) {
            // Particules rouges autour du mob
            for (int i = 0; i < 4; i++) {
                serverLevel.sendParticles(
                    ParticleTypes.SMOKE,
                    mob.getX() + (Math.random() - 0.5) * 1.0,
                    mob.getEyeY() + (Math.random() - 0.5) * 0.3,
                    mob.getZ() + (Math.random() - 0.5) * 1.0,
                    1, 0, 0, 0, 0.08
                );
            }
        }
    }
    
    /**
     * Animation d'attaque/placement de bloc
     */
    public static void actionAnimation(Mob mob) {
        mob.swing(mob.getUsedItemHand());
    }
}
