package com.smartmobs.adaptation;

import com.smartmobs.memory.PlayerBehavior;

public enum Strategy {
    SEEK_COVER(PlayerBehavior.Stat.RANGED, "Seek Cover"),
    FLANK(PlayerBehavior.Stat.MELEE, "Flank"),
    FIND_ALTERNATE_PATH(PlayerBehavior.Stat.BUILDING, "Alternate Path"),
    CLIMB_TOWARD(PlayerBehavior.Stat.HIGH_GROUND, "Reach High Ground"),
    CAUTIOUS(PlayerBehavior.Stat.EASY_KILLS, "Cautious");

    public final PlayerBehavior.Stat stat;
    public final String label;

    Strategy(PlayerBehavior.Stat stat, String label) {
        this.stat = stat;
        this.label = label;
    }
}
