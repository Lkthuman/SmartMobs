package com.smartmobs.adaptation;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;

public interface AdaptationStrategy {
    
    /**
     * Retourne le type de stratégie
     */
    StrategyType getType();
    
    /**
     * Retourne le niveau d'adaptation minimum requis pour cette stratégie
     */
    int getMinimumAdaptationLevel();
    
    /**
     * Retourne le cooldown en ticks avant de pouvoir réutiliser cette stratégie
     */
    int getCooldownTicks();
    
    /**
     * Vérifie si les conditions d'activation sont remplies
     */
    boolean canActivate(Mob mob, Player target);
    
    /**
     * Exécute la stratégie
     */
    void execute(Mob mob, Player target);
    
    /**
     * Retourne vrai si la stratégie doit rester active
     */
    boolean shouldContinue(Mob mob, Player target);
    
    /**
     * Appelé quand la stratégie se termine
     */
    void onComplete(Mob mob);
}
