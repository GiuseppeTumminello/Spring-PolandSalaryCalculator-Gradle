package com.acoustic.SpringPolandSalaryCalculator.calculatorservice;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.acoustic.SpringPolandSalaryCalculator.calculationorder.ServiceCalculationOrder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MonthlyNetService implements SalaryCalculatorService {

    private final ServiceCalculationOrder serviceCalculationOrder;
    @Override
    public int getCalculationOrder() {
        return this.serviceCalculationOrder.getMonthlyNetServiceOrder();
    }

    @Override
    public String getDescription() {
        return "Monthly net";
    }

    @Override
    public BigDecimal apply(BigDecimal grossMonthlySalary) {
        return grossMonthlySalary.setScale(2, RoundingMode.HALF_EVEN);
    }
}
