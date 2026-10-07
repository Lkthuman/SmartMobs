package com.smartmobs.learning;

/** Mutable per-mob learning state. Kept small and entity-reference free. */
public final class MobLearningState {
    public LearningPhase phase = LearningPhase.IDLE;
    public int phaseTicksLeft;
    public int unreachableAttempts;
    public int strategyCooldown;
    public StrategyType current;
    public String action = "NONE";
    public int blocksCarried;
    public final FailureMemory memory = new FailureMemory();

    public void enter(LearningPhase next, int ticks) {
        this.phase = next;
        this.phaseTicksLeft = ticks;
    }

    /** Learning progress for debug bars, 0..1. */
    public double progress(int attemptsNeeded) {
        return Math.min(1.0, unreachableAttempts / (double) Math.max(1, attemptsNeeded));
    }
}
