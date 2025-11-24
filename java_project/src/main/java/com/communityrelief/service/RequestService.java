package com.communityrelief.service;

import com.communityrelief.data.InMemoryDatabase;
import com.communityrelief.model.AssistanceRequest;
import com.communityrelief.model.RequestStatus;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Orchestrates lifecycle of assistance requests.
 */
public class RequestService {
    private final InMemoryDatabase database;

    public RequestService(InMemoryDatabase database) {
        this.database = database;
    }

    public AssistanceRequest createRequest(String residentId, String description,
                                           String category, int urgency) {
        String id = database.nextRequestId();
        AssistanceRequest request = new AssistanceRequest(id, residentId, description, category, urgency);
        database.saveRequest(request);
        return request;
    }

    public Optional<AssistanceRequest> findRequest(String id) {
        return database.findRequest(id);
    }

    public Collection<AssistanceRequest> listRequests() {
        return database.allRequests();
    }

    public List<AssistanceRequest> listOpenRequests() {
        return database.allRequests().stream()
                .filter(r -> r.getStatus() == RequestStatus.NEW || r.getStatus() == RequestStatus.SCHEDULED)
                .sorted(Comparator.comparingInt(AssistanceRequest::getUrgency).reversed())
                .collect(Collectors.toList());
    }

    public void updateStatus(String requestId, RequestStatus status) {
        database.findRequest(requestId).ifPresent(r -> r.setStatus(status));
    }
}

