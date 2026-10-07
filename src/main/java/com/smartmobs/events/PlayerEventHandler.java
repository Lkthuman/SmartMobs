package com.smartmobs.events;

import com.smartmobs.learning.LearningSystem;
import com.smartmobs.learning.PlayerAction;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingAttackEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.minecraft.world.entity.player.Player;

@Mod.EventBusSubscriber(modid = "smartmobs", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEventHandler {
    
    @SubscribeEvent
    public static void onPlayerAttack(LivingAttackEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (event.getEntity() instanceof net.minecraft.world.entity.Monster) {
                // Record player action
                LearningSystem.recordPlayerAction(player, PlayerAction.MELEE_ATTACK);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        LearningSystem.cleanup(event.getEntity());
    }
}
