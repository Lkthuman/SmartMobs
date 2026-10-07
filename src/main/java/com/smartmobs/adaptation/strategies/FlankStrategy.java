package com.smartmobs.adaptation.strategies;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import com.smartmobs.adaptation.StrategyType;
import com.smartmobs.learning.LearningMoments;

public class FlankStrategy extends AbstractAdaptationStrategy {
    
    private int flankDirection = 1;
    
    public FlankStrategy() {
        super(StrategyType.FLANK, 2, 250);
        this.maxActiveDuration = 100;
    }
    
    @Override
    public boolean canActivate(Mob mob, Player target) {
        return getDistance(mob, target) < 12.0 && getDistance(mob, target) > 2.0;
    }
    
    @Override
    public void execute(Mob mob, Player target) {
        incrementActiveDuration();
        
        // Déterminer la direction de flanquement (gauche ou droite)
        if (activeDuration == 1) {
            flankDirection = Math.random() > 0.5 ? 1 : -1;
            LearningMoments.adaptationFlashAnimation(mob);
        }
        
        Vec3 toTarget = target.position().subtract(mob.position()).normalize();
        Vec3 perpendicular = new Vec3(-toTarget.z * flankDirection, 0, toTarget.x * flankDirection);
        Vec3 flankPos = target.position().add(perpendicular.scale(3.0));
        
        LearningMoments.searchAnimation(mob);
        mob.getNavigation().moveTo(flankPos.x, flankPos.y, flankPos.z, 1.2);
    }
    
    @Override
    public boolean shouldContinue(Mob mob, Player target) {
        return getDistance(mob, target) < 15.0 && activeDuration < maxActiveDuration;
    }
}
