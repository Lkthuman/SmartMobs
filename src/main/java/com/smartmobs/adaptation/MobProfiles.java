package com.smartmobs.adaptation;

import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.EntityType;
import java.util.*;

public class MobProfiles {
    
    public static final Set<Class<?>> SUPPORTED_MOBS = Set.of(
        Zombie.class,
        Skeleton.class,
        Creeper.class,
        Spider.class,
        Enderman.class,
        Drowned.class,
        Husk.class,
        Stray.class,
        Pillager.class,
        Vindicator.class,
        Witch.class
    );
    
    public static boolean isSupportedMob(Object mob) {
        return SUPPORTED_MOBS.stream().anyMatch(clazz -> clazz.isInstance(mob));
    }
}
