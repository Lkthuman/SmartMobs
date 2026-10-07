package com.smartmobs.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;
import com.smartmobs.ai.AdaptiveGoal;
import com.smartmobs.adaptation.MobMemory;
import com.smartmobs.config.SmartMobsConfig;
import com.smartmobs.memory.ModAttachments;
import java.util.List;

public class SmartMobsDebugCommand {
    
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("smartmobs")
                .then(Commands.literal("debug")
                    .executes(SmartMobsDebugCommand::executeDebug))
                .then(Commands.literal("stats")
                    .executes(SmartMobsDebugCommand::executeStats))
                .then(Commands.literal("reset")
                    .executes(SmartMobsDebugCommand::executeReset))
                .then(Commands.literal("reload")
                    .executes(SmartMobsDebugCommand::executeReload))
        );
    }
    
    private static int executeDebug(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        Player player = source.getPlayerOrException();
        
        if (!SmartMobsConfig.COMMON.debugMode.get()) {
            source.sendFailure(Component.literal("§cDebug mode is disabled in config"));
            return 0;
        }
        
        // Trouver le mob le plus proche
        ServerLevel level = source.getLevel();
        Mob nearestMob = level.getEntities(
            null,
            player.getBoundingBox().inflate(16),
            entity -> entity instanceof Mob && entity != player
        ).stream()
            .filter(e -> e instanceof Mob)
            .map(e -> (Mob) e)
            .min((a, b) -> Double.compare(a.distanceTo(player), b.distanceTo(player)))
            .orElse(null);
        
        if (nearestMob == null) {
            source.sendFailure(Component.literal("§cNo mobs found nearby"));
            return 0;
        }
        
        // Afficher les informations de debug
        String mobName = nearestMob.getEncodeId().toString();
        int distance = (int) nearestMob.distanceTo(player);
        
        source.sendSuccess(
            () -> Component.literal(
                "§b=== Smart Mobs Debug ===\n" +
                "§eMob: §f" + mobName + "\n" +
                "§eDistance: §f" + distance + " blocks\n" +
                "§eAdaptation Level: §f" + getMobAdaptationLevel(nearestMob) + "/3\n" +
                "§eLearning Progress: §f" + getMobLearningProgress(nearestMob) + "%\n" +
                "§eStrategy: §f" + getMobStrategy(nearestMob)
            ),
            false
        );
        
        return 1;
    }
    
    private static int executeStats(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        Player player = source.getPlayerOrException();
        
        try {
            var behavior = player.getData(ModAttachments.PLAYER_BEHAVIOR);
            if (behavior != null) {
                source.sendSuccess(
                    () -> Component.literal(
                        "§b=== Your Behavior Stats ===\n" +
                        "§eMelee: §f" + String.format("%.1f%%", behavior.getMeleeUsage() * 100) + "\n" +
                        "§eRanged: §f" + String.format("%.1f%%", behavior.getRangedUsage() * 100) + "\n" +
                        "§eBuilding: §f" + String.format("%.1f%%", behavior.getBuildingUsage() * 100) + "\n" +
                        "§eHigh Ground: §f" + String.format("%.1f%%", behavior.getHighGroundUsage() * 100)
                    ),
                    false
                );
            } else {
                source.sendFailure(Component.literal("§cNo behavior data found"));
            }
        } catch (Exception e) {
            source.sendFailure(Component.literal("§cError reading behavior: " + e.getMessage()));
        }
        
        return 1;
    }
    
    private static int executeReset(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        source.sendSuccess(
            () -> Component.literal("§eMob memory and behavior reset (not implemented yet)"),
            false
        );
        return 1;
    }
    
    private static int executeReload(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        source.sendSuccess(
            () -> Component.literal("§eConfig reloaded"),
            false
        );
        return 1;
    }
    
    private static int getMobAdaptationLevel(Mob mob) {
        // Placeholder - intégration avec AdaptiveGoal
        return 0;
    }
    
    private static int getMobLearningProgress(Mob mob) {
        // Placeholder - intégration avec AdaptiveGoal
        return 0;
    }
    
    private static String getMobStrategy(Mob mob) {
        // Placeholder - intégration avec AdaptiveGoal
        return "None";
    }
}
