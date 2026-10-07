package com.smartmobs.config;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import net.neoforged.neoforge.common.ModConfigSpec;

public final class SmartMobsConfig {
    public static final ModConfigSpec SPEC;

    private static final ModConfigSpec.BooleanValue ENABLED;
    private static final ModConfigSpec.DoubleValue LEARNING_SPEED;
    private static final ModConfigSpec.DoubleValue FORGETTING_SPEED;
    private static final ModConfigSpec.IntValue MAX_LEVEL;
    private static final ModConfigSpec.ConfigValue<List<? extends String>> MOBS;
    private static final ModConfigSpec.DoubleValue DIFFICULTY;
    private static final ModConfigSpec.IntValue OBSERVATION_RANGE;
    private static final ModConfigSpec.BooleanValue PERSISTENT;
    private static final ModConfigSpec.BooleanValue DEBUG;

    static {
        ModConfigSpec.Builder b = new ModConfigSpec.Builder();
        ENABLED = b.comment("Enable the learning system").define("enabled", true);
        LEARNING_SPEED = b.comment("Learning speed multiplier").defineInRange("learningSpeed", 1.0, 0.1, 5.0);
        FORGETTING_SPEED = b.comment("Forgetting speed multiplier (decay per minute)").defineInRange("forgettingSpeed", 1.0, 0.0, 5.0);
        MAX_LEVEL = b.comment("Maximum adaptation level (0-3)").defineInRange("maxAdaptationLevel", 3, 0, 3);
        MOBS = b.comment("Entity ids affected").defineListAllowEmpty("affectedMobs",
                List.of("minecraft:zombie", "minecraft:skeleton", "minecraft:creeper", "minecraft:spider",
                        "minecraft:enderman", "minecraft:drowned", "minecraft:husk", "minecraft:stray",
                        "minecraft:pillager", "minecraft:vindicator", "minecraft:witch"),
                () -> "minecraft:zombie", o -> o instanceof String);
        DIFFICULTY = b.comment("Adaptation strength multiplier").defineInRange("adaptationDifficulty", 1.0, 0.0, 2.0);
        OBSERVATION_RANGE = b.comment("Max distance (blocks) a mob can observe a player to learn from").defineInRange("observationRange", 32, 8, 128);
        PERSISTENT = b.comment("Persist memory between sessions").define("persistentMemory", true);
        DEBUG = b.comment("Debug mode").define("debug", false);
        SPEC = b.build();
    }

    private SmartMobsConfig() {}

    public static boolean enabled() { return ENABLED.get(); }
    public static double learningSpeed() { return LEARNING_SPEED.get(); }
    public static double forgettingSpeed() { return FORGETTING_SPEED.get(); }
    public static int maxLevel() { return MAX_LEVEL.get(); }
    public static double difficulty() { return DIFFICULTY.get(); }
    public static int observationRange() { return OBSERVATION_RANGE.get(); }
    public static boolean persistent() { return PERSISTENT.get(); }
    public static boolean debug() { return DEBUG.get(); }

    public static Set<String> affectedMobs() {
        return MOBS.get().stream().map(Object::toString).collect(Collectors.toSet());
    }
}
