package com.acoustic.SpringPolandSalaryCalculator.calculatorservice;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.acoustic.SpringPolandSalaryCalculator.rates.RatesConfigurationProperties;


@ExtendWith(MockitoExtension.class)
public class AnnualGrossServiceTest {


    @InjectMocks
    AnnualGrossService annualGrossService;
    @Mock
    private RatesConfigurationProperties rate;


    @ParameterizedTest
    @CsvSource({"6000, 72000", "7000, 84000", "15891.68, 190700.16"})
    void getAnnualGrossSalary(BigDecimal input, BigDecimal expected) {
        given(rate.getMonthNumber()).willReturn(rate.getMonthNumber());
        assertThat(annualGrossService.apply(input)).isEqualTo(expected);

    }

}
