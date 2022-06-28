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
class SicknessInsuranceServiceTest {

    @InjectMocks
    private SicknessInsuranceService sicknessInsuranceService;

    @Mock
    private RatesConfigurationProperties ratesConfigurationProperties;


    @ParameterizedTest
    @CsvSource({"6000, 147.00, 0.0245", "7000, 171.50, 0.0245", "15891.68, 389.35, 0.0245"})
    public void getSicknessInsurance(BigDecimal input, BigDecimal expected, BigDecimal rate) {
        given(ratesConfigurationProperties.getSicknessZusRate()).willReturn(rate);
        assertThat(sicknessInsuranceService.apply(input)).isEqualTo(expected);

    }

}