package com.smartmobs.adaptation.strategies;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import com.smartmobs.adaptation.StrategyType;
import com.smartmobs.learning.LearningMoments;

public class SeekCoverStrategy extends AbstractAdaptationStrategy {
    
    private BlockPos targetCover = null;
    
    public SeekCoverStrategy() {
        super(StrategyType.SEEK_COVER, 1, 200);
        this.maxActiveDuration = 60;
    }
    
    @Override
    public boolean canActivate(Mob mob, Player target) {
        return canSeeTarget(mob, target) && getDistance(mob, target) < 16.0;
    }
    
    @Override
    public void execute(Mob mob, Player target) {
        incrementActiveDuration();
        
        if (isActiveDurationExceeded()) {
            return;
        }
        
        if (targetCover == null || !isGoodCover(mob, targetCover, target)) {
            targetCover = findNearestCover(mob, target);
        }
        
        if (targetCover != null) {
            LearningMoments.searchAnimation(mob);
            mob.getNavigation().moveTo(targetCover.getX() + 0.5, targetCover.getY(), targetCover.getZ() + 0.5, 1.0);
        }
    }
    
    @Override
    public boolean shouldContinue(Mob mob, Player target) {
        return canSeeTarget(mob, target) && activeDuration < maxActiveDuration;
    }
    
    @Override
    public void onComplete(Mob mob) {
        super.onComplete(mob);
        targetCover = null;
    }
    
    private BlockPos findNearestCover(Mob mob, Player target) {
        BlockPos bestCover = null;
        double bestDistance = Double.MAX_VALUE;
        
        int searchRadius = 8;
        BlockPos mobPos = mob.blockPosition();
        
        for (int x = -searchRadius; x <= searchRadius; x++) {
            for (int y = -2; y <= 2; y++) {
                for (int z = -searchRadius; z <= searchRadius; z++) {
                    BlockPos checkPos = mobPos.offset(x, y, z);
                    if (isGoodCover(mob, checkPos, target)) {
                        double dist = mob.distanceToSqr(Vec3.atCenterOf(checkPos));
                        if (dist < bestDistance) {
                            bestDistance = dist;
                            bestCover = checkPos;
                        }
                    }
                }
            }
        }
        
        return bestCover;
    }
    
    private boolean isGoodCover(Mob mob, BlockPos pos, Player target) {
        if (mob.level().isEmptyBlock(pos)) {
            return false;
        }
        
        // Vérifier que c'est bloqué entre le mob et le joueur
        Vec3 mobEye = mob.getEyePosition();
        Vec3 targetPos = target.getEyePosition();
        Vec3 checkPos = Vec3.atCenterOf(pos);
        
        return !mob.level().clipWithInteractionOverride(mobEye, targetPos, pos).isEmpty();
    }
}
