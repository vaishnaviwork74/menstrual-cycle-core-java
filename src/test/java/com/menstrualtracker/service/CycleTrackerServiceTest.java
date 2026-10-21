package com.menstrualtracker.service;

import com.menstrualtracker.repository.PeriodRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CycleTrackerServiceTest {
    @Test
    void estimatesNextStartFromAverageRecordedInterval() {
        CycleTrackerService service = new CycleTrackerService(new PeriodRepository());
        service.addPeriodStart(LocalDate.of(2026, 1, 1));
        service.addPeriodStart(LocalDate.of(2026, 1, 29));

        assertEquals(LocalDate.of(2026, 2, 26), service.estimateNextPeriodStart().orElseThrow());
    }

    @Test
    void needsAtLeastTwoRecordsForEstimate() {
        CycleTrackerService service = new CycleTrackerService(new PeriodRepository());
        service.addPeriodStart(LocalDate.of(2026, 1, 1));

        assertTrue(service.estimateNextPeriodStart().isEmpty());
    }
}
