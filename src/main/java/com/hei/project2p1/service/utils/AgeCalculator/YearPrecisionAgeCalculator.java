package com.hei.project2p1.service.utils.AgeCalculator;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class YearPrecisionAgeCalculator implements AgeCalculator{

    @Override
    public int getAge(LocalDate date) {
        System.out.println("YEAR PRECISION");
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear()-date.getYear();
    }

    @Override
    public int getAge(LocalDate date, Precision precision) {
        return 0;
    }


}
