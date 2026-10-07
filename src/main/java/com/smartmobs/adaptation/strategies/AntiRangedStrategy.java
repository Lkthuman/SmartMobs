package com.smartmobs.adaptation.strategies;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import com.smartmobs.adaptation.StrategyType;
import com.smartmobs.learning.LearningMoments;

public class AntiRangedStrategy extends AbstractAdaptationStrategy {
    
    private int seekCoverTicks = 0;
    
    public AntiRangedStrategy() {
        super(StrategyType.ANTI_RANGED, 1, 150);
        this.maxActiveDuration = 80;
    }
    
    @Override
    public boolean canActivate(Mob mob, Player target) {
        return getDistance(mob, target) > 6.0 && getDistance(mob, target) < 20.0;
    }
    
    @Override
    public void execute(Mob mob, Player target) {
        incrementActiveDuration();
        
        seekCoverTicks++;
        if (seekCoverTicks > 40) {
            // Avancer vers le joueur après avoir cherché de la couverture
            LearningMoments.adaptationFlashAnimation(mob);
            mob.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), 1.2);
            seekCoverTicks = 0;
        } else {
            // Chercher de la couverture
            LearningMoments.searchAnimation(mob);
            Vec3 away = mob.position().subtract(target.position()).normalize().scale(2.0);
            mob.getNavigation().moveTo(mob.getX() + away.x, mob.getY(), mob.getZ() + away.z, 0.8);
        }
    }
    
    @Override
    public boolean shouldContinue(Mob mob, Player target) {
        return getDistance(mob, target) > 4.0 && activeDuration < maxActiveDuration;
    }
}
