package com.communityrelief.data;

import com.communityrelief.model.AssistanceRequest;
import com.communityrelief.model.Resident;
import com.communityrelief.model.Volunteer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Thread-safe in-memory data store adequate for demos and tests.
 */
public class InMemoryDatabase {
    private final Map<String, Volunteer> volunteers = new ConcurrentHashMap<>();
    private final Map<String, Resident> residents = new ConcurrentHashMap<>();
    private final Map<String, AssistanceRequest> requests = new ConcurrentHashMap<>();
    private final AtomicInteger volunteerCounter = new AtomicInteger(100);
    private final AtomicInteger residentCounter = new AtomicInteger(500);
    private final AtomicInteger requestCounter = new AtomicInteger(900);

    public String nextVolunteerId() {
        return "VOL-" + volunteerCounter.incrementAndGet();
    }

    public String nextResidentId() {
        return "RES-" + residentCounter.incrementAndGet();
    }

    public String nextRequestId() {
        return "REQ-" + requestCounter.incrementAndGet();
    }

    public void saveVolunteer(Volunteer volunteer) {
        volunteers.put(volunteer.getId(), volunteer);
    }

    public void saveResident(Resident resident) {
        residents.put(resident.getId(), resident);
    }

    public void saveRequest(AssistanceRequest request) {
        requests.put(request.getId(), request);
    }

    public Optional<Volunteer> findVolunteer(String id) {
        return Optional.ofNullable(volunteers.get(id));
    }

    public Optional<Resident> findResident(String id) {
        return Optional.ofNullable(residents.get(id));
    }

    public Optional<AssistanceRequest> findRequest(String id) {
        return Optional.ofNullable(requests.get(id));
    }

    public Collection<Volunteer> allVolunteers() {
        return Collections.unmodifiableCollection(new ArrayList<>(volunteers.values()));
    }

    public Collection<Resident> allResidents() {
        return Collections.unmodifiableCollection(new ArrayList<>(residents.values()));
    }

    public Collection<AssistanceRequest> allRequests() {
        return Collections.unmodifiableCollection(new ArrayList<>(requests.values()));
    }
}

