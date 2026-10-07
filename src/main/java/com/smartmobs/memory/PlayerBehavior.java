package com.smartmobs.memory;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.nbt.CompoundTag;

public class PlayerBehavior {
    
    private float meleeUsage = 0.0f;
    private float rangedUsage = 0.0f;
    private float projectileUsage = 0.0f;
    private float buildingUsage = 0.0f;
    private float highGroundUsage = 0.0f;
    private float easyKillsUsage = 0.0f;
    private float preferredCombatDistance = 3.0f;
    
    public static final Codec<PlayerBehavior> CODEC = RecordCodecBuilder.create(instance =>
        instance.group(
            Codec.FLOAT.fieldOf("melee").forGetter(PlayerBehavior::getMeleeUsage),
            Codec.FLOAT.fieldOf("ranged").forGetter(PlayerBehavior::getRangedUsage),
            Codec.FLOAT.fieldOf("projectile").forGetter(PlayerBehavior::getProjectileUsage),
            Codec.FLOAT.fieldOf("building").forGetter(PlayerBehavior::getBuildingUsage),
            Codec.FLOAT.fieldOf("highGround").forGetter(PlayerBehavior::getHighGroundUsage),
            Codec.FLOAT.fieldOf("easyKills").forGetter(PlayerBehavior::getEasyKillsUsage)
        ).apply(instance, (m, r, p, b, h, e) -> {
            PlayerBehavior pb = new PlayerBehavior();
            pb.meleeUsage = m;
            pb.rangedUsage = r;
            pb.projectileUsage = p;
            pb.buildingUsage = b;
            pb.highGroundUsage = h;
            pb.easyKillsUsage = e;
            return pb;
        })
    );
    
    public void increaseMeleeUsage() {
        meleeUsage = Math.min(1.0f, meleeUsage + 0.1f);
    }
    
    public void increaseRangedUsage() {
        rangedUsage = Math.min(1.0f, rangedUsage + 0.1f);
    }
    
    public void increaseProjectileUsage() {
        projectileUsage = Math.min(1.0f, projectileUsage + 0.1f);
    }
    
    public void increaseBuildingUsage() {
        buildingUsage = Math.min(1.0f, buildingUsage + 0.1f);
    }
    
    public void increaseHighGroundUsage() {
        highGroundUsage = Math.min(1.0f, highGroundUsage + 0.1f);
    }
    
    public void increaseEasyKillsUsage() {
        easyKillsUsage = Math.min(1.0f, easyKillsUsage + 0.1f);
    }
    
    public void decay(float speed) {
        float decay = 0.01f * speed;
        meleeUsage = Math.max(0.0f, meleeUsage - decay);
        rangedUsage = Math.max(0.0f, rangedUsage - decay);
        projectileUsage = Math.max(0.0f, projectileUsage - decay);
        buildingUsage = Math.max(0.0f, buildingUsage - decay);
        highGroundUsage = Math.max(0.0f, highGroundUsage - decay);
        easyKillsUsage = Math.max(0.0f, easyKillsUsage - decay);
    }
    
    public float getMeleeUsage() { return meleeUsage; }
    public float getRangedUsage() { return rangedUsage; }
    public float getProjectileUsage() { return projectileUsage; }
    public float getBuildingUsage() { return buildingUsage; }
    public float getHighGroundUsage() { return highGroundUsage; }
    public float getEasyKillsUsage() { return easyKillsUsage; }
    public float getPreferredCombatDistance() { return preferredCombatDistance; }
    
    public void setPreferredCombatDistance(float distance) {
        this.preferredCombatDistance = Math.max(0.5f, distance);
    }
}
