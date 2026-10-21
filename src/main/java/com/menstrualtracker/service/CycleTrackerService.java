package com.menstrualtracker.service;

import com.menstrualtracker.model.PeriodRecord;
import com.menstrualtracker.repository.PeriodRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class CycleTrackerService {
    private final PeriodRepository repository;

    public CycleTrackerService(PeriodRepository repository) {
        this.repository = repository;
    }

    public void addPeriodStart(LocalDate startDate) {
        repository.save(new PeriodRecord(startDate));
    }

    public List<PeriodRecord> getPeriodHistory() {
        return repository.findAll();
    }

    public Optional<LocalDate> estimateNextPeriodStart() {
        List<PeriodRecord> records = getPeriodHistory();
        if (records.size() < 2) {
            return Optional.empty();
        }

        long totalDays = 0;
        for (int i = 1; i < records.size(); i++) {
            totalDays += ChronoUnit.DAYS.between(
                    records.get(i - 1).startDate(), records.get(i).startDate());
        }

        long averageCycleLength = Math.round((double) totalDays / (records.size() - 1));
        // Guard against obviously invalid intervals in this educational starter.
        if (averageCycleLength < 15 || averageCycleLength > 60) {
            return Optional.empty();
        }

        LocalDate mostRecentStart = records.get(records.size() - 1).startDate();
        return Optional.of(mostRecentStart.plusDays(averageCycleLength));
    }
}
