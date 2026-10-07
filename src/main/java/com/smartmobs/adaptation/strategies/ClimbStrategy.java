package com.smartmobs.adaptation.strategies;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import com.smartmobs.adaptation.StrategyType;
import com.smartmobs.learning.LearningMoments;

public class ClimbStrategy extends AbstractAdaptationStrategy {
    
    public ClimbStrategy() {
        super(StrategyType.CLIMB, 2, 200);
        this.maxActiveDuration = 100;
    }
    
    @Override
    public boolean canActivate(Mob mob, Player target) {
        // Activé si le joueur est plus haut et qu'il y a du terrain
        return (target.getY() - mob.getY()) > 1.5 && getDistance(mob, target) < 10.0;
    }
    
    @Override
    public void execute(Mob mob, Player target) {
        incrementActiveDuration();
        
        if (activeDuration % 15 == 0) {
            mob.getJumpControl().jump();
            LearningMoments.actionAnimation(mob);
        }
        
        LearningMoments.searchAnimation(mob);
        mob.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), 1.0);
    }
    
    @Override
    public boolean shouldContinue(Mob mob, Player target) {
        return (target.getY() - mob.getY()) > 0.5 && activeDuration < maxActiveDuration;
    }
}
