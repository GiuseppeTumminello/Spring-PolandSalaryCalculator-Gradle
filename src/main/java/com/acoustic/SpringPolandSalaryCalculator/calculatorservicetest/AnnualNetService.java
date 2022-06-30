package com.acoustic.SpringPolandSalaryCalculator.calculatorservicetest;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class AnnualNetService implements SalaryCalculatorService {

    private static final int MONTHS_NUMBER = 12;

    @Override
    public int getOrder() {
        return 5;
    }

    @Override
    public String getDescription() {
        return "Annual net";
    }

    @Override
    public BigDecimal apply(BigDecimal grossMonthlySalary) {
        return grossMonthlySalary.multiply(BigDecimal.valueOf(MONTHS_NUMBER));
    }
}
