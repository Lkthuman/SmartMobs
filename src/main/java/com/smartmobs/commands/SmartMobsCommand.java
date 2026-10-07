package com.smartmobs.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class SmartMobsCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("smartmobs")
                .then(Commands.literal("debug")
                        .executes(SmartMobsCommand::debug))
                .then(Commands.literal("stats")
                        .executes(SmartMobsCommand::stats))
                .then(Commands.literal("reload")
                        .executes(SmartMobsCommand::reload))
        );
    }

    private static int debug(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.literal("SmartMobs Debug Mode"), false);
        return 1;
    }

    private static int stats(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.literal("SmartMobs Statistics"), false);
        return 1;
    }

    private static int reload(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.literal("SmartMobs reloaded"), false);
        return 1;
    }
}
