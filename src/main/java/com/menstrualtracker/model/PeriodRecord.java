package com.menstrualtracker.model;

import java.time.LocalDate;
import java.util.Objects;

public record PeriodRecord(LocalDate startDate) {
    public PeriodRecord {
        Objects.requireNonNull(startDate, "startDate must not be null");
        if (startDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Period start date cannot be in the future.");
        }
    }
}
