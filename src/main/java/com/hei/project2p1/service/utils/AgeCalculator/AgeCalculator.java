package com.hei.project2p1.service.utils.AgeCalculator;

import java.time.LocalDate;

public interface AgeCalculator {
    int getAge(LocalDate date, Integer minInterval);

    int getAge(LocalDate date, Precision precision, Integer minInterval);

    static final Precision DAY = Precision.DAY;
    static final Precision MONTH = Precision.MONTH;
    static final Precision YEAR = Precision.YEAR;
    static final Precision CUSTOM = Precision.CUSTOM;
    enum Precision{
        DAY,
        MONTH,
        YEAR,
        CUSTOM
    }

}
