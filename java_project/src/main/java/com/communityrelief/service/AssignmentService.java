package com.communityrelief.service;

import com.communityrelief.model.AssistanceRequest;
import com.communityrelief.model.RequestStatus;
import com.communityrelief.model.Volunteer;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Matches volunteers with the highest priority request they can serve.
 */
public class AssignmentService {
    private final UserService userService;
    private final RequestService requestService;

    public AssignmentService(UserService userService, RequestService requestService) {
        this.userService = userService;
        this.requestService = requestService;
    }

    public Optional<String> autoAssignNextRequest() {
        List<AssistanceRequest> openRequests = requestService.listOpenRequests();
        if (openRequests.isEmpty()) {
            return Optional.empty();
        }

        AssistanceRequest request = openRequests.get(0);
        return assignVolunteerToRequest(request.getId(), request.getCategory());
    }

    public Optional<String> assignVolunteerToRequest(String requestId, String neededSkill) {
        Optional<AssistanceRequest> requestOpt = requestService.findRequest(requestId);
        if (requestOpt.isEmpty()) {
            return Optional.empty();
        }

        List<Volunteer> candidates = userService.listVolunteers().stream()
                .filter(Volunteer::isAvailable)
                .filter(v -> v.hasSkill(neededSkill))
                .sorted(Comparator.comparingInt(Volunteer::getWeeklyCapacityHours).reversed())
                .toList();

        if (candidates.isEmpty()) {
            return Optional.empty();
        }

        Volunteer volunteer = candidates.get(0);
        AssistanceRequest request = requestOpt.get();
        request.setAssignedVolunteerId(volunteer.getId());
        request.setStatus(RequestStatus.SCHEDULED);
        volunteer.setAvailable(false);
        return Optional.of(volunteer.getId());
    }
}

