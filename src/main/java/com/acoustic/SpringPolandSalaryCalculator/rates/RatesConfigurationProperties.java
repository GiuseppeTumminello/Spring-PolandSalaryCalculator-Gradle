package com.acoustic.SpringPolandSalaryCalculator.rates;

import java.math.BigDecimal;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ConfigurationProperties(prefix = "rate")
@PropertySource("classpath:rates.properties")
@Configuration
public class RatesConfigurationProperties {

    private  BigDecimal pensionZusRate;

    private  BigDecimal disabilityZusRate;

    private  BigDecimal sicknessZusRate;

    private  BigDecimal totalZusRate;

    private  BigDecimal healthRate;

    private  BigDecimal taxRate17Rate;

    private  BigDecimal taxRate32Rate;

    private  BigDecimal taxGrossAmountTrashold;

    private  BigDecimal monthNumber;

    private  BigDecimal minimumSalary;
}
