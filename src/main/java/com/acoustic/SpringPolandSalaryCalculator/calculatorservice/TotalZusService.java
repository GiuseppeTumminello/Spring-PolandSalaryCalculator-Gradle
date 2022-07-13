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
public class TotalZusService implements SalaryCalculatorService {

    private final RatesConfigurationProperties rate;

    private final ServiceCalculationOrder serviceCalculationOrder;

    @Override
    public int getCalculationOrder() {
        return this.serviceCalculationOrder.getTotalZusServiceOrder();
    }

    @Override
    public String getDescription() {
        return "Total zus";
    }

    @Override
    public BigDecimal apply(BigDecimal grossMonthlySalary) {
        return grossMonthlySalary.multiply(this.rate.getTotalZusRate()).setScale(2, RoundingMode.HALF_EVEN);
    }

}
