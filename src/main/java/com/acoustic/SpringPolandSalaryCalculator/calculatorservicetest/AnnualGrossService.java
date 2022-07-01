package com.acoustic.SpringPolandSalaryCalculator.calculatorservicetest;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class AnnualGrossService implements SalaryCalculatorService {

    private static final int MONTHS_NUMBER = 12;


    @Override
    public int getCalculationOrder() {
        return 6;
    }

    @Override
    public String getDescription() {
        return "Annual gross";
    }


    @Override
    public BigDecimal apply(BigDecimal grossMonthlySalary) {
        return grossMonthlySalary.multiply(BigDecimal.valueOf(MONTHS_NUMBER)).setScale(2, RoundingMode.HALF_EVEN);
    }
}
