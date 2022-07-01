package com.acoustic.SpringPolandSalaryCalculator.calculatorservicetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;

import com.acoustic.SpringPolandSalaryCalculator.calculatorservice.HealthInsuranceService;
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
    @CsvSource({"5177.4, 465.97, 0.09", "6040.3, 543.63, 0.09", "13712.93, 1234.16, 0.09"})
    public void getHealthInsurance(
            BigDecimal input, BigDecimal expected, BigDecimal healthRate) {
        given(ratesConfigurationProperties.getHealthRate()).willReturn(healthRate);
        assertThat(healthInsuranceService.apply(input)).isEqualTo(expected);
    }

}