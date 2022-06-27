package com.acoustic.SpringPolandSalaryCalculator.entity;


import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;


@Entity
@Builder
@Getter
public class DataSalaryCalculator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private BigDecimal pensionZus;

    private BigDecimal disabilityZus;

    private BigDecimal sicknessZus;

    private BigDecimal totalZus;

    private BigDecimal health;

    private BigDecimal annualGross;

    private BigDecimal tax;

    private BigDecimal netMonthly;

    private BigDecimal annualNet;

    private BigDecimal grossMonthly;

    private String jobTitle;

}




