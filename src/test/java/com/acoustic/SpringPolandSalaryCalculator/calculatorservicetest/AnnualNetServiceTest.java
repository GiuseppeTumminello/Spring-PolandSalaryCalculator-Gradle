package com.acoustic.SpringPolandSalaryCalculator.calculatorservicetest;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import com.acoustic.SpringPolandSalaryCalculator.calculatorservice.AnnualNetService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class AnnualNetServiceTest {

    @InjectMocks
    AnnualNetService annualNetService;

    @ParameterizedTest
    @CsvSource({"4319.44, 51833.28", "5039.35, 60472.20", "10691.81, 128301.72"})
    void getAnnualNetSalary(BigDecimal input, BigDecimal expected) {
        assertThat(annualNetService.apply(input)).isEqualTo(expected);

    }

}
