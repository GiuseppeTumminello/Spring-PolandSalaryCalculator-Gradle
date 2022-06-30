package com.acoustic.SpringPolandSalaryCalculator.calculatorservicetest;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class MonthlyNetServiceTest {

    @InjectMocks
    private MonthlyNetService monthlyNetService;


    @ParameterizedTest
    @CsvSource({"4319.44, 4319.44", "5039.35, 5039.35", "10691.81,10691.81"})
    public void getMonthlyNet(
            BigDecimal input, BigDecimal expected) {
        assertThat(monthlyNetService.apply(input)).isEqualTo(expected);

    }

}