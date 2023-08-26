package com.hei.project2p1.service.utils.AgeCalculator;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DayPrecisionAgeCalculator implements AgeCalculator{

    @Override
    public int getAge(LocalDate date) {
        System.out.println("DAY PRECISION");
        LocalDate currentDate = LocalDate.now();
        int diff = currentDate.getYear()-date.getYear();

        if (date.getMonth().getValue() > currentDate.getMonth().getValue() ||
                (date.getMonth().getValue() == currentDate.getMonth().getValue()
                        && date.getDayOfMonth() > currentDate.getDayOfMonth())
            ){
            diff-=1;
        }
        return diff;
    }

    @Override
    public int getAge(LocalDate date, Precision precision) {
        return 0;
    }


}
