package com.acoustic.SpringPolandSalaryCalculator.calculatorservicetest;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

import org.springframework.stereotype.Service;


@Service
public interface SalaryCalculatorService extends UnaryOperator<BigDecimal> {

    int getOrder();

    String getDescription();

}
