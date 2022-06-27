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
class HealthInsuranceServiceTest {

    @InjectMocks
    private HealthInsuranceService healthInsuranceService;

    @Mock
    private RatesConfigurationProperties ratesConfigurationProperties;


    @ParameterizedTest
    @CsvSource({"6000, 465.97, 0.09, 0.1371", "7000, 543.63, 0.09, 0.1371", "15891.68, 1234.16, 0.09, 0.1371"})
    public void getHealthInsurance(
            BigDecimal input,
            BigDecimal expected,
            BigDecimal healthRate,
            BigDecimal totalZusRate) {
        given(ratesConfigurationProperties.getTotalZusRate()).willReturn(totalZusRate);
        given(ratesConfigurationProperties.getHealthRate()).willReturn(healthRate);
        assertThat(healthInsuranceService.apply(input)).isEqualTo(expected);
    }

}