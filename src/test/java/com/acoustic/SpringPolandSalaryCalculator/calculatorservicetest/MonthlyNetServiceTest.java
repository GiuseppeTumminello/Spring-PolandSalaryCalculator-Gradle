package com.acoustic.SpringPolandSalaryCalculator.calculatorservicetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class MonthlyNetServiceTest {

    @InjectMocks
    private MonthlyNetService monthlyNetService;
    @Mock
    private TotalZusService totalZusService;
    @Mock
    private TaxService taxService;
    @Mock
    private HealthInsuranceService healthInsuranceService;


    @ParameterizedTest
    @CsvSource({"6000, 4319.44, 822.60, 391.99, 465.97",
            "7000, 5039.35, 959.70, 457.32, 543.63",
            "15891.68,10691.81, 2178.75, 1786.96, 1234.16"})
    public void getMonthlyNet(
            BigDecimal input, BigDecimal expected, BigDecimal totalZus, BigDecimal tax, BigDecimal health) {
        given(totalZusService.apply(input)).willReturn(totalZus);
        given(taxService.apply(input)).willReturn(tax);
        given(healthInsuranceService.apply(input)).willReturn(health);
        assertThat(monthlyNetService.apply(input)).isEqualTo(expected);

    }

}