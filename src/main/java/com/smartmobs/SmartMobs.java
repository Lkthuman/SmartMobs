package com.smartmobs;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smartmobs.config.SmartMobsConfig;
import com.smartmobs.events.MobEventHandler;
import com.smartmobs.events.PlayerEventHandler;
import com.smartmobs.learning.LearningSystem;
import com.smartmobs.adaptation.AdaptationManager;

@Mod(SmartMobs.MOD_ID)
public class SmartMobs {
    public static final String MOD_ID = "smartmobs";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    private static LearningSystem learningSystem;
    private static AdaptationManager adaptationManager;

    public SmartMobs(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Smart Mobs mod initializing...");
        
        // Initialize systems
        learningSystem = new LearningSystem();
        adaptationManager = new AdaptationManager();
        
        // Register config
        modContainer.registerConfig(ModConfig.Type.COMMON, SmartMobsConfig.SPEC);
        
        // Register event handlers
        NeoForge.EVENT_BUS.register(PlayerEventHandler.class);
        NeoForge.EVENT_BUS.register(MobEventHandler.class);
        
        LOGGER.info("Smart Mobs mod loaded successfully!");
    }

    public static LearningSystem getLearningSystem() {
        return learningSystem;
    }

    public static AdaptationManager getAdaptationManager() {
        return adaptationManager;
    }
}