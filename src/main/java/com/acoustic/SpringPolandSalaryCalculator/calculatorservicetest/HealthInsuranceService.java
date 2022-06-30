package com.acoustic.SpringPolandSalaryCalculator.calculatorservicetest;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.acoustic.SpringPolandSalaryCalculator.rates.RatesConfigurationProperties;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class HealthInsuranceService implements SalaryCalculatorService {

    private final RatesConfigurationProperties rate;

    @Override
    public BigDecimal apply(BigDecimal grossMonthlySalary) {
        return grossMonthlySalary.multiply(this.rate.getHealthRate()).setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public int getOrder() {
        return 2;
    }

    @Override
    public String getDescription() {
        return "Health insurance";
    }
}
