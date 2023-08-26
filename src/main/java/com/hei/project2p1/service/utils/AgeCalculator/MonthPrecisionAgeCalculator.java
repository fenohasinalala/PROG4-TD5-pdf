package com.hei.project2p1.service.utils.AgeCalculator;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MonthPrecisionAgeCalculator implements AgeCalculator {

    @Override
    public int getAge(LocalDate date) {
        System.out.println("MONTH PRECISION");
        LocalDate currentDate = LocalDate.now();
        int diff = currentDate.getYear()-date.getYear();
        if (date.getMonth().getValue() > currentDate.getMonth().getValue()){
            diff-=1;
        }
        return diff;
    }

    @Override
    public int getAge(LocalDate date, AgeCalculator.Precision precision) {
        return 0;
    }


}
