package com.communityrelief.analytics;

import com.communityrelief.model.AssistanceRequest;
import com.communityrelief.model.RequestStatus;
import com.communityrelief.model.Volunteer;
import com.communityrelief.service.RequestService;
import com.communityrelief.service.UserService;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Produces lightweight analytics for console display.
 */
public class ReportService {
    private final UserService userService;
    private final RequestService requestService;

    public ReportService(UserService userService, RequestService requestService) {
        this.userService = userService;
        this.requestService = requestService;
    }

    public String buildSummaryReport() {
        Collection<Volunteer> volunteers = userService.listVolunteers();
        Collection<AssistanceRequest> requests = requestService.listRequests();

        Map<RequestStatus, Long> byStatus = requests.stream()
                .collect(Collectors.groupingBy(AssistanceRequest::getStatus, Collectors.counting()));

        long completed = byStatus.getOrDefault(RequestStatus.COMPLETED, 0L);
        long scheduled = byStatus.getOrDefault(RequestStatus.SCHEDULED, 0L);
        long newRequests = byStatus.getOrDefault(RequestStatus.NEW, 0L);

        double assignmentRate = requests.isEmpty() ? 0 :
                (double) (completed + scheduled) / requests.size() * 100;

        return """
                ----- Relief Operations Summary -----
                Volunteers onboarded : %d
                Requests logged      : %d
                New / Scheduled / Completed : %d / %d / %d
                Assignment coverage  : %.1f %%
                -------------------------------------
                """.formatted(volunteers.size(), requests.size(), newRequests, scheduled, completed, assignmentRate);
    }
}

