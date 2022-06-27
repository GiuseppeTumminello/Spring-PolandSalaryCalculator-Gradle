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
class TaxServiceTest {

    @InjectMocks
    private TaxService taxService;

    @Mock
    private RatesConfigurationProperties ratesConfigurationProperties;

    @Mock

    private TotalZusService totalZusService;

    @Mock
    private HealthInsuranceService healthInsuranceService;


    @ParameterizedTest
    @CsvSource({"6000, 391.99, 0.0832,  822.60, 465.97",
            "7000, 457.32, 0.0832,  959.70, 543.63",
            "15891.68, 1786.96, 0.1432, 2178.75,1234.16"})
    public void getTaxAmountBasedOnRate(
            BigDecimal input, BigDecimal expected, BigDecimal rate, BigDecimal totalZus, BigDecimal health) {

        given(ratesConfigurationProperties.getTaxRate17Rate()).willReturn(rate);
        given(ratesConfigurationProperties.getTaxGrossAmountTrashold()).willReturn(BigDecimal.valueOf(120_000.00));
        given(totalZusService.apply(input)).willReturn(totalZus);
        given(healthInsuranceService.apply(input)).willReturn(health);

        assertThat(taxService.apply(input)).isEqualTo(expected);

    }

}