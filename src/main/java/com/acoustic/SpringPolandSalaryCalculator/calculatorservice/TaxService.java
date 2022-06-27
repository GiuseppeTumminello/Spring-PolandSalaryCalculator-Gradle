package com.acoustic.SpringPolandSalaryCalculator.calculatorservice;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.acoustic.SpringPolandSalaryCalculator.rates.RatesConfigurationProperties;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class TaxService implements SalaryCalculatorService {

    private final RatesConfigurationProperties rate;

    private  final TotalZusService totalZusService;

    private  final HealthInsuranceService healthInsuranceService;


    @Override
    public BigDecimal apply(BigDecimal grossMonthlySalary) {
        return (grossMonthlySalary.multiply(grossMonthlySalary)
                .compareTo(this.rate.getTaxGrossAmountTrashold()) < 0)
                ? getTaxAmountBasedOnRate(grossMonthlySalary, this.rate.getTaxRate32Rate())
                : getTaxAmountBasedOnRate(grossMonthlySalary, this.rate.getTaxRate17Rate());
    }

    @Override
    public String getDescription() {
        return "Tax amount";
    }


    private BigDecimal getTaxAmountBasedOnRate(BigDecimal grossMonthlySalary, BigDecimal rate) {
        return grossMonthlySalary.subtract(this.totalZusService.apply(grossMonthlySalary))
                .subtract(this.healthInsuranceService.apply(grossMonthlySalary))
                .multiply(rate)
                .setScale(2, RoundingMode.HALF_EVEN);
    }
}
