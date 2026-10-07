package com.smartmobs;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(SmartMobs.MODID)
@EventBusSubscriber(modid = SmartMobs.MODID, bus = EventBusSubscriber.Bus.MOD)
public class SmartMobs {
    
    public static final String MODID = "smartmobs";
    
    public SmartMobs() {
        // Enregistrement des événements
        NeoForge.EVENT_BUS.register(this);
    }
}
