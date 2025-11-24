# Community Relief Coordination System – Statement

## Problem Statement
Neighborhood coordinators still rely on spreadsheets and messaging apps to organize volunteers and respond to resident assistance requests. This causes duplicated effort, poor traceability, and slow response times when matching needs to available skills.

## Scope
Build a pure-Java console workflow that manages volunteers, residents, assistance requests, automated assignments, and lightweight analytics. The system uses an in-memory data layer for simplicity but preserves clean layering so that persistence can be swapped later. Documentation, requirements, and diagrams are embedded via `ProjectDocumentation` and accessible through the CLI.

## Target Users
- Ward officers or NGO coordinators running neighborhood relief programs
- Volunteer leads who dispatch teams and need visibility into open tasks
- Analysts tracking response coverage and bottlenecks

## High-Level Features
1. Volunteer registry with skill tags, weekly capacity, and availability tracking
2. Resident registry plus request intake with urgency scores
3. Assignment engine that pairs the highest-priority request with a skilled volunteer
4. Summary reporting module showing request lifecycle counts and coverage percentage
5. Embedded documentation menu item for quick reference to objectives and requirements

