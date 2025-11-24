package com.communityrelief.testing;

import com.communityrelief.data.InMemoryDatabase;
import com.communityrelief.model.RequestStatus;
import com.communityrelief.service.AssignmentService;
import com.communityrelief.service.RequestService;
import com.communityrelief.service.UserService;

import java.util.Set;

/**
 * Simple manual test runner using Java assertions (enable with -ea flag).
 */
public final class ManualTestSuite {

    private ManualTestSuite() {
    }

    public static void main(String[] args) {
        InMemoryDatabase db = new InMemoryDatabase();
        UserService userService = new UserService(db);
        RequestService requestService = new RequestService(db);
        AssignmentService assignmentService = new AssignmentService(userService, requestService);

        var volunteer = userService.registerVolunteer("Test Vol", "111", "Zone", Set.of("transport"), 10);
        var resident = userService.registerResident("Resident", "222", "Zone", 3);
        var request = requestService.createRequest(resident.getId(), "Need ride", "transport", 5);

        assignmentService.autoAssignNextRequest()
                .orElseThrow(() -> new AssertionError("Assignment should succeed"));

        assert request.getStatus() == RequestStatus.SCHEDULED : "Request scheduled";
        assert volunteer.isAvailable() == false : "Volunteer flagged unavailable";

        System.out.println("Manual tests completed successfully.");
    }
}

