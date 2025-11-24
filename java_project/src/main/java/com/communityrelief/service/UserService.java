package com.communityrelief.service;

import com.communityrelief.data.InMemoryDatabase;
import com.communityrelief.model.Resident;
import com.communityrelief.model.Volunteer;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles creation and lookup of residents and volunteers.
 */
public class UserService {
    private final InMemoryDatabase database;

    public UserService(InMemoryDatabase database) {
        this.database = database;
    }

    public Volunteer registerVolunteer(String name, String phone, String location,
                                       Set<String> skills, int weeklyCapacityHours) {
        String id = database.nextVolunteerId();
        Volunteer volunteer = new Volunteer(id, name, phone, location, skills, weeklyCapacityHours);
        database.saveVolunteer(volunteer);
        return volunteer;
    }

    public Resident registerResident(String name, String phone, String location, int priorityLevel) {
        String id = database.nextResidentId();
        Resident resident = new Resident(id, name, phone, location, priorityLevel);
        database.saveResident(resident);
        return resident;
    }

    public Optional<Volunteer> findVolunteer(String id) {
        return database.findVolunteer(id);
    }

    public Optional<Resident> findResident(String id) {
        return database.findResident(id);
    }

    public Collection<Volunteer> listVolunteers() {
        return database.allVolunteers();
    }

    public Collection<Resident> listResidents() {
        return database.allResidents();
    }

    public Collection<Volunteer> findVolunteersWithSkill(String skill) {
        if (skill == null || skill.isBlank()) {
            return Collections.emptyList();
        }
        String normalized = skill.toLowerCase();
        return database.allVolunteers().stream()
                .filter(v -> v.hasSkill(normalized))
                .collect(Collectors.toList());
    }
}

