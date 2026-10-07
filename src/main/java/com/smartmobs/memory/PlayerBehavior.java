package com.smartmobs.memory;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Mth;

/** Normalized (0..1) observations about one player. Immutable-ish, tiny to serialize. */
public final class PlayerBehavior {
    public static final Codec<PlayerBehavior> CODEC = RecordCodecBuilder.create(i -> i.group(
            Codec.FLOAT.fieldOf("melee").forGetter(p -> p.melee),
            Codec.FLOAT.fieldOf("ranged").forGetter(p -> p.ranged),
            Codec.FLOAT.fieldOf("building").forGetter(p -> p.building),
            Codec.FLOAT.fieldOf("highGround").forGetter(p -> p.highGround),
            Codec.FLOAT.fieldOf("easyKills").forGetter(p -> p.easyKills)
    ).apply(i, PlayerBehavior::new));

    public enum Stat { MELEE, RANGED, BUILDING, HIGH_GROUND, EASY_KILLS }

    private float melee, ranged, building, highGround, easyKills;

    public PlayerBehavior() {}

    private PlayerBehavior(float melee, float ranged, float building, float highGround, float easyKills) {
        this.melee = clamp(melee);
        this.ranged = clamp(ranged);
        this.building = clamp(building);
        this.highGround = clamp(highGround);
        this.easyKills = clamp(easyKills);
    }

    private static float clamp(float v) { return Mth.clamp(Float.isNaN(v) ? 0f : v, 0f, 1f); }

    public float get(Stat s) {
        return switch (s) {
            case MELEE -> melee;
            case RANGED -> ranged;
            case BUILDING -> building;
            case HIGH_GROUND -> highGround;
            case EASY_KILLS -> easyKills;
        };
    }

    private void set(Stat s, float v) {
        v = clamp(v);
        switch (s) {
            case MELEE -> melee = v;
            case RANGED -> ranged = v;
            case BUILDING -> building = v;
            case HIGH_GROUND -> highGround = v;
            case EASY_KILLS -> easyKills = v;
        }
    }

    /** Slowly pushes a stat toward 1 (diminishing returns near the cap). */
    public void observe(Stat s, float amount) {
        float cur = get(s);
        set(s, cur + amount * (1f - cur));
    }

    /** Multiplicative decay applied to every stat. */
    public void decay(float factor) {
        for (Stat s : Stat.values()) set(s, get(s) * factor);
    }

    public void reset() {
        for (Stat s : Stat.values()) set(s, 0f);
    }

    public PlayerBehavior copy() {
        return new PlayerBehavior(melee, ranged, building, highGround, easyKills);
    }
}
