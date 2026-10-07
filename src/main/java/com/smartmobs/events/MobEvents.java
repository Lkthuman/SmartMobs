package com.smartmobs.events;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.minecraft.world.entity.monster.Monster;
import com.smartmobs.SmartMobs;

@EventBusSubscriber(modid = SmartMobs.MODID, bus = EventBusSubscriber.Bus.GAME)
public class MobEvents {
    
    @SubscribeEvent
    public static void onMobJoinLevel(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof Monster)) {
            return;
        }
        
        // Les goals sont maintenant enregistrés dans MobGoalEvents
    }
}
