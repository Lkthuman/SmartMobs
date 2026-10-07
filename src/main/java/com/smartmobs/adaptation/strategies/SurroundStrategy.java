package com.smartmobs.adaptation.strategies;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import com.smartmobs.adaptation.StrategyType;
import com.smartmobs.learning.LearningMoments;

public class SurroundStrategy extends AbstractAdaptationStrategy {
    
    private double targetAngle = 0;
    
    public SurroundStrategy() {
        super(StrategyType.SURROUND, 3, 400);
        this.maxActiveDuration = 150;
    }
    
    @Override
    public boolean canActivate(Mob mob, Player target) {
        return getDistance(mob, target) < 10.0;
    }
    
    @Override
    public void execute(Mob mob, Player target) {
        incrementActiveDuration();
        
        if (activeDuration == 1) {
            targetAngle = Math.random() * Math.PI * 2;
            LearningMoments.adaptationFlashAnimation(mob);
        }
        
        // Cercler autour du joueur
        double angle = targetAngle + (activeDuration * 0.02);
        double radius = 3.0;
        double x = target.getX() + Math.cos(angle) * radius;
        double z = target.getZ() + Math.sin(angle) * radius;
        
        LearningMoments.searchAnimation(mob);
        mob.getNavigation().moveTo(x, target.getY(), z, 1.1);
    }
    
    @Override
    public boolean shouldContinue(Mob mob, Player target) {
        return getDistance(mob, target) < 12.0 && activeDuration < maxActiveDuration;
    }
}
