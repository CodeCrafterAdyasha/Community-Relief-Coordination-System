# Community Relief Coordination System

## Overview
Pure Java console program for coordinating volunteers and resident requests. It demonstrates three major modules:
1. Volunteer management with skills and capacity tracking.
2. Resident/request intake with urgency ranking.
3. Assignment plus analytics to match skilled volunteers and show coverage.

## Folder Structure
```
src/main/java/com/communityrelief/
├─ model/        Domain entities (User, Volunteer, Resident, AssistanceRequest, RequestStatus)
├─ data/         InMemoryDatabase persistence layer
├─ service/      Business services (UserService, RequestService, AssignmentService)
├─ analytics/    ReportService for summary metrics
├─ io/           ConsoleMenu + InputHelper for user workflow
├─ docs/         ProjectDocumentation (problem statement, requirements, diagrams)
└─ testing/      ManualTestSuite (assertion-based validation)
```

## Build & Run (Java only)
1. Open a terminal in `C:\Users\hp\OneDrive\Desktop\java_project`.
2. Compile every `.java` file into the `out` folder:
   - PowerShell:
     ```
     javac -d out @(Get-ChildItem -Recurse -Filter *.java | Select-Object -ExpandProperty FullName)
     ```
   - cmd.exe alternative (two steps):
     ```
     for /R %f in (*.java) do @echo %f   // inspect list
     javac -d out @sources.txt           // use response file if preferred
     ```
3. Run the console app:
   ```
   java -cp out com.communityrelief.App
   ```
4. Manual test harness with assertions:
   ```
   java -ea -cp out com.communityrelief.testing.ManualTestSuite
   ```

## Usage Tips
- Option 7 in the CLI prints the embedded ProjectDocumentation (problem statement, requirements, architecture, workflow, UML summaries, and run instructions).
- Seed data loads automatically so you can test auto-assignment immediately.
- Restart the program to reset the in-memory database.

