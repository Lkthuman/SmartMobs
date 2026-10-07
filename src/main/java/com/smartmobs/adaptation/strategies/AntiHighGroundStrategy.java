package com.smartmobs.adaptation.strategies;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import com.smartmobs.adaptation.StrategyType;
import com.smartmobs.learning.LearningMoments;

public class AntiHighGroundStrategy extends AbstractAdaptationStrategy {
    
    public AntiHighGroundStrategy() {
        super(StrategyType.ANTI_HIGH_GROUND, 2, 300);
        this.maxActiveDuration = 120;
    }
    
    @Override
    public boolean canActivate(Mob mob, Player target) {
        // Activé si le joueur est suffisamment plus haut
        return (target.getY() - mob.getY()) > 2.5;
    }
    
    @Override
    public void execute(Mob mob, Player target) {
        incrementActiveDuration();
        
        LearningMoments.searchAnimation(mob);
        
        // Essayer de monter vers le joueur
        mob.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), 1.1);
        
        // Chercher des blocs pour grimper
        if (activeDuration % 10 == 0) {
            mob.getJumpControl().jump();
            LearningMoments.actionAnimation(mob);
        }
    }
    
    @Override
    public boolean shouldContinue(Mob mob, Player target) {
        return (target.getY() - mob.getY()) > 1.0 && activeDuration < maxActiveDuration;
    }
}
