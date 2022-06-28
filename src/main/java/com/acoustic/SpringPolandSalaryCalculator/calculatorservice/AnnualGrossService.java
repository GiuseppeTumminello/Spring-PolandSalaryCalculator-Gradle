package com.acoustic.SpringPolandSalaryCalculator.calculatorservice;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.acoustic.SpringPolandSalaryCalculator.rates.RatesConfigurationProperties;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class AnnualGrossService implements SalaryCalculatorService {
    private static final int MONTHS_NUMBER = 12;

    @Override
    public String getDescription() {
        return "Annual gross:";
    }

    @Override
    public BigDecimal apply(BigDecimal grossMonthlySalary) {
        return grossMonthlySalary.multiply(BigDecimal.valueOf(MONTHS_NUMBER));
    }
}
