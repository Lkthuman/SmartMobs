package com.smartmobs.learning;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public class AdvancedAnimations {
    
    /**
     * Animation avancée de réflexion/apprentissage
     */
    public static void thinkingAnimation(Mob mob) {
        if (mob.level() instanceof ServerLevel serverLevel) {
            // Particules en spirale autour du mob
            for (int i = 0; i < 8; i++) {
                double angle = (i / 8.0) * Math.PI * 2;
                double distance = 0.7;
                double x = mob.getX() + Math.cos(angle) * distance;
                double y = mob.getEyeY() + (Math.sin(i * 0.5) * 0.3);
                double z = mob.getZ() + Math.sin(angle) * distance;
                
                serverLevel.sendParticles(
                    ParticleTypes.ENCHANT,
                    x, y, z,
                    1, 0.1, 0.1, 0.1, 0.05
                );
            }
            
            // Son subtil
            if (Math.random() < 0.3) {
                serverLevel.playSound(
                    null,
                    mob.getX(), mob.getY(), mob.getZ(),
                    SoundEvents.VILLAGER_THINKING,
                    SoundSource.HOSTILE,
                    0.3f, 1.0f
                );
            }
        }
    }
    
    /**
     * Animation de concentration/ciblage
     */
    public static void focusAnimation(Mob mob, Player target) {
        if (mob.level() instanceof ServerLevel serverLevel) {
            Vec3 direction = target.getEyePosition().subtract(mob.getEyePosition()).normalize();
            
            // Particules en ligne vers la cible
            for (int i = 0; i < 3; i++) {
                Vec3 pos = mob.getEyePosition().add(direction.scale(i + 1));
                serverLevel.sendParticles(
                    ParticleTypes.DAMAGE_INDICATOR,
                    pos.x, pos.y, pos.z,
                    1, 0.05, 0.05, 0.05, 0.05
                );
            }
        }
    }
    
    /**
     * Animation de préparation d'action
     */
    public static void prepareActionAnimation(Mob mob) {
        if (mob.level() instanceof ServerLevel serverLevel) {
            // Particules pulsantes autour du mob
            for (int i = 0; i < 6; i++) {
                double angle = (i / 6.0) * Math.PI * 2;
                double x = mob.getX() + Math.cos(angle) * 1.0;
                double z = mob.getZ() + Math.sin(angle) * 1.0;
                
                serverLevel.sendParticles(
                    ParticleTypes.CRIT,
                    x, mob.getEyeY(), z,
                    1, 0, 0, 0, 0.1
                );
            }
            
            // Son de préparation
            serverLevel.playSound(
                null,
                mob.getX(), mob.getY(), mob.getZ(),
                SoundEvents.GENERIC_HURT,
                SoundSource.HOSTILE,
                0.2f, 1.2f
            );
        }
    }
    
    /**
     * Animation de mouvements nerveux
     */
    public static void nervousAnimation(Mob mob) {
        if (mob.level() instanceof ServerLevel serverLevel) {
            // Particules erratiques
            for (int i = 0; i < 4; i++) {
                double x = mob.getX() + (Math.random() - 0.5) * 1.5;
                double y = mob.getY() + Math.random() * 1.0;
                double z = mob.getZ() + (Math.random() - 0.5) * 1.5;
                
                serverLevel.sendParticles(
                    ParticleTypes.SMOKE,
                    x, y, z,
                    1, 0, 0, 0, 0.05
                );
            }
        }
    }
    
    /**
     * Animation de découverte (Eureka!)
     */
    public static void discoveryAnimation(Mob mob) {
        if (mob.level() instanceof ServerLevel serverLevel) {
            // Burst de particules joyeuses
            for (int i = 0; i < 10; i++) {
                double angle = (i / 10.0) * Math.PI * 2;
                double x = mob.getX() + Math.cos(angle) * 1.2;
                double z = mob.getZ() + Math.sin(angle) * 1.2;
                
                serverLevel.sendParticles(
                    ParticleTypes.HAPPY_VILLAGER,
                    x, mob.getEyeY() + 0.5, z,
                    1, 0.2, 0.3, 0.2, 0.15
                );
            }
            
            // Son de succès
            serverLevel.playSound(
                null,
                mob.getX(), mob.getY(), mob.getZ(),
                SoundEvents.VILLAGER_YES,
                SoundSource.HOSTILE,
                0.5f, 1.0f
            );
        }
    }
    
    /**
     * Animation de placement de bloc (pour Golem)
     */
    public static void blockPlaceAnimation(Mob mob) {
        if (mob.level() instanceof ServerLevel serverLevel) {
            // Particules de poussière
            for (int i = 0; i < 8; i++) {
                double x = mob.getX() + (Math.random() - 0.5) * 0.8;
                double y = mob.getY();
                double z = mob.getZ() + (Math.random() - 0.5) * 0.8;
                
                serverLevel.sendParticles(
                    new BlockParticleOption(ParticleTypes.BLOCK, Blocks.COBBLESTONE.defaultBlockState()),
                    x, y, z,
                    1, 0.1, 0.1, 0.1, 0.1
                );
            }
            
            // Son de placement
            serverLevel.playSound(
                null,
                mob.getX(), mob.getY(), mob.getZ(),
                SoundEvents.GRINDSTONE_USE,
                SoundSource.HOSTILE,
                0.4f, 0.8f
            );
        }
    }
    
    /**
     * Animation de frustration/échec
     */
    public static void frustrationAnimation(Mob mob) {
        if (mob.level() instanceof ServerLevel serverLevel) {
            // Particules rouges et noires
            for (int i = 0; i < 5; i++) {
                double x = mob.getX() + (Math.random() - 0.5) * 1.0;
                double y = mob.getEyeY() + (Math.random() - 0.5) * 0.5;
                double z = mob.getZ() + (Math.random() - 0.5) * 1.0;
                
                serverLevel.sendParticles(
                    ParticleTypes.SOUL,
                    x, y, z,
                    1, 0, 0, 0, 0.08
                );
            }
            
            // Son de frustration
            serverLevel.playSound(
                null,
                mob.getX(), mob.getY(), mob.getZ(),
                SoundEvents.ZOMBIE_ATTACK_WOODEN_DOOR,
                SoundSource.HOSTILE,
                0.3f, 0.7f
            );
        }
    }
    
    /**
     * Animation de curiosité
     */
    public static void curiosityAnimation(Mob mob) {
        if (mob.level() instanceof ServerLevel serverLevel) {
            // Petits point d'interrogation de particules
            for (int i = 0; i < 4; i++) {
                double x = mob.getX() + (Math.random() - 0.5) * 0.6;
                double y = mob.getEyeY() + 0.5 + (i * 0.2);
                double z = mob.getZ() + (Math.random() - 0.5) * 0.6;
                
                serverLevel.sendParticles(
                    ParticleTypes.DRAGON_BREATH,
                    x, y, z,
                    1, 0, 0, 0, 0.03
                );
            }
        }
    }
}
