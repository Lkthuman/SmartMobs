package com.smartmobs.learning;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.InteractionHand;

/**
 * Golem BUILD_UP: after repeated failed attempts to reach an elevated player, the golem
 * collects a loose block from the ground nearby (no free blocks), walks under the player
 * and stacks it to climb. Scan is small, bounded and only done once per search step.
 */
public final class GolemBuildUp implements AdaptationStrategy {
    private static final int SCAN_RADIUS = 4;
    private static final int MAX_CARRIED = 4;

    private BlockPos fetchTarget;

    @Override public StrategyType type() { return StrategyType.BUILD_UP; }
    @Override public int requiredLevel() { return 2; }
    @Override public int cooldownTicks() { return 200; }

    @Override
    public boolean canStart(PathfinderMob mob, LivingEntity target, MobLearningState state) {
        return target.getY() > mob.getY() + 2.0
                && state.unreachableAttempts >= 3
                && mob.getNavigation().isDone();
    }

    @Override
    public void start(PathfinderMob mob, LivingEntity target, MobLearningState state) {
        state.current = StrategyType.BUILD_UP;
        state.action = "SEARCH_BLOCK";
        fetchTarget = null;
        LearningMoments.realise(mob, target);
    }

    @Override
    public void tick(PathfinderMob mob, LivingEntity target, MobLearningState state) {
        if (!(mob.level() instanceof ServerLevel level)) return;

        if (state.blocksCarried == 0) {
            if (fetchTarget == null) fetchTarget = findLooseBlock(level, mob.blockPosition());
            if (fetchTarget == null) { state.action = "NO_BLOCK_FOUND"; return; }
            state.action = "FETCH_BLOCK";
            mob.getNavigation().moveTo(fetchTarget.getX() + 0.5, fetchTarget.getY(), fetchTarget.getZ() + 0.5, 1.0);
            if (mob.blockPosition().distSqr(fetchTarget) <= 4.0) {
                level.destroyBlock(fetchTarget, false);
                mob.swing(InteractionHand.MAIN_HAND);
                state.blocksCarried = Math.min(MAX_CARRIED, 1);
                fetchTarget = null;
            }
            return;
        }

        state.action = "BUILD_UP";
        BlockPos under = mob.blockPosition();
        if (mob.getY() < target.getY() - 1.0 && level.getBlockState(under.above(2)).isAir()) {
            mob.swing(InteractionHand.MAIN_HAND);
            level.setBlockAndUpdate(under, Blocks.COBBLESTONE.defaultBlockState());
            mob.teleportTo(mob.getX(), mob.getY() + 1.0, mob.getZ());
            state.blocksCarried--;
        }
    }

    @Override
    public boolean shouldStop(PathfinderMob mob, LivingEntity target, MobLearningState state) {
        return mob.getY() >= target.getY() - 1.0 || (state.blocksCarried == 0 && fetchTarget == null && "NO_BLOCK_FOUND".equals(state.action));
    }

    @Override
    public void stop(PathfinderMob mob, MobLearningState state) {
        state.current = null;
        state.action = "NONE";
        fetchTarget = null;
    }

    private static BlockPos findLooseBlock(ServerLevel level, BlockPos origin) {
        for (BlockPos p : BlockPos.betweenClosed(origin.offset(-SCAN_RADIUS, -1, -SCAN_RADIUS), origin.offset(SCAN_RADIUS, 1, SCAN_RADIUS))) {
            BlockState s = level.getBlockState(p);
            if (s.is(Blocks.COBBLESTONE) || s.is(Blocks.DIRT) || s.is(Blocks.STONE)) {
                return p.immutable();
            }
        }
        return null;
    }
}
