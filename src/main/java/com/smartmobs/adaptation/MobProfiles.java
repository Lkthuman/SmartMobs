package com.smartmobs.adaptation;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;

/** Declares which strategies each mob type may use. Extend by adding entries. */
public final class MobProfiles {
    private static final Set<Strategy> MELEE_MOB = EnumSet.of(Strategy.FLANK, Strategy.FIND_ALTERNATE_PATH, Strategy.CLIMB_TOWARD, Strategy.CAUTIOUS);
    private static final Set<Strategy> RANGED_MOB = EnumSet.of(Strategy.SEEK_COVER, Strategy.CAUTIOUS);

    private static final Map<EntityType<?>, Set<Strategy>> PROFILES = Map.ofEntries(
            Map.entry(EntityType.ZOMBIE, MELEE_MOB),
            Map.entry(EntityType.HUSK, MELEE_MOB),
            Map.entry(EntityType.DROWNED, MELEE_MOB),
            Map.entry(EntityType.VINDICATOR, MELEE_MOB),
            Map.entry(EntityType.SPIDER, EnumSet.of(Strategy.FLANK, Strategy.CLIMB_TOWARD, Strategy.FIND_ALTERNATE_PATH)),
            Map.entry(EntityType.CREEPER, EnumSet.of(Strategy.FIND_ALTERNATE_PATH, Strategy.CAUTIOUS)),
            Map.entry(EntityType.ENDERMAN, EnumSet.of(Strategy.FIND_ALTERNATE_PATH, Strategy.CAUTIOUS)),
            Map.entry(EntityType.SKELETON, RANGED_MOB),
            Map.entry(EntityType.STRAY, RANGED_MOB),
            Map.entry(EntityType.PILLAGER, RANGED_MOB),
            Map.entry(EntityType.WITCH, RANGED_MOB)
    );

    private MobProfiles() {}

    public static Set<Strategy> strategiesFor(Mob mob) {
        return PROFILES.getOrDefault(mob.getType(), Set.of());
    }
}
