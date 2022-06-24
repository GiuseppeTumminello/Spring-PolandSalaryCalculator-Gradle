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
class PensionZusServiceTest {

    @InjectMocks
    private PensionZusService pensionZusService;
    @Mock
    private RatesConfigurationProperties ratesConfigurationProperties;


    @ParameterizedTest
    @CsvSource({"6000.,585.60, 0.0976", "7000, 683.20, 0.0976", "15891.68, 1551.03, 0.0976"})
    public void getPensionZus(BigDecimal input, BigDecimal expected, BigDecimal rate){
        given(ratesConfigurationProperties.getPensionZusRate()).willReturn(rate);
        assertThat(pensionZusService.apply(input)).isEqualTo(expected);
    }

}