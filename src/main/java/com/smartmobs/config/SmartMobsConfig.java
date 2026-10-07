package com.smartmobs.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class SmartMobsConfig {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.IntValue LEARNING_SPEED;
    public static final ModConfigSpec.IntValue ADAPTATION_CHANCE;
    public static final ModConfigSpec.IntValue MAX_ADAPTATION_LEVEL;
    public static final ModConfigSpec.IntValue STRATEGY_COOLDOWN;
    public static final ModConfigSpec.BooleanValue DEBUG_MODE;

    static {
        BUILDER.push("smart_mobs");
        
        LEARNING_SPEED = BUILDER
            .comment("How fast mobs learn from player behavior (1-10)")
            .defineInRange("learning_speed", 5, 1, 10);
        
        ADAPTATION_CHANCE = BUILDER
            .comment("Chance for mob to adapt strategy (1-100)")
            .defineInRange("adaptation_chance", 30, 1, 100);
        
        MAX_ADAPTATION_LEVEL = BUILDER
            .comment("Maximum adaptation level for mobs (1-5)")
            .defineInRange("max_adaptation_level", 3, 1, 5);
        
        STRATEGY_COOLDOWN = BUILDER
            .comment("Cooldown between strategy changes in ticks")
            .defineInRange("strategy_cooldown", 200, 20, 1000);
        
        DEBUG_MODE = BUILDER
            .comment("Enable debug mode for detailed logging")
            .define("debug_mode", false);
        
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}