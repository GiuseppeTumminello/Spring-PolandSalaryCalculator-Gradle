package com.acoustic.SpringPolandSalaryCalculator.calculatorservicetest;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.acoustic.SpringPolandSalaryCalculator.rates.RatesConfigurationProperties;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class PensionZusService implements SalaryCalculatorService {

    private final RatesConfigurationProperties rate;

    @Override
    public BigDecimal apply(final BigDecimal grossMonthlySalary) {
        return grossMonthlySalary.multiply(this.rate.getPensionZusRate()).setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public int getCalculationOrder() {
        return 8;
    }

    @Override
    public String getDescription() {
        return "Pension zus";
    }
}
