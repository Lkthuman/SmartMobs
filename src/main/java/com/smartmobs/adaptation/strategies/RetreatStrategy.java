package com.smartmobs.adaptation.strategies;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import com.smartmobs.adaptation.StrategyType;
import com.smartmobs.learning.LearningMoments;

public class RetreatStrategy extends AbstractAdaptationStrategy {
    
    public RetreatStrategy() {
        super(StrategyType.RETREAT, 1, 180);
        this.maxActiveDuration = 60;
    }
    
    @Override
    public boolean canActivate(Mob mob, Player target) {
        // Retraite si le joueur est trop proche ou trop puissant
        return getDistance(mob, target) < 4.0;
    }
    
    @Override
    public void execute(Mob mob, Player target) {
        incrementActiveDuration();
        
        LearningMoments.failureAnimation(mob);
        
        // S'éloigner du joueur
        Vec3 away = mob.position().subtract(target.position()).normalize().scale(2.0);
        mob.getNavigation().moveTo(
            mob.getX() + away.x,
            mob.getY(),
            mob.getZ() + away.z,
            1.3
        );
    }
    
    @Override
    public boolean shouldContinue(Mob mob, Player target) {
        return getDistance(mob, target) < 6.0 && activeDuration < maxActiveDuration;
    }
}
