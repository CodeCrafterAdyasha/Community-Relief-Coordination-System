package com.communityrelief.model;

/**
 * Resident requesting support with a configurable urgency priority.
 */
public class Resident extends User {
    private final int priorityLevel; // 1 (low) - 5 (critical)

    public Resident(String id, String name, String phone, String location, int priorityLevel) {
        super(id, name, phone, location);
        this.priorityLevel = Math.max(1, Math.min(priorityLevel, 5));
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }
}

