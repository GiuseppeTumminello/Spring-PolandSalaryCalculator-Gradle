package com.acoustic.SpringPolandSalaryCalculator.entity;


import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DataSalaryCalculator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal grossMonthly;
    private String jobTitle;

}




