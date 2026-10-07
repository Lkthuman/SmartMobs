package com.smartmobs.adaptation;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;

public class AdaptationManager {
    private static final Map<LivingEntity, MobAdaptation> mobAdaptations = new HashMap<>();

    public static MobAdaptation getMobAdaptation(LivingEntity mob) {
        return mobAdaptations.computeIfAbsent(mob, m -> new MobAdaptation(m));
    }

    public static void updateMobAdaptation(LivingEntity mob, Player player) {
        MobAdaptation adaptation = getMobAdaptation(mob);
        adaptation.update(player);
    }

    public static void cleanup(LivingEntity mob) {
        mobAdaptations.remove(mob);
    }
}
