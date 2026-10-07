package com.smartmobs.adaptation.strategies;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.BlockPos;
import com.smartmobs.adaptation.StrategyType;
import com.smartmobs.learning.LearningMoments;

public class PathReevaluationStrategy extends AbstractAdaptationStrategy {
    
    private Vec3 alternativePath = null;
    private int pathSearchAttempts = 0;
    
    public PathReevaluationStrategy() {
        super(StrategyType.PATH_REEVALUATION, 1, 200);
        this.maxActiveDuration = 80;
    }
    
    @Override
    public boolean canActivate(Mob mob, Player target) {
        // Activé si le mob n'a pas de chemin valide
        return !mob.getNavigation().isDone() && getDistance(mob, target) > 4.0;
    }
    
    @Override
    public void execute(Mob mob, Player target) {
        incrementActiveDuration();
        
        if (alternativePath == null) {
            LearningMoments.searchAnimation(mob);
            alternativePath = findAlternativePath(mob, target);
            pathSearchAttempts++;
        }
        
        if (alternativePath != null) {
            mob.getNavigation().moveTo(alternativePath.x, alternativePath.y, alternativePath.z, 1.0);
        }
    }
    
    @Override
    public boolean shouldContinue(Mob mob, Player target) {
        return activeDuration < maxActiveDuration && pathSearchAttempts < 3;
    }
    
    @Override
    public void onComplete(Mob mob) {
        super.onComplete(mob);
        alternativePath = null;
        pathSearchAttempts = 0;
    }
    
    private Vec3 findAlternativePath(Mob mob, Player target) {
        // Essayer différentes approches
        Vec3 direct = target.position();
        Vec3 left = direct.add(-3, 0, 0);
        Vec3 right = direct.add(3, 0, 0);
        Vec3 back = direct.add(0, 0, -3);
        
        Vec3[] alternatives = {left, right, back, direct};
        
        for (Vec3 alt : alternatives) {
            if (mob.level().getBlockState(BlockPos.containing(alt)).getMaterial().isReplaceable()) {
                return alt;
            }
        }
        
        return direct;
    }
}
