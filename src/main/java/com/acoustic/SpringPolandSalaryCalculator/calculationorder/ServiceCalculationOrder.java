package com.acoustic.SpringPolandSalaryCalculator.calculationorder;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@ConfigurationProperties(prefix = "order")
@PropertySource("classpath:calculation-order.properties")
@Configuration
public class ServiceCalculationOrder {

    private int totalZusServiceOrder;
    private int healthInsuranceServiceOrder;
    private int taxServiceServiceOrder;
    private int monthlyNetServiceOrder;
    private int annualNetServiceOrder;
    private int annualGrossServiceOrder;
    private int disabilityZusServiceOrder;
    private int pensionZusServiceOrder;
    private int sicknessZusServiceOrder;

}
