package com.smartmobs.adaptation;

import com.smartmobs.config.SmartMobsConfig;

public final class AdaptationLevel {
    private AdaptationLevel() {}

    /** Maps a 0..1 stat to a level 0..3, scaled by config difficulty and capped by max level. */
    public static int of(float stat) {
        float v = (float) (stat * SmartMobsConfig.difficulty());
        int level = v >= 0.75f ? 3 : v >= 0.5f ? 2 : v >= 0.25f ? 1 : 0;
        return Math.min(level, SmartMobsConfig.maxLevel());
    }
}
