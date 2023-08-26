package com.hei.project2p1.service.utils.AgeCalculator;

import java.time.LocalDate;

public interface AgeCalculator {
    int getAge(LocalDate date);

    int getAge(LocalDate date, Precision precision);

    static final Precision DAY = Precision.DAY;
    static final Precision MONTH = Precision.MONTH;
    static final Precision YEAR = Precision.YEAR;
    enum Precision{
        DAY,
        MONTH,
        YEAR
    }

}
