package com.smartmobs.ai;

import com.smartmobs.adaptation.AdaptationLevel;
import com.smartmobs.adaptation.Strategy;
import com.smartmobs.memory.ModAttachments;
import com.smartmobs.memory.PlayerBehavior;
import java.util.EnumSet;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.phys.Vec3;

/**
 * One reusable goal driving every strategy. Runs cheap checks only every CHECK_INTERVAL ticks,
 * and only uses information the mob legitimately has (its current target and line of sight).
 */
public class AdaptiveGoal extends Goal {
    private static final int CHECK_INTERVAL = 20;
    private static final int COOLDOWN = 100;

    private final Mob mob;
    private final Strategy strategy;
    private int cooldown;
    private int timeLeft;
    private Vec3 destination;

    public AdaptiveGoal(Mob mob, Strategy strategy) {
        this.mob = mob;
        this.strategy = strategy;
        setFlags(EnumSet.of(Flag.MOVE));
    }

    public Strategy strategy() { return strategy; }

    private int level() {
        LivingEntity t = mob.getTarget();
        if (!(t instanceof ServerPlayer p)) return 0;
        PlayerBehavior b = p.getData(ModAttachments.BEHAVIOR.get());
        return AdaptationLevel.of(b.get(strategy.stat));
    }

    @Override
    public boolean canUse() {
        if (cooldown > 0) { cooldown--; return false; }
        if (mob.tickCount % CHECK_INTERVAL != 0) return false;
        LivingEntity target = mob.getTarget();
        if (target == null || !target.isAlive()) return false;
        int lvl = level();
        if (lvl <= 0) return false;
        if (mob.getRandom().nextFloat() > 0.25f * lvl) return false;
        destination = computeDestination(target, lvl);
        return destination != null;
    }

    private Vec3 computeDestination(LivingEntity target, int lvl) {
        switch (strategy) {
            case SEEK_COVER:
                if (!mob.getSensing().hasLineOfSight(target)) return null;
                return DefaultRandomPos.getPosAway(mob_pathfinder(), 8, 4, target.position());
            case FLANK: {
                Vec3 toTarget = target.position().subtract(mob.position()).normalize();
                Vec3 side = new Vec3(-toTarget.z, 0, toTarget.x).scale(mob.getRandom().nextBoolean() ? 4 : -4);
                return target.position().add(side);
            }
            case CAUTIOUS:
                if (mob.getHealth() > mob.getMaxHealth() * 0.5f) return null;
                return DefaultRandomPos.getPosAway(mob_pathfinder(), 6, 3, target.position());
            case FIND_ALTERNATE_PATH:
                if (mob.getNavigation().isDone() || mob.getNavigation().getPath() != null && mob.getNavigation().getPath().canReach()) return null;
                return DefaultRandomPos.getPos(mob_pathfinder(), 10, 4);
            case CLIMB_TOWARD:
                if (target.getY() - mob.getY() < 2.0) return null;
                BlockPos p = target.blockPosition();
                return new Vec3(p.getX() + 0.5, p.getY(), p.getZ() + 0.5);
            default:
                return null;
        }
    }

    private net.minecraft.world.entity.PathfinderMob mob_pathfinder() {
        return (net.minecraft.world.entity.PathfinderMob) mob;
    }

    @Override
    public void start() {
        timeLeft = 40 + 10 * level();
        if (destination != null) mob.getNavigation().moveTo(destination.x, destination.y, destination.z, 1.0);
    }

    @Override
    public boolean canContinueToUse() {
        return timeLeft > 0 && mob.getTarget() != null && !mob.getNavigation().isDone();
    }

    @Override
    public void tick() { timeLeft--; }

    @Override
    public void stop() {
        cooldown = COOLDOWN;
        destination = null;
    }
}
