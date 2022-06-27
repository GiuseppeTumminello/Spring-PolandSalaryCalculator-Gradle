package com.acoustic.SpringPolandSalaryCalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.acoustic.SpringPolandSalaryCalculator.service.DataSalaryCalculatorRepository;


@SpringBootApplication
public class SpringPolandSalaryCalculatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringPolandSalaryCalculatorApplication.class, args);

    }
}
