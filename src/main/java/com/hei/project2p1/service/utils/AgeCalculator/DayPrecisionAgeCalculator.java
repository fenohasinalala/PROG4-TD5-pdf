package com.hei.project2p1.service.utils.AgeCalculator;

import com.hei.project2p1.exception.ApiException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.hei.project2p1.controller.constant.CommonErrorMessage.UNSUPPORTED_ERROR_MESSAGE;

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
        throw new ApiException(UNSUPPORTED_ERROR_MESSAGE);
    }


}
