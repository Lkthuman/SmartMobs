package com.smartmobs;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod("smartmobs")
public class SmartMobs {
    private static final Logger LOGGER = LoggerFactory.getLogger(SmartMobs.class);
    
    public SmartMobs(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("SmartMobs mod loaded!");
        NeoForge.EVENT_BUS.register(this);
    }
}