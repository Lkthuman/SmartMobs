package com.smartmobs;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SmartMobs - Adaptive Mob AI for Minecraft 1.21.1
 * Les mobs apprennent progressivement du comportement des joueurs
 */
@Mod(SmartMobs.MOD_ID)
public class SmartMobs {
    public static final String MOD_ID = "smartmobs";
    public static final String MOD_NAME = "SmartMobs";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public SmartMobs(IEventBus modEventBus) {
        ModLoadingContext.getInstance().registerConfig(ModConfig.Type.COMMON, SmartMobsConfig.SPEC);
        modEventBus.addListener(this::setup);
        NeoForge.EVENT_BUS.addListener(this::serverStarted);
        LOGGER.info("SmartMobs initialized!");
    }

    private void setup(FMLCommonSetupEvent event) {
        LOGGER.info("SmartMobs common setup");
    }

    private void serverStarted(net.neoforged.neoforge.event.server.ServerStartedEvent event) {
        LOGGER.info("SmartMobs server started");
    }
}
