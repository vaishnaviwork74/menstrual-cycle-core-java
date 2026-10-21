# Menstrual Cycle Tracker — Core Java

A beginner-friendly command-line menstrual cycle tracker implemented with **Core Java only**.
It uses in-memory storage, so records are lost when the application exits. This starter project
is for learning clean Java design; it is not a medical application.

## Features
- Add a period start date
- List recorded period start dates
- Estimate the next period using the average interval between recorded starts
- Input validation and clear separation of model, service, and application layers

## Requirements
- JDK 17 or later
- Maven 3.8+ (or an IDE that imports Maven projects)

## Run
```bash
mvn clean package
mvn exec:java
```

## Example
The menu asks for period start dates in `YYYY-MM-DD` format. Enter at least two dates before
asking for a prediction. Predictions are estimates only and should not be used for contraception
or medical decisions.

## Project structure
```text
src/main/java/com/menstrualtracker/
├── Main.java
├── model/PeriodRecord.java
├── repository/PeriodRepository.java
└── service/CycleTrackerService.java
```

## Git workflow
```bash
git status
git add .
git commit -m "feat: add core cycle tracking"
git push
```

Before committing, review `git status` and never commit passwords, private health records, or
local IDE/build files.
