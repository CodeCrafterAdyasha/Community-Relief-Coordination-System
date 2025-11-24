package com.communityrelief.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Volunteer able to take requests based on available skills.
 */
public class Volunteer extends User {
    private final Set<String> skills = new HashSet<>();
    private final int weeklyCapacityHours;
    private boolean available;

    public Volunteer(String id, String name, String phone, String location,
                     Set<String> skills, int weeklyCapacityHours) {
        super(id, name, phone, location);
        if (skills != null) {
            skills.stream().filter(Objects::nonNull).map(String::toLowerCase).forEach(this.skills::add);
        }
        this.weeklyCapacityHours = weeklyCapacityHours;
        this.available = true;
    }

    public Set<String> getSkills() {
        return Collections.unmodifiableSet(skills);
    }

    public boolean hasSkill(String skill) {
        return skill != null && skills.contains(skill.toLowerCase());
    }

    public int getWeeklyCapacityHours() {
        return weeklyCapacityHours;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

