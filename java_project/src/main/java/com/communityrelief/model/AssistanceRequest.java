package com.communityrelief.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a single community support request raised by a resident.
 */
public class AssistanceRequest {
    private final String id;
    private final String residentId;
    private final String description;
    private final String category;
    private final int urgency; // 1-5
    private final LocalDateTime createdAt;

    private RequestStatus status;
    private String assignedVolunteerId;

    public AssistanceRequest(String id,
                             String residentId,
                             String description,
                             String category,
                             int urgency) {
        this.id = Objects.requireNonNull(id, "id");
        this.residentId = Objects.requireNonNull(residentId, "residentId");
        this.description = Objects.requireNonNull(description, "description");
        this.category = Objects.requireNonNull(category, "category").toLowerCase();
        this.urgency = Math.max(1, Math.min(urgency, 5));
        this.createdAt = LocalDateTime.now();
        this.status = RequestStatus.NEW;
    }

    public String getId() {
        return id;
    }

    public String getResidentId() {
        return residentId;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public int getUrgency() {
        return urgency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getAssignedVolunteerId() {
        return assignedVolunteerId;
    }

    public void setAssignedVolunteerId(String assignedVolunteerId) {
        this.assignedVolunteerId = assignedVolunteerId;
    }
}

