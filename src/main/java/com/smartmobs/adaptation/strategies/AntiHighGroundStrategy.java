package com.smartmobs.adaptation.strategies;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;

public class AntiHighGroundStrategy implements AdaptationStrategy {

    @Override
    public boolean canActivate(Mob mob, Player player) {
        return player.getY() > mob.getY() + 2 && mob.distanceTo(player) < 30;
    }

    @Override
    public void execute(Mob mob, Player player) {
        // Move towards the player while avoiding obstacles
        mob.getNavigation().moveTo(player, 1.2);
    }

    @Override
    public String getName() {
        return "ANTI_HIGH_GROUND";
    }

    @Override
    public int getRequiredLevel() {
        return 2;
    }

    @Override
    public long getCooldown() {
        return 200;
    }
}