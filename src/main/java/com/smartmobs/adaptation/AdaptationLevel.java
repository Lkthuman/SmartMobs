package com.smartmobs.adaptation;

public enum AdaptationLevel {
    NONE(0),
    LOW(1),
    MEDIUM(2),
    HIGH(3);
    
    private final int level;
    
    AdaptationLevel(int level) {
        this.level = level;
    }
    
    public int getLevel() {
        return level;
    }
    
    public static AdaptationLevel fromLevel(int level) {
        return switch(level) {
            case 0 -> NONE;
            case 1 -> LOW;
            case 2 -> MEDIUM;
            case 3 -> HIGH;
            default -> NONE;
        };
    }
}
