package com.smartmobs.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class SmartMobsConfig {
    
    public static class CommonConfig {
        public final ModConfigSpec.BooleanValue enabled;
        public final ModConfigSpec.IntValue learningSpeed;
        public final ModConfigSpec.IntValue forgetSpeed;
        public final ModConfigSpec.IntValue maxAdaptationLevel;
        public final ModConfigSpec.IntValue observationRange;
        public final ModConfigSpec.IntValue adaptationDifficulty;
        public final ModConfigSpec.BooleanValue persistentMemory;
        public final ModConfigSpec.BooleanValue debugMode;
        public final ModConfigSpec.IntValue adaptationChance;
        public final ModConfigSpec.IntValue strategyCooldown;
        public final ModConfigSpec.IntValue observationTime;
        
        CommonConfig(ModConfigSpec.Builder builder) {
            builder.comment("Smart Mobs Configuration");
            
            enabled = builder
                .comment("Enable Smart Mobs system")
                .define("enabled", true);
            
            learningSpeed = builder
                .comment("Speed of learning (1-10)")
                .defineInRange("learningSpeed", 3, 1, 10);
            
            forgetSpeed = builder
                .comment("Speed of forgetting player behavior (1-10)")
                .defineInRange("forgetSpeed", 2, 1, 10);
            
            maxAdaptationLevel = builder
                .comment("Maximum adaptation level (0-3)")
                .defineInRange("maxAdaptationLevel", 3, 0, 3);
            
            observationRange = builder
                .comment("Range in blocks for mob observation")
                .defineInRange("observationRange", 32, 8, 64);
            
            adaptationDifficulty = builder
                .comment("Difficulty of adaptations (1-5, higher = harder)")
                .defineInRange("adaptationDifficulty", 3, 1, 5);
            
            persistentMemory = builder
                .comment("Should mob memory persist between sessions")
                .define("persistentMemory", true);
            
            debugMode = builder
                .comment("Enable debug mode")
                .define("debugMode", false);
            
            adaptationChance = builder
                .comment("Chance of adaptation in percentage (0-100)")
                .defineInRange("adaptationChance", 70, 0, 100);
            
            strategyCooldown = builder
                .comment("Cooldown between strategy changes in ticks")
                .defineInRange("strategyCooldown", 200, 50, 1000);
            
            observationTime = builder
                .comment("Time to observe before adapting in ticks")
                .defineInRange("observationTime", 100, 20, 500);
        }
    }
    
    public static final ModConfigSpec COMMON_SPEC;
    public static final CommonConfig COMMON;
    
    static {
        var pair = new ModConfigSpec.Builder().configure(CommonConfig::new);
        COMMON_SPEC = pair.getRight();
        COMMON = pair.getLeft();
    }
}
