package com.acoustic.SpringPolandSalaryCalculator.calculatorservice;

import java.math.BigDecimal;

import com.acoustic.SpringPolandSalaryCalculator.calculationorder.ServiceCalculationOrder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AnnualNetService implements SalaryCalculatorService {

    private final ServiceCalculationOrder serviceCalculationOrder;

    private static final int MONTHS_NUMBER = 12;

    @Override
    public int getCalculationOrder() {
        return this.serviceCalculationOrder.getAnnualNetServiceOrder();
    }

    @Override
    public String getDescription() {
        return "Annual net";
    }

    @Override
    public BigDecimal apply(BigDecimal grossMonthlySalary) {
        return grossMonthlySalary.multiply(BigDecimal.valueOf(MONTHS_NUMBER));
    }
}
