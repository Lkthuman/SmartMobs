package com.smartmobs.adaptation.strategies;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;

public class AntiRangedStrategy implements AdaptationStrategy {

    @Override
    public boolean canActivate(Mob mob, Player player) {
        return mob.distanceTo(player) > 15;
    }

    @Override
    public void execute(Mob mob, Player player) {
        // Rush towards player to minimize ranged damage
        mob.getNavigation().moveTo(player, 1.5);
    }

    @Override
    public String getName() {
        return "ANTI_RANGED";
    }

    @Override
    public int getRequiredLevel() {
        return 1;
    }

    @Override
    public long getCooldown() {
        return 150;
    }
}