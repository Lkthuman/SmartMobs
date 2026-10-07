package com.smartmobs.learning;

public class PlayerBehaviorData {
    private float meleeUsage = 0f;
    private float rangedUsage = 0f;
    private float buildingUsage = 0f;
    private float fleeingUsage = 0f;
    private int totalInteractions = 0;

    public void recordAction(PlayerAction action) {
        totalInteractions++;
        switch (action) {
            case MELEE_ATTACK:
                meleeUsage += 1f;
                break;
            case RANGED_ATTACK:
                rangedUsage += 1f;
                break;
            case BUILDING:
                buildingUsage += 1f;
                break;
            case FLEEING:
                fleeingUsage += 1f;
                break;
        }
    }

    public float getMeleePercentage() {
        return totalInteractions > 0 ? meleeUsage / totalInteractions : 0f;
    }

    public float getRangedPercentage() {
        return totalInteractions > 0 ? rangedUsage / totalInteractions : 0f;
    }

    public float getBuildingPercentage() {
        return totalInteractions > 0 ? buildingUsage / totalInteractions : 0f;
    }

    public float getFleeingPercentage() {
        return totalInteractions > 0 ? fleeingUsage / totalInteractions : 0f;
    }
}
