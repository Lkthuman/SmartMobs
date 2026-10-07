package com.smartmobs.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class SmartMobsCommand {
    
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("smartmobs")
            .then(Commands.literal("debug")
                .executes(ctx -> executeDebug(ctx.getSource())))
            .then(Commands.literal("stats")
                .executes(ctx -> executeStats(ctx.getSource())))
            .then(Commands.literal("reload")
                .executes(ctx -> executeReload(ctx.getSource())))
        );
    }

    private static int executeDebug(CommandSourceStack source) {
        source.sendSuccess(() -> Component.literal("§6Smart Mobs Debug Enabled"), true);
        return 1;
    }

    private static int executeStats(CommandSourceStack source) {
        source.sendSuccess(() -> Component.literal("§6Smart Mobs Stats: System operational"), true);
        return 1;
    }

    private static int executeReload(CommandSourceStack source) {
        source.sendSuccess(() -> Component.literal("§6Smart Mobs Configuration Reloaded"), true);
        return 1;
    }
}