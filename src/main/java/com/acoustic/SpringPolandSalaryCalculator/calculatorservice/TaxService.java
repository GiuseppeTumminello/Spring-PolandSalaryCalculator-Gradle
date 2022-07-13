package com.acoustic.SpringPolandSalaryCalculator.calculatorservice;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.acoustic.SpringPolandSalaryCalculator.calculationorder.ServiceCalculationOrder;
import org.springframework.stereotype.Component;

import com.acoustic.SpringPolandSalaryCalculator.rates.RatesConfigurationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TaxService implements SalaryCalculatorService {


    public static final int MONTHS_NUMBER = 12;
    private final RatesConfigurationProperties rate;

    private final ServiceCalculationOrder serviceCalculationOrder;


    @Override
    public BigDecimal apply(BigDecimal grossMonthlySalary) {
        if (grossMonthlySalary.multiply(BigDecimal.valueOf(MONTHS_NUMBER)).compareTo(this.rate.getTaxGrossAmountThreshold()) < 0) {
            return getTaxAmountBasedOnRate(grossMonthlySalary, this.rate.getTaxRate17Rate());
        } else {
            return getTaxAmountBasedOnRate(grossMonthlySalary, this.rate.getTaxRate32Rate());
        }
    }

    @Override
    public int getCalculationOrder() {
        return this.serviceCalculationOrder.getTaxServiceServiceOrder();
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