package com.hei.project2p1.service.utils.AgeCalculator;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Primary
@AllArgsConstructor
public class AgeCalculatorFacade implements AgeCalculator {
    private final DayPrecisionAgeCalculator dayPrecisionAgeCalculator;
    private final MonthPrecisionAgeCalculator monthPrecisionAgeCalculator;
    private final YearPrecisionAgeCalculator yearPrecisionAgeCalculator;


    @Override
    public int getAge(LocalDate date) {
        return getCalculator().getAge(date);
    }

    @Override
    public int getAge(LocalDate date, AgeCalculator.Precision precision) {
        return getCalculator(precision).getAge(date);
    }

    private AgeCalculator getCalculator() {
        return dayPrecisionAgeCalculator;
    }

    private AgeCalculator getCalculator(AgeCalculator.Precision precision) {
        return switch (precision) {
            case DAY -> dayPrecisionAgeCalculator;
            case MONTH -> monthPrecisionAgeCalculator;
            case YEAR -> yearPrecisionAgeCalculator;
            default -> throw new IllegalArgumentException("Precision [" + precision + "] not recognized.");
        };
    }
}
