package com.communityrelief;

import com.communityrelief.analytics.ReportService;
import com.communityrelief.data.InMemoryDatabase;
import com.communityrelief.io.ConsoleMenu;
import com.communityrelief.service.AssignmentService;
import com.communityrelief.service.RequestService;
import com.communityrelief.service.UserService;

/**
 * Entry point that wires together the services and launches the CLI.
 */
public final class App {

    private App() {
    }

    public static void main(String[] args) {
        InMemoryDatabase database = new InMemoryDatabase();
        UserService userService = new UserService(database);
        RequestService requestService = new RequestService(database);
        AssignmentService assignmentService = new AssignmentService(userService, requestService);
        ReportService reportService = new ReportService(userService, requestService);

        ConsoleMenu menu = new ConsoleMenu(userService, requestService, assignmentService, reportService);
        menu.start();
    }
}

