package com.smartmobs.events;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.api.distmarker.OnlyIn;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import com.smartmobs.SmartMobs;
import com.smartmobs.commands.SmartMobsDebugCommand;

@EventBusSubscriber(modid = SmartMobs.MODID, bus = EventBusSubscriber.Bus.GAME)
public class CommandRegistrationEvent {
    
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        SmartMobsDebugCommand.register(event.getDispatcher());
    }
}
