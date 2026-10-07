package com.smartmobs.adaptation.strategies;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;

public interface AdaptationStrategy {
    
    boolean canActivate(Mob mob, Player player);
    
    void execute(Mob mob, Player player);
    
    String getName();
    
    int getRequiredLevel();
    
    long getCooldown();
}