package com.acoustic.SpringPolandSalaryCalculator.calculatorservice;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class MonthlyNetService implements SalaryCalculatorService {

    private final TotalZusService totalZusService;
    private final TaxService taxService;
    private final HealthInsuranceService healthInsuranceService;

    @Override
    public String getDescription() {
        return "Monthly net";
    }

    @Override
    public BigDecimal apply(BigDecimal grossMonthlySalary) {
        return grossMonthlySalary.subtract(this.totalZusService.apply(grossMonthlySalary))
                .subtract((this.taxService.apply(grossMonthlySalary)))
                .subtract(this.healthInsuranceService.apply(grossMonthlySalary))
                .setScale(2, RoundingMode.HALF_EVEN);
    }
}
