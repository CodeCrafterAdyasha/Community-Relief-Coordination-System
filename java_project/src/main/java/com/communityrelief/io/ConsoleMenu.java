package com.communityrelief.io;

import com.communityrelief.analytics.ReportService;
import com.communityrelief.docs.ProjectDocumentation;
import com.communityrelief.model.AssistanceRequest;
import com.communityrelief.model.RequestStatus;
import com.communityrelief.model.Volunteer;
import com.communityrelief.service.AssignmentService;
import com.communityrelief.service.RequestService;
import com.communityrelief.service.UserService;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Simple CLI workflow to demonstrate the three functional modules.
 */
public class ConsoleMenu {
    private final UserService userService;
    private final RequestService requestService;
    private final AssignmentService assignmentService;
    private final ReportService reportService;
    private final InputHelper inputHelper;

    public ConsoleMenu(UserService userService,
                       RequestService requestService,
                       AssignmentService assignmentService,
                       ReportService reportService) {
        this.userService = userService;
        this.requestService = requestService;
        this.assignmentService = assignmentService;
        this.reportService = reportService;
        this.inputHelper = new InputHelper(new Scanner(System.in));

        seedDemoData();
    }

    public void start() {
        System.out.println("=== Community Relief Coordination ===");
        boolean running = true;
        while (running) {
            printMenu();
            String choice = inputHelper.readNonEmpty("Select option: ");
            switch (choice) {
                case "1" -> registerVolunteer();
                case "2" -> registerResident();
                case "3" -> logRequest();
                case "4" -> autoAssign();
                case "5" -> System.out.println(reportService.buildSummaryReport());
                case "6" -> listData();
                case "7" -> showDocumentation();
                case "0" -> running = false;
                default -> System.out.println("Unknown option.");
            }
        }
        System.out.println("Goodbye!");
    }

    private void printMenu() {
        System.out.println("""
                1. Register volunteer
                2. Register resident
                3. Log new assistance request
                4. Auto-assign most urgent request
                5. View summary report
                6. List volunteers & requests
                7. Show project documentation
                0. Exit
                """);
    }

    private void registerVolunteer() {
        String name = inputHelper.readNonEmpty("Name: ");
        String phone = inputHelper.readNonEmpty("Phone: ");
        String location = inputHelper.readNonEmpty("Location: ");
        String skillsRaw = inputHelper.readNonEmpty("Skills (comma separated): ");
        Set<String> skills = Arrays.stream(skillsRaw.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
        int hours = inputHelper.readInt("Weekly capacity (hrs): ", 1, 80);
        Volunteer volunteer = userService.registerVolunteer(name, phone, location, skills, hours);
        System.out.println("Registered volunteer " + volunteer.getId());
    }

    private void registerResident() {
        String name = inputHelper.readNonEmpty("Name: ");
        String phone = inputHelper.readNonEmpty("Phone: ");
        String location = inputHelper.readNonEmpty("Location: ");
        int priority = inputHelper.readInt("Priority (1-5): ", 1, 5);
        var resident = userService.registerResident(name, phone, location, priority);
        System.out.println("Registered resident " + resident.getId());
    }

    private void logRequest() {
        String residentId = inputHelper.readNonEmpty("Resident ID: ");
        if (userService.findResident(residentId).isEmpty()) {
            System.out.println("Resident not found.");
            return;
        }
        String description = inputHelper.readNonEmpty("Describe the need: ");
        String category = inputHelper.readNonEmpty("Required skill/category: ").toLowerCase();
        int urgency = inputHelper.readInt("Urgency (1-5): ", 1, 5);
        AssistanceRequest request = requestService.createRequest(residentId, description, category, urgency);
        System.out.println("Logged request " + request.getId());
    }

    private void autoAssign() {
        var result = assignmentService.autoAssignNextRequest();
        System.out.println(result.map(id -> "Assigned volunteer " + id)
                .orElse("No suitable volunteer found or no open requests."));
    }

    private void listData() {
        System.out.println("--- Volunteers ---");
        userService.listVolunteers().forEach(v ->
                System.out.println(v.getId() + " | " + v.getName() + " | skills=" + v.getSkills() +
                        " | available=" + v.isAvailable()));
        System.out.println("--- Requests ---");
        requestService.listRequests().forEach(r ->
                System.out.println(r.getId() + " | " + r.getDescription() +
                        " | status=" + r.getStatus() +
                        " | assigned=" + r.getAssignedVolunteerId()));
    }

    private void showDocumentation() {
        System.out.println(ProjectDocumentation.fullReport());
    }

    private void seedDemoData() {
        userService.registerVolunteer("Asha Khan", "555-1010", "Ward 4",
                Set.of("medical", "logistics"), 12);
        userService.registerVolunteer("Daniel Reed", "555-2020", "Ward 3",
                Set.of("transport", "logistics"), 8);
        var res = userService.registerResident("Maria Rao", "555-3030", "Ward 4", 4);
        AssistanceRequest request = requestService.createRequest(res.getId(),
                "Need transport to clinic", "transport", 5);
        request.setStatus(RequestStatus.NEW);
    }
}

