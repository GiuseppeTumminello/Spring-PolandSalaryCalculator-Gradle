package com.acoustic.SpringPolandSalaryCalculator.rates;

import java.math.BigDecimal;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@ConfigurationProperties(prefix = "response")
@PropertySource("classpath:response-test.properties")
@Configuration


public class RatesConfigurationPropertiesTest {

    private BigDecimal pensionZusRate;

    private BigDecimal disabilityZusRate;

    private BigDecimal sicknessZusRate;

    private BigDecimal totalZusRate;

    private BigDecimal healthRate;

    private BigDecimal taxRate17Rate;

    private BigDecimal taxRate32Rate;

    private BigDecimal taxGrossAmountTrashold;

    private BigDecimal minimumSalary;

    public BigDecimal getPensionZusRate() {
        return pensionZusRate;
    }

    public void setPensionZusRate(final BigDecimal pensionZusRate) {
        this.pensionZusRate = pensionZusRate;
    }

    public BigDecimal getDisabilityZusRate() {
        return disabilityZusRate;
    }

    public void setDisabilityZusRate(final BigDecimal disabilityZusRate) {
        this.disabilityZusRate = disabilityZusRate;
    }

    public BigDecimal getSicknessZusRate() {
        return sicknessZusRate;
    }

    public void setSicknessZusRate(final BigDecimal sicknessZusRate) {
        this.sicknessZusRate = sicknessZusRate;
    }

    public BigDecimal getTotalZusRate() {
        return totalZusRate;
    }

    public void setTotalZusRate(final BigDecimal totalZusRate) {
        this.totalZusRate = totalZusRate;
    }

    public BigDecimal getHealthRate() {
        return healthRate;
    }

    public void setHealthRate(final BigDecimal healthRate) {
        this.healthRate = healthRate;
    }

    public BigDecimal getTaxRate17Rate() {
        return taxRate17Rate;
    }

    public void setTaxRate17Rate(final BigDecimal taxRate17Rate) {
        this.taxRate17Rate = taxRate17Rate;
    }

    public BigDecimal getTaxRate32Rate() {
        return taxRate32Rate;
    }

    public void setTaxRate32Rate(final BigDecimal taxRate32Rate) {
        this.taxRate32Rate = taxRate32Rate;
    }

    public BigDecimal getTaxGrossAmountTrashold() {
        return taxGrossAmountTrashold;
    }

    public void setTaxGrossAmountTrashold(final BigDecimal taxGrossAmountTrashold) {
        this.taxGrossAmountTrashold = taxGrossAmountTrashold;
    }

    public BigDecimal getMinimumSalary() {
        return minimumSalary;
    }

    public void setMinimumSalary(final BigDecimal minimumSalary) {
        this.minimumSalary = minimumSalary;
    }
}
