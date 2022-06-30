package com.acoustic.SpringPolandSalaryCalculator.calculatorservicetest;

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

    public static final int TAX_GROSS_AMOUNT_TRASHOLD = 120000;
    public static final double TOTAL_ZUS_RATE = 0.1371;
    public static final double HEALTH_RATE = 0.09;
    @InjectMocks
    private TaxService taxService;

    @Mock
    private RatesConfigurationProperties ratesConfigurationProperties;


    @ParameterizedTest
    @CsvSource({"6000, 391.99, 0.0832", "7000, 457.32, 0.0832",})
    public void getTaxAmountBasedOnRate17(BigDecimal input, BigDecimal expected, BigDecimal rate) {
        given(ratesConfigurationProperties.getTaxRate17Rate()).willReturn(rate);
        given(ratesConfigurationProperties.getTaxGrossAmountTrashold()).willReturn(BigDecimal.valueOf(
                TAX_GROSS_AMOUNT_TRASHOLD));
        given(ratesConfigurationProperties.getTotalZusRate()).willReturn(BigDecimal.valueOf(TOTAL_ZUS_RATE));
        given(ratesConfigurationProperties.getHealthRate()).willReturn(BigDecimal.valueOf(HEALTH_RATE));
        assertThat(taxService.apply(input)).isEqualTo(expected);

    }

    @ParameterizedTest
    @CsvSource({"15891.68, 1786.96, 0.1432"})
    public void getTaxAmountBasedOnRate32(BigDecimal input, BigDecimal expected, BigDecimal rate) {
        given(ratesConfigurationProperties.getTaxRate32Rate()).willReturn(rate);
        given(ratesConfigurationProperties.getTaxGrossAmountTrashold()).willReturn(BigDecimal.valueOf(
                TAX_GROSS_AMOUNT_TRASHOLD));
        given(ratesConfigurationProperties.getTotalZusRate()).willReturn(BigDecimal.valueOf(TOTAL_ZUS_RATE));
        given(ratesConfigurationProperties.getHealthRate()).willReturn(BigDecimal.valueOf(HEALTH_RATE));
        assertThat(taxService.apply(input)).isEqualTo(expected);

    }

}