package com.hei.project2p1.service.utils.AgeCalculator;

import com.hei.project2p1.exception.ApiException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.hei.project2p1.controller.constant.CommonErrorMessage.UNSUPPORTED_ERROR_MESSAGE;

@Component
public class YearPrecisionAgeCalculator implements AgeCalculator{

    @Override
    public int getAge(LocalDate date, Integer minInterval) {
        System.out.println("YEAR PRECISION");
        LocalDate currentDate = LocalDate.now();
        return currentDate.getYear()-date.getYear();
    }

    @Override
    public int getAge(LocalDate date, Precision precision, Integer minInterval) {
        throw new ApiException(UNSUPPORTED_ERROR_MESSAGE);
    }


}
