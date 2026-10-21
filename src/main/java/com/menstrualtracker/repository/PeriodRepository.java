package com.menstrualtracker.repository;

import com.menstrualtracker.model.PeriodRecord;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PeriodRepository {
    private final List<PeriodRecord> records = new ArrayList<>();

    public void save(PeriodRecord record) {
        if (!records.contains(record)) {
            records.add(record);
            records.sort(Comparator.comparing(PeriodRecord::startDate));
        }
    }

    public List<PeriodRecord> findAll() {
        return List.copyOf(records);
    }
}
