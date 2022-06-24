package com.acoustic.SpringPolandSalaryCalculator.calculatorservice;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.acoustic.SpringPolandSalaryCalculator.rates.RatesConfigurationProperties;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class SicknessInsuranceService implements SalaryCalculatorService{
    private final RatesConfigurationProperties ratesConfigurationProperties;
    @Override
    public BigDecimal apply(final BigDecimal bigDecimal) {
        return bigDecimal.multiply(ratesConfigurationProperties.getSicknessZusRate()).setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public String getDescription() {
        return "Sickness insurance";
    }
}
