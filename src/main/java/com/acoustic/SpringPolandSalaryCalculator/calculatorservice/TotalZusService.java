package com.acoustic.SpringPolandSalaryCalculator.calculatorservice;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.acoustic.SpringPolandSalaryCalculator.rates.RatesConfigurationProperties;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class TotalZusService implements SalaryCalculatorService {

    private final RatesConfigurationProperties rate;

    @Override
    public int getCalculationOrder() {
        return 1;
    }

    @Override
    public String getDescription() {
        return "Total zus";
    }

    @Override
    public BigDecimal apply(BigDecimal grossMonthlySalary) {
        return grossMonthlySalary.multiply(this.rate.getTotalZusRate()).setScale(2, RoundingMode.HALF_EVEN);
    }

}
