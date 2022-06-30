package com.acoustic.SpringPolandSalaryCalculator.calculatorservicetest;


import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class AnnualGrossServiceTest {

    @InjectMocks
    AnnualGrossService annualGrossService;


    @ParameterizedTest
    @CsvSource({"6000, 72000.00", "7000, 84000.00", "15891.68, 190700.16"})
    void getAnnualGrossSalary(BigDecimal input, BigDecimal expected) {
        assertThat(annualGrossService.apply(input)).isEqualTo(expected);

    }

}
