package com.menstrualtracker;

import com.menstrualtracker.model.PeriodRecord;
import com.menstrualtracker.repository.PeriodRepository;
import com.menstrualtracker.service.CycleTrackerService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CycleTrackerService service = new CycleTrackerService(new PeriodRepository());

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                System.out.println("\n=== Menstrual Cycle Tracker (Core Java) ===");
                System.out.println("1. Add period start date");
                System.out.println("2. View period history");
                System.out.println("3. Estimate next period");
                System.out.println("0. Exit");
                System.out.print("Choose an option: ");
                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1" -> addDate(scanner, service);
                    case "2" -> {
                        if (service.getPeriodHistory().isEmpty()) {
                            System.out.println("No records yet.");
                        } else {
                            for (PeriodRecord record : service.getPeriodHistory()) {
                                System.out.println("- " + record.startDate());
                            }
                        }
                    }
                    case "3" -> {
                        Optional<LocalDate> estimate = service.estimateNextPeriodStart();
                        if (estimate.isPresent()) {
                            System.out.println("Estimated next start date: " + estimate.get());
                            System.out.println("This is only an estimate, not medical advice.");
                        } else {
                            System.out.println("Add at least two valid period start dates to estimate.");
                        }
                    }
                    case "0" -> running = false;
                    default -> System.out.println("Please choose 1, 2, 3, or 0.");
                }
            }
        }
        System.out.println("Goodbye.");
    }

    private static void addDate(Scanner scanner, CycleTrackerService service) {
        System.out.print("Enter period start date (YYYY-MM-DD): ");
        String input = scanner.nextLine().trim();
        try {
            service.addPeriodStart(LocalDate.parse(input));
            System.out.println("Period start date saved.");
        } catch (DateTimeParseException ex) {
            System.out.println("Invalid date. Use YYYY-MM-DD, for example 2026-10-01.");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
