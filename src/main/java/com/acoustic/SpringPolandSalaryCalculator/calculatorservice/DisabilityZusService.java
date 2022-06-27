package com.acoustic.SpringPolandSalaryCalculator.calculatorservice;

import java.math.BigDecimal;
import java.math.RoundingMode;


import org.springframework.stereotype.Component;

import com.acoustic.SpringPolandSalaryCalculator.rates.RatesConfigurationProperties;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class DisabilityZusService implements SalaryCalculatorService {
    private final RatesConfigurationProperties rates;
    @Override
    public BigDecimal apply(BigDecimal grossMonthlySalary) {
        return grossMonthlySalary.multiply(this.rates.getDisabilityZusRate()).setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String getDescription() {
        return "Disability zus";
    }
}
