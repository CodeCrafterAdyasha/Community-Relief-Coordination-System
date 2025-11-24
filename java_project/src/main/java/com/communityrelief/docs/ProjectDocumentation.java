package com.communityrelief.docs;

/**
 * Raw text documentation maintained entirely in Java to satisfy the
 * "only Java files" restriction while preserving all academic artefacts.
 */
public final class ProjectDocumentation {

    private ProjectDocumentation() {
    }

    public static String problemStatement() {
        return """
                Problem Statement:
                Neighborhood relief coordinators rely on spreadsheets and chat apps that \
                provide poor traceability, causing duplicated efforts and slow response \
                times when matching volunteers to resident requests.
                """;
    }

    public static String objectives() {
        return """
                Objectives:
                1. Provide structured intake for residents and ward representatives.
                2. Maintain a live volunteer registry with skills and capacity.
                3. Automate assignment of urgent tasks to capable volunteers.
                4. Offer visibility into operations via console analytics.
                """;
    }

    public static String functionalRequirements() {
        return """
                Functional Requirements:
                - Volunteer Management Module: register/list volunteers, track skills and availability.
                - Resident & Request Intake Module: register residents, log requests with urgency ranking.
                - Assignment & Analytics Module: auto-match volunteers, update lifecycle, show summary report.
                """;
    }

    public static String nonFunctionalRequirements() {
        return """
                Non-Functional Requirements:
                1. Usability: end-to-end workflow achievable within five console steps.
                2. Reliability: deterministic in-memory storage plus manual test harness.
                3. Maintainability: layered packages (model, service, analytics, io, data, docs).
                4. Performance: operations execute in <10 ms for <=10k records (in-memory collections).
                5. Error Handling: input helper enforces ranges and required fields.
                6. Logging: console traces each action; ready for future adapters.
                """;
    }

    public static String architectureDiagram() {
        return """
                Architecture (textual):
                [ConsoleMenu] -> invokes -> [UserService | RequestService | AssignmentService | ReportService]
                Services -> depend on -> [InMemoryDatabase]
                ReportService pulls aggregated metrics from UserService and RequestService.
                AssignmentService coordinates both services to lock volunteers and update requests.
                """;
    }

    public static String workflowDiagram() {
        return """
                Workflow (textual flow):
                MENU -> (Register Volunteer) -> Store -> Back to MENU
                MENU -> (Register Resident) -> Store -> Back to MENU
                MENU -> (Log Request) -> Validate Resident -> Store Request -> Back to MENU
                MENU -> (Auto Assign) -> Fetch Highest Urgency -> Match Skill -> Update Status -> Back to MENU
                MENU -> (View Report) -> Aggregate Stats -> Display -> Back to MENU
                """;
    }

    public static String umlSummary() {
        return """
                UML Summary:
                Use Cases: Register Volunteer, Register Resident, Log Request, Auto Assign, View Report.
                Class Relations: User <- Resident, Volunteer; AssignmentService uses UserService + RequestService;
                ReportService queries both services; InMemoryDatabase stores entities; InputHelper enables CLI validation.
                Sequence Highlight (Auto Assign):
                ConsoleMenu -> AssignmentService -> RequestService -> InMemoryDatabase (requests)
                -> AssignmentService -> UserService -> InMemoryDatabase (volunteers)
                -> AssignmentService updates models -> ConsoleMenu displays result.
                """;
    }

    public static String dataModel() {
        return """
                Data Model:
                Resident(id, name, phone, location, priorityLevel)
                Volunteer(id, name, phone, location, skills, weeklyCapacityHours, available)
                AssistanceRequest(id, residentId, description, category, urgency, status, assignedVolunteerId)
                Relationships: Resident 1..* AssistanceRequest; Volunteer 0..* AssistanceRequest.
                """;
    }

    public static String instructions() {
        return """
                Build & Run (only Java tooling):
                1. From project root: javac -d out $(Get-ChildItem -Recurse -Filter *.java)
                2. Run: java -cp out com.communityrelief.App
                Manual Test Harness:
                - Toggle Java assertions (`java -ea ...`) or run the ManualTestSuite class.
                """;
    }

    public static String fullReport() {
        return String.join("\n",
                problemStatement(),
                objectives(),
                functionalRequirements(),
                nonFunctionalRequirements(),
                architectureDiagram(),
                workflowDiagram(),
                umlSummary(),
                dataModel(),
                instructions());
    }
}

