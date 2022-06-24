package com.acoustic.SpringPolandSalaryCalculator.calculatorservice;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
class TotalZusServiceTest {


    @InjectMocks
    private TotalZusService totalZusService;


    @Mock
    private RatesConfigurationProperties ratesConfigurationProperties;

    @ParameterizedTest
    @CsvSource({"6000, 822.60, 0.1371", "7000, 959.70, 0.1371", "15891.68, 2178.75, 0.1371"})
    public void getTotalZus(BigDecimal input, BigDecimal expected, BigDecimal rate){
        given(ratesConfigurationProperties.getTotalZusRate()).willReturn(rate);
        assertThat(totalZusService.apply(input)).isEqualTo(expected);

    }


}