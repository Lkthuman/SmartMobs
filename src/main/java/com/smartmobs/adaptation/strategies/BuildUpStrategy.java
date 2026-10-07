package com.smartmobs.adaptation.strategies;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import com.smartmobs.adaptation.StrategyType;
import com.smartmobs.learning.LearningMoments;
import com.smartmobs.learning.AdvancedAnimations;

public class BuildUpStrategy extends AbstractAdaptationStrategy {
    
    private int blocksPlaced = 0;
    private final int MAX_BLOCKS = 8;
    private BlockPos targetBlock = null;
    private int searchCooldown = 0;
    private int blockPlacementCooldown = 0;
    
    public BuildUpStrategy() {
        super(StrategyType.BUILD_UP, 2, 500);
        this.maxActiveDuration = 200;
    }
    
    @Override
    public boolean canActivate(Mob mob, Player target) {
        // Activé si le joueur est en hauteur et inaccessible
        return (target.getY() - mob.getY()) > 2.0;
    }
    
    @Override
    public void execute(Mob mob, Player target) {
        incrementActiveDuration();
        searchCooldown++;
        blockPlacementCooldown++;
        
        // Animation de réflexion au début
        if (activeDuration == 1) {
            AdvancedAnimations.thinkingAnimation(mob);
        }
        
        // Chercher un bloc à utiliser
        if (searchCooldown > 20 || targetBlock == null) {
            targetBlock = findNearestPickableBlock(mob);
            searchCooldown = 0;
            
            if (targetBlock != null) {
                AdvancedAnimations.discoveryAnimation(mob);
            }
        }
        
        if (targetBlock != null && blocksPlaced < MAX_BLOCKS) {
            // Aller vers le bloc
            double distToBlock = mob.distanceTo(Vec3.atCenterOf(targetBlock));
            
            if (distToBlock < 2.0 && blockPlacementCooldown > 10) {
                // Placer le bloc sous le mob
                BlockPos placementPos = mob.blockPosition().below();
                if (mob.level().getBlockState(placementPos).getMaterial().isReplaceable()) {
                    mob.level().setBlockAndUpdate(placementPos, Blocks.COBBLESTONE.defaultBlockState());
                    blocksPlaced++;
                    blockPlacementCooldown = 0;
                    
                    // Animer l'action
                    AdvancedAnimations.blockPlaceAnimation(mob);
                    
                    // Trouver un nouveau bloc après placement
                    targetBlock = findNearestPickableBlock(mob);
                }
            } else {
                // Aller vers le bloc
                LearningMoments.searchAnimation(mob);
                mob.getNavigation().moveTo(targetBlock.getX() + 0.5, targetBlock.getY() + 0.5, targetBlock.getZ() + 0.5, 1.0);
            }
        } else if (blocksPlaced >= MAX_BLOCKS) {
            // Monter vers le joueur une fois assez de blocs placés
            AdvancedAnimations.prepareActionAnimation(mob);
            mob.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), 1.1);
        }
    }
    
    @Override
    public boolean shouldContinue(Mob mob, Player target) {
        return (target.getY() - mob.getY()) > 0.5 && activeDuration < maxActiveDuration && blocksPlaced < MAX_BLOCKS + 2;
    }
    
    @Override
    public void onComplete(Mob mob) {
        super.onComplete(mob);
        blocksPlaced = 0;
        targetBlock = null;
        searchCooldown = 0;
        blockPlacementCooldown = 0;
    }
    
    private BlockPos findNearestPickableBlock(Mob mob) {
        BlockPos bestBlock = null;
        double bestDistance = Double.MAX_VALUE;
        
        int searchRadius = 6;
        BlockPos mobPos = mob.blockPosition();
        
        for (int x = -searchRadius; x <= searchRadius; x++) {
            for (int y = -2; y <= 2; y++) {
                for (int z = -searchRadius; z <= searchRadius; z++) {
                    BlockPos checkPos = mobPos.offset(x, y, z);
                    if (isPickableBlock(mob, checkPos)) {
                        double dist = mob.distanceToSqr(Vec3.atCenterOf(checkPos));
                        if (dist < bestDistance) {
                            bestDistance = dist;
                            bestBlock = checkPos;
                        }
                    }
                }
            }
        }
        
        return bestBlock;
    }
    
    private boolean isPickableBlock(Mob mob, BlockPos pos) {
        var state = mob.level().getBlockState(pos);
        return state.is(Blocks.COBBLESTONE) || state.is(Blocks.DIRT) || state.is(Blocks.STONE);
    }
}
