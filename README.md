# 🏗️ Community Relief Coordination System

> A pure Java console application for coordinating volunteers and resident requests during community relief operations.

## 📋 Overview

This system demonstrates three major modules for efficient disaster relief coordination:

🎯 **Core Modules:**
- 👥 **Volunteer Management** - Track skills, availability, and capacity
- 🏠 **Resident Request Intake** - Manage assistance requests with urgency ranking  
- 🔄 **Smart Assignment & Analytics** - Match skilled volunteers and show coverage metrics

## 🗂️ Project Structure

```
src/main/java/com/communityrelief/
├─ model/        🏛️ Domain entities (User, Volunteer, Resident, AssistanceRequest, RequestStatus)
├─ data/         💾 InMemoryDatabase persistence layer
├─ service/      ⚙️ Business services (UserService, RequestService, AssignmentService)
├─ analytics/    📊 ReportService for summary metrics
├─ io/           🖥️ ConsoleMenu + InputHelper for user workflow
├─ docs/         📚 ProjectDocumentation (problem statement, requirements, diagrams)
└─ testing/      🧪 ManualTestSuite (assertion-based validation)
```

## 🚀 Quick Start

### Prerequisites
- ☕ Java JDK 8 or higher
- 💻 Terminal/Command Prompt access

### 🛠️ Build & Run

1. **Navigate to project directory:**
   ```bash
   cd C:\Users\hp\OneDrive\Desktop\java_project
   ```

2. **Compile all Java files:**
   - **PowerShell:**
     ```powershell
     javac -d out @(Get-ChildItem -Recurse -Filter *.java | Select-Object -ExpandProperty FullName)
     ```
   - **Command Prompt:**
     ```cmd
     dir /s /b *.java > sources.txt
     javac -d out @sources.txt
     ```

3. **Run the application:**
   ```bash
   java -cp out com.communityrelief.App
   ```

4. **Run test suite:**
   ```bash
   java -ea -cp out com.communityrelief.testing.ManualTestSuite
   ```

## 🎮 Usage Guide

### 🏠 Main Features
- **Volunteer Registration** - Add volunteers with specific skills and capacity
- **Request Management** - Create assistance requests with urgency levels
- **Smart Matching** - Auto-assign volunteers based on skills and availability
- **Real-time Analytics** - View coverage reports and metrics
- **Request Tracking** - Monitor request status from PENDING to COMPLETED

### 💡 Pro Tips
- ✨ **Option 7** in the CLI displays comprehensive project documentation
- 🌱 **Seed data loads automatically** - test assignments immediately
- 🔄 **Restart the program** to reset the in-memory database
- 🧪 Use the test suite to validate system functionality

## 📊 System Capabilities

### Volunteer Skills Tracking
- Medical, Construction, Logistics, Counseling, and more
- Capacity limits per volunteer
- Availability status monitoring

### Request Urgency System
- CRITICAL, HIGH, MEDIUM, LOW priority levels
- Automated assignment based on urgency and skills
- Real-time status updates

### Analytics Dashboard
- Volunteer coverage metrics
- Request fulfillment rates
- Skill gap analysis

## 🎯 Ideal For
- 🚨 Disaster relief coordination
- 🤝 Community support programs  
- 🏥 Emergency response management
- 👥 Volunteer organization tracking

---

**Built with ❤️ using Pure Java** • *No external dependencies required*
