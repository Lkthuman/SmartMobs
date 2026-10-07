package com.smartmobs.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.smartmobs.adaptation.AdaptationLevel;
import com.smartmobs.adaptation.Strategy;
import com.smartmobs.config.SmartMobsConfig;
import com.smartmobs.memory.ModAttachments;
import com.smartmobs.memory.PlayerBehavior;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public class SmartMobsCommand {
    @SubscribeEvent
    public void register(RegisterCommandsEvent e) {
        CommandDispatcher<CommandSourceStack> d = e.getDispatcher();
        d.register(Commands.literal("smartmobs")
                .requires(s -> s.hasPermission(2))
                .then(Commands.literal("stats").executes(c -> stats(c.getSource())))
                .then(Commands.literal("debug").executes(c -> debug(c.getSource())))
                .then(Commands.literal("reset").executes(c -> reset(c.getSource()))));
    }

    private static ServerPlayer player(CommandSourceStack s) {
        return s.getPlayer();
    }

    private static int stats(CommandSourceStack s) {
        ServerPlayer p = player(s);
        if (p == null) return 0;
        PlayerBehavior b = p.getData(ModAttachments.BEHAVIOR.get());
        s.sendSuccess(() -> Component.literal("Smart Mobs - behavior"), false);
        for (Strategy st : Strategy.values()) {
            float v = b.get(st.stat);
            s.sendSuccess(() -> Component.literal(st.stat.name() + ": " + Math.round(v * 100) + "% -> level " + AdaptationLevel.of(v) + " (" + st.label + ")"), false);
        }
        return 1;
    }

    private static int debug(CommandSourceStack s) {
        s.sendSuccess(() -> Component.literal("Debug config value: " + SmartMobsConfig.debug() + " (edit smartmobs-common.toml)"), false);
        return stats(s);
    }

    private static int reset(CommandSourceStack s) {
        ServerPlayer p = player(s);
        if (p == null) return 0;
        PlayerBehavior b = p.getData(ModAttachments.BEHAVIOR.get());
        b.reset();
        p.setData(ModAttachments.BEHAVIOR.get(), b);
        s.sendSuccess(() -> Component.literal("Smart Mobs memory reset"), false);
        return 1;
    }
}
