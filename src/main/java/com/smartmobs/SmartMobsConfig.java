package com.smartmobs;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class SmartMobsConfig {
    public static class Common {
        public final ModConfigSpec.BooleanValue enabled;
        public final ModConfigSpec.IntValue debugLevel;
        public final ModConfigSpec.EnumValue<LearningDifficulty> learningDifficulty;
        public final ModConfigSpec.IntValue adaptationUpdateFrequency;

        public Common(ModConfigSpec.Builder builder) {
            builder.push("general");
            
            enabled = builder
                    .comment("Enable SmartMobs learning system")
                    .define("enabled", true);
            
            debugLevel = builder
                    .comment("Debug level (0-2)")
                    .defineInRange("debugLevel", 0, 0, 2);
            
            learningDifficulty = builder
                    .comment("Learning difficulty level")
                    .defineEnum("learningDifficulty", LearningDifficulty.NORMAL);
            
            adaptationUpdateFrequency = builder
                    .comment("Ticks between adaptation updates")
                    .defineInRange("adaptationUpdateFrequency", 20, 5, 100);
            
            builder.pop();
        }
    }

    public static final ModConfigSpec SPEC;
    public static final Common COMMON;

    static {
        Pair<Common, ModConfigSpec> pair = new ModConfigSpec.Builder()
                .configure(Common::new);
        SPEC = pair.getRight();
        COMMON = pair.getLeft();
    }

    public enum LearningDifficulty {
        EASY,
        NORMAL,
        HARD,
        VERY_HARD
    }
}
