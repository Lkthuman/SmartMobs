package com.smartmobs;

import com.smartmobs.config.SmartMobsConfig;
import com.smartmobs.events.MobEvents;
import com.smartmobs.events.PlayerObservationEvents;
import com.smartmobs.commands.SmartMobsCommand;
import com.smartmobs.memory.ModAttachments;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(SmartMobs.MOD_ID)
public class SmartMobs {
    public static final String MOD_ID = "smartmobs";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public SmartMobs(IEventBus modBus, ModContainer container) {
        ModAttachments.REGISTER.register(modBus);
        container.registerConfig(ModConfig.Type.COMMON, SmartMobsConfig.SPEC);
        NeoForge.EVENT_BUS.register(new PlayerObservationEvents());
        NeoForge.EVENT_BUS.register(new MobEvents());
        NeoForge.EVENT_BUS.register(new SmartMobsCommand());
    }
}
