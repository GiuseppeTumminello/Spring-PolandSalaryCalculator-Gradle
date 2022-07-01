package com.acoustic.SpringPolandSalaryCalculator.calculatorservice;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.acoustic.SpringPolandSalaryCalculator.rates.RatesConfigurationProperties;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class SicknessInsuranceService implements SalaryCalculatorService {

    private final RatesConfigurationProperties rate;

    @Override
    public BigDecimal apply(final BigDecimal grossMonthlySalary) {
        return grossMonthlySalary.multiply(this.rate.getSicknessZusRate()).setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public int getCalculationOrder() {
        return 9;
    }

    @Override
    public String getDescription() {
        return "Sickness insurance";
    }
}
