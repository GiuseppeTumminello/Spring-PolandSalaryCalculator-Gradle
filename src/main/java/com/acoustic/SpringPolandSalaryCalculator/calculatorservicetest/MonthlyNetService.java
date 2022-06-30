package com.acoustic.SpringPolandSalaryCalculator.calculatorservicetest;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class MonthlyNetService implements SalaryCalculatorService {

    @Override
    public int getOrder() {
        return 4;
    }

    @Override
    public String getDescription() {
        return "Monthly net";
    }

    @Override
    public BigDecimal apply(BigDecimal grossMonthlySalary) {
        return grossMonthlySalary.setScale(2, RoundingMode.HALF_EVEN);
    }
}
