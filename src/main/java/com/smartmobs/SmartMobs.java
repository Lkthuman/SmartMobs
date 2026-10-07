package com.smartmobs;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmartMobs {
    public static final String MOD_ID = "smartmobs";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public SmartMobs(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("SmartMobs mod initializing...");
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, SmartMobsConfig.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Common setup for SmartMobs");
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ServerEvents {
        @net.neoforged.neoforge.api.distmarker.OnlyIn(Dist.DEDICATED_SERVER)
        public static void onServerStarting(ServerStartingEvent event) {
            LOGGER.info("SmartMobs server starting");
        }
    }
}
