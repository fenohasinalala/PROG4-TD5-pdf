package com.hei.project2p1.service.utils.AgeCalculator;

import com.hei.project2p1.exception.BadRequestException;
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
    private final CustomPrecisionAgeCalculator customPrecisionAgeCalculator;


    @Override
    public int getAge(LocalDate date, Integer minInterval) {
        return getCalculator().getAge(date,minInterval);
    }

    @Override
    public int getAge(LocalDate date, AgeCalculator.Precision precision, Integer minInterval) {
        AgeCalculator calculator = getCalculator(precision);
        return calculator.getAge(date,minInterval);
    }

    private AgeCalculator getCalculator() {
        return dayPrecisionAgeCalculator;
    }

    private AgeCalculator getCalculator(AgeCalculator.Precision precision) {
        return switch (precision) {
            case DAY -> dayPrecisionAgeCalculator;
            case MONTH -> monthPrecisionAgeCalculator;
            case YEAR -> yearPrecisionAgeCalculator;
            case CUSTOM -> customPrecisionAgeCalculator;
            default -> throw new IllegalArgumentException("Precision [" + precision + "] not recognized.");
        };
    }
}
