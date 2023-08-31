package com.hei.project2p1.service.utils.AgeCalculator;

import com.hei.project2p1.exception.ApiException;
import com.hei.project2p1.exception.BadRequestException;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

import static com.hei.project2p1.controller.constant.CommonErrorMessage.UNSUPPORTED_ERROR_MESSAGE;

@Component
public class CustomPrecisionAgeCalculator implements AgeCalculator{

    @Override
    public int getAge(LocalDate date, Integer minInterval) {
        System.out.println("CUSTOM PRECISION");

        if (minInterval == null){
            throw new BadRequestException("Birthday min interval must be specified");
        }
        date = date.minusDays(minInterval);
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
    public int getAge(LocalDate date, Precision precision, Integer minInterval) {
        throw new ApiException(UNSUPPORTED_ERROR_MESSAGE);
    }


}
