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
public class AnnualNetServiceTest {

    @InjectMocks
    AnnualNetService annualNetService;

    @Mock
    private MonthlyNetService monthlyNetService;

    @ParameterizedTest
    @CsvSource({"6000, 51833.28, 4319.44", "7000, 60472.20, 5039.35", "15891.68, 128301.72, 10691.81"})
    void getAnnualNetSalary(BigDecimal input, BigDecimal expected, BigDecimal monthlyNet) {
        given(monthlyNetService.apply(input)).willReturn(monthlyNet);
        assertThat(annualNetService.apply(input)).isEqualTo(expected);

    }

}
