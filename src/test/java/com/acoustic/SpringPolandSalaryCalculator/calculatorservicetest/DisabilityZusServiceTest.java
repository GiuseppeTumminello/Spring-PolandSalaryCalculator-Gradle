package com.acoustic.SpringPolandSalaryCalculator.calculatorservicetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;

import com.acoustic.SpringPolandSalaryCalculator.calculatorservice.DisabilityZusService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.acoustic.SpringPolandSalaryCalculator.rates.RatesConfigurationProperties;


@ExtendWith(MockitoExtension.class)
class DisabilityZusServiceTest {

    @InjectMocks
    private DisabilityZusService disabilityZusService;
    @Mock
    private RatesConfigurationProperties ratesConfigurationProperties;

    @ParameterizedTest
    @CsvSource({"6000, 90.00, 0.0150", "7000, 105.00, 0.0150", "15891.68, 238.38, 0.0150"})
    public void getDisabilityZus(BigDecimal input, BigDecimal expected, BigDecimal rate) {
        given(ratesConfigurationProperties.getDisabilityZusRate()).willReturn(rate);
        assertThat(disabilityZusService.apply(input)).isEqualTo(expected);
    }
}