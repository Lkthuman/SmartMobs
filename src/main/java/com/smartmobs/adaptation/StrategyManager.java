package com.smartmobs.adaptation;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import com.smartmobs.adaptation.strategies.*;
import com.smartmobs.config.SmartMobsConfig;
import java.util.*;

public class StrategyManager {
    
    private static final List<AdaptationStrategy> ALL_STRATEGIES = List.of(
        new SeekCoverStrategy(),
        new AntiRangedStrategy(),
        new AntiHighGroundStrategy(),
        new FlankStrategy(),
        new SurroundStrategy(),
        new ClimbStrategy(),
        new RetreatStrategy(),
        new PathReevaluationStrategy(),
        new BuildUpStrategy()
    );
    
    private AdaptationStrategy currentStrategy = null;
    private int strategyCooldown = 0;
    
    public AdaptationStrategy selectStrategy(Mob mob, Player target, MobMemory memory, IntelligenceLevel intelligence) {
        if (strategyCooldown > 0) {
            strategyCooldown--;
            return currentStrategy;
        }
        
        int adaptLevel = memory.getAdaptationLevel();
        List<AdaptationStrategy> available = new ArrayList<>();
        
        for (AdaptationStrategy strategy : ALL_STRATEGIES) {
            if (strategy.getMinimumAdaptationLevel() <= adaptLevel && 
                strategy.canActivate(mob, target) &&
                !memory.isStrategyCooldown(strategy.getType(), strategy.getCooldownTicks())) {
                
                float penalty = memory.getStrategyFailurePenalty(strategy.getType());
                if (Math.random() < penalty) {
                    available.add(strategy);
                }
            }
        }
        
        if (!available.isEmpty()) {
            AdaptationStrategy newStrategy = available.get((int)(Math.random() * available.size()));
            currentStrategy = newStrategy;
            memory.setCurrentStrategy(newStrategy.getType());
            strategyCooldown = newStrategy.getCooldownTicks();
            return newStrategy;
        }
        
        return currentStrategy;
    }
    
    public AdaptationStrategy getCurrentStrategy() {
        return currentStrategy;
    }
    
    public void clearStrategy() {
        if (currentStrategy != null) {
            currentStrategy = null;
        }
    }
}
