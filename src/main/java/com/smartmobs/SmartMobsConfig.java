package com.smartmobs;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class SmartMobsConfig {
    public static final ModConfigSpec SPEC;
    public static final Common COMMON;

    static {
        Pair<Common, ModConfigSpec> pair = new ModConfigSpec.Builder()
                .configure(Common::new);
        COMMON = pair.getLeft();
        SPEC = pair.getRight();
    }

    public static class Common {
        public final ModConfigSpec.IntValue adaptationLevel;
        public final ModConfigSpec.BooleanValue enableDebug;
        public final ModConfigSpec.DoubleValue learningSpeed;

        public Common(ModConfigSpec.Builder builder) {
            builder.push("smartmobs");

            adaptationLevel = builder
                    .comment("Adaptation level for mobs (0-3)")
                    .defineInRange("adaptation_level", 1, 0, 3);

            enableDebug = builder
                    .comment("Enable debug mode")
                    .define("enable_debug", false);

            learningSpeed = builder
                    .comment("Learning speed multiplier")
                    .defineInRange("learning_speed", 1.0, 0.5, 2.0);

            builder.pop();
        }
    }
}
