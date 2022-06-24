package com.acoustic.SpringPolandSalaryCalculator.calculatorservice;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.acoustic.SpringPolandSalaryCalculator.rates.RatesConfigurationProperties;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class AnnualNetService implements SalaryCalculatorService{
    private final RatesConfigurationProperties rate;
    private final MonthlyNetService monthlyNetService;


    @Override
    public String getDescription() {
        return "Annual net:";
    }

    @Override
    public BigDecimal apply(BigDecimal grossMonthlySalary) {
        return monthlyNetService.apply(grossMonthlySalary).multiply(rate.getMonthNumber());
    }
}
