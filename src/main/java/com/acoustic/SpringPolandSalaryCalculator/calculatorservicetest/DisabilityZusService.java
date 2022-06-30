package com.acoustic.SpringPolandSalaryCalculator.calculatorservicetest;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.acoustic.SpringPolandSalaryCalculator.rates.RatesConfigurationProperties;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class DisabilityZusService implements SalaryCalculatorService {

    private final RatesConfigurationProperties rate;

    @Override
    public BigDecimal apply(BigDecimal grossMonthlySalary) {
        return grossMonthlySalary.multiply(this.rate.getDisabilityZusRate()).setScale(2, RoundingMode.HALF_EVEN);
    }

    @Override
    public int getOrder() {
        return 6;
    }

    @Override
    public String getDescription() {
        return "Disability zus";
    }
}
