package com.hei.project2p1.service.utils.AgeCalculator;

import com.hei.project2p1.exception.ApiException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.hei.project2p1.controller.constant.CommonErrorMessage.UNSUPPORTED_ERROR_MESSAGE;


@Component
public class MonthPrecisionAgeCalculator implements AgeCalculator {

    @Override
    public int getAge(LocalDate date, Integer minInterval) {
        System.out.println("MONTH PRECISION");
        LocalDate currentDate = LocalDate.now();
        int diff = currentDate.getYear()-date.getYear();
        if (date.getMonth().getValue() > currentDate.getMonth().getValue()){
            diff-=1;
        }
        return diff;
    }

    @Override
    public int getAge(LocalDate date, AgeCalculator.Precision precision, Integer minInterval) {
        throw new ApiException(UNSUPPORTED_ERROR_MESSAGE);
    }


}
