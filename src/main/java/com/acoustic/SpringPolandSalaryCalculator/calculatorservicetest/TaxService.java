package com.acoustic.SpringPolandSalaryCalculator.calculatorservicetest;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.acoustic.SpringPolandSalaryCalculator.rates.RatesConfigurationProperties;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class TaxService implements SalaryCalculatorService {


    public static final int MONTHS_NUMBER = 12;
    private final RatesConfigurationProperties rate;


    @Override
    public BigDecimal apply(BigDecimal grossMonthlySalary) {
        return (grossMonthlySalary.multiply(BigDecimal.valueOf(MONTHS_NUMBER))
                .compareTo(this.rate.getTaxGrossAmountTrashold()) < 0)
                ? getTaxAmountBasedOnRate(
                grossMonthlySalary,
                this.rate.getTaxRate17Rate())
                : getTaxAmountBasedOnRate(grossMonthlySalary, this.rate.getTaxRate32Rate());
    }

    @Override
    public int getCalculationOrder() {
        return 3;
    }

    @Override
    public String getDescription() {
        return "Tax amount";
    }


    private BigDecimal getTaxAmountBasedOnRate(BigDecimal grossMonthlySalary, BigDecimal rate) {
        grossMonthlySalary = grossMonthlySalary.subtract(grossMonthlySalary.multiply(this.rate.getTotalZusRate()));
        grossMonthlySalary = grossMonthlySalary.subtract(grossMonthlySalary.multiply(this.rate.getHealthRate()));
        return grossMonthlySalary.multiply(rate).setScale(2, RoundingMode.HALF_EVEN);

    }
}