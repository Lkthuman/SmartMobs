package com.smartmobs.ai;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import com.smartmobs.adaptation.*;
import com.smartmobs.memory.ModAttachments;
import com.smartmobs.memory.PlayerBehavior;
import com.smartmobs.config.SmartMobsConfig;
import com.smartmobs.learning.LearningMoments;
import java.util.*;

public class AdaptiveGoal extends Goal {
    
    private final Mob mob;
    private Player target;
    private int observationTicks = 0;
    private int learningTicks = 0;
    private StrategyManager strategyManager;
    private MobMemory mobMemory;
    private int failedPathfinds = 0;
    private int pathfindCheckTicks = 0;
    
    public AdaptiveGoal(Mob mob) {
        this.mob = mob;
        this.strategyManager = new StrategyManager();
    }
    
    @Override
    public boolean canUse() {
        if (!SmartMobsConfig.COMMON.enabled.get()) {
            return false;
        }
        
        target = mob.getTarget();
        if (target == null) {
            return false;
        }
        
        if (!mob.hasLineOfSight(target)) {
            return false;
        }
        
        double distance = mob.distanceTo(target);
        if (distance > SmartMobsConfig.COMMON.observationRange.get()) {
            return false;
        }
        
        if (mobMemory == null) {
            initializeMobMemory();
        }
        
        return true;
    }
    
    @Override
    public void start() {
        observationTicks = 0;
        learningTicks = 0;
        if (mobMemory != null) {
            mobMemory.updateLastObservation();
            LearningMoments.observationAnimation(mob, target);
        }
    }
    
    @Override
    public void tick() {
        if (target == null || !target.isAlive()) {
            return;
        }
        
        observationTicks++;
        pathfindCheckTicks++;
        
        // Mettre à jour les données comportementales du joueur
        PlayerBehavior behavior = getPlayerBehavior();
        if (behavior != null) {
            updateAdaptationLevel(behavior);
        }
        
        // Vérifier si le pathfinding échoue
        if (pathfindCheckTicks > 20) {
            checkPathfindingFailure();
            pathfindCheckTicks = 0;
        }
        
        // Sélectionner et exécuter une stratégie
        IntelligenceLevel intelligence = IntelligenceLevel.NORMAL;
        AdaptationStrategy strategy = strategyManager.selectStrategy(mob, target, mobMemory, intelligence);
        
        if (strategy != null) {
            if (strategy.canActivate(mob, target)) {
                strategy.execute(mob, target);
                
                if (!strategy.shouldContinue(mob, target)) {
                    strategy.onComplete(mob);
                    strategyManager.clearStrategy();
                }
            }
        }
        
        // Animation d'observation régulière
        if (observationTicks % 40 == 0) {
            LearningMoments.observationAnimation(mob, target);
        }
        
        // Augmenter la progression d'apprentissage
        if (mobMemory != null && observationTicks % 10 == 0) {
            mobMemory.increaseLearningProgress((int)(SmartMobsConfig.COMMON.learningSpeed.get() * 1.5));
            
            if (mobMemory.hasLearned()) {
                LearningMoments.adaptationFlashAnimation(mob);
                mobMemory.resetLearning();
            }
        }
    }
    
    @Override
    public void stop() {
        strategyManager.clearStrategy();
        if (mobMemory != null) {
            mobMemory.resetPathfindingFailures();
        }
    }
    
    @Override
    public boolean isInterruptable() {
        return true;
    }
    
    private void initializeMobMemory() {
        mobMemory = new MobMemory(mob.getUUID(), target.getUUID());
    }
    
    private PlayerBehavior getPlayerBehavior() {
        if (target == null) return null;
        
        try {
            var attachment = target.getData(ModAttachments.PLAYER_BEHAVIOR);
            if (attachment != null) {
                return attachment;
            }
        } catch (Exception e) {
            // Silently fail if attachment not available
        }
        
        return null;
    }
    
    private void updateAdaptationLevel(PlayerBehavior behavior) {
        if (mobMemory == null) return;
        
        float totalBehavior = behavior.getMeleeUsage() + behavior.getRangedUsage() + 
                             behavior.getBuildingUsage() + behavior.getHighGroundUsage();
        
        int newLevel = 0;
        if (totalBehavior > 0.25f) newLevel = 1;
        if (totalBehavior > 0.50f) newLevel = 2;
        if (totalBehavior > 0.75f) newLevel = 3;
        
        mobMemory.setAdaptationLevel(Math.min(newLevel, SmartMobsConfig.COMMON.maxAdaptationLevel.get()));
    }
    
    private void checkPathfindingFailure() {
        if (!mob.getNavigation().isDone()) {
            failedPathfinds = 0;
            return;
        }
        
        if (mob.distanceTo(target) > 2.0) {
            failedPathfinds++;
            
            if (failedPathfinds > 3 && mobMemory != null) {
                mobMemory.recordPathfindingFailure();
                LearningMoments.failureAnimation(mob);
                
                if (mobMemory.getFailedPathfindingAttempts() > 5) {
                    // Trop d'échecs, essayer une autre stratégie
                    strategyManager.clearStrategy();
                    mobMemory.resetPathfindingFailures();
                }
            }
        }
    }
    
    public MobMemory getMobMemory() {
        return mobMemory;
    }
}
