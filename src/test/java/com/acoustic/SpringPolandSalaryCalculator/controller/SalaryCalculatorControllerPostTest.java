package com.acoustic.SpringPolandSalaryCalculator.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.NestedServletException;

import com.acoustic.SpringPolandSalaryCalculator.calculatorservice.SalaryCalculatorService;
import com.fasterxml.jackson.databind.ObjectMapper;


@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")

public class SalaryCalculatorControllerPostTest {


    public static final String CALCULATOR_ENDPOINTS = "/calculator/";
    public static final String DEPARTMENT_NAME_REQUEST_PARAM = "?departmentName=";
    public static final String JOB_TITLE_ID_REQUEST_PARAM = "&jobTitleId=";


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private List<SalaryCalculatorService> salaryCalculatorService;
    @Autowired
    private SalaryCalculatorTest salaryCalculatorTest;


    @ParameterizedTest
    @CsvSource({"6000, finance, 1",
            "7000, it, 2",
            "15891.68, airline, 1",
            "7700, restaurant, 6",
            "12191.68, it, 10",
            "185891.68, finance, 2"})
    public void getSalaryCalculation(BigDecimal grossMonthlySalary, String departmentName, int jobTitleId)
            throws Exception {
        this.mockMvc.perform(post(
                        CALCULATOR_ENDPOINTS + grossMonthlySalary + DEPARTMENT_NAME_REQUEST_PARAM + departmentName +
                                JOB_TITLE_ID_REQUEST_PARAM + jobTitleId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(objectMapper.writeValueAsString(salaryCalculatorTest.expectedValue(
                                grossMonthlySalary,
                                true))));

    }

    @ParameterizedTest
    @CsvSource({"6000, finance, -10",
            "7000, it, -1000",
            "15891.68, airline, 11",
            "7700, restaurant, 20",
            "12191.68, it, 30",
            "185891.68, finance, 40"})
    public void getSalaryCalculationIdOutOfBounds(
            BigDecimal grossMonthlySalary, String departmentName, int jobTitleId) {

        Assertions.assertThrows(NestedServletException.class,
                () -> this.mockMvc.perform(post(
                                CALCULATOR_ENDPOINTS + grossMonthlySalary + DEPARTMENT_NAME_REQUEST_PARAM + departmentName +
                                        JOB_TITLE_ID_REQUEST_PARAM + jobTitleId))
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.content()
                                .string(this.objectMapper.writeValueAsString(salaryCalculatorTest.expectedValue(
                                        grossMonthlySalary,
                                        true)))));

    }


    @ParameterizedTest
    @CsvSource({"6000, fff, 1",
            "7000, its, 1",
            "15891.68, airlines, 1",
            "7700, restaurants, 1",
            "12191.68, rest, 2",
            "185891.68, finances, 3"})
    public void getSalaryCalculationWrongDepartmentName(
            BigDecimal grossMonthlySalary, String departmentName, int jobTitleId) {

        Assertions.assertThrows(NestedServletException.class,
                () -> this.mockMvc.perform(post(
                                CALCULATOR_ENDPOINTS + grossMonthlySalary + DEPARTMENT_NAME_REQUEST_PARAM + departmentName +
                                        JOB_TITLE_ID_REQUEST_PARAM + jobTitleId))
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.content()
                                .string(this.objectMapper.writeValueAsString(salaryCalculatorTest.expectedValue(
                                        grossMonthlySalary,
                                        true)))));
    }


    @ParameterizedTest
    @CsvSource({"-6000, finance, 1",
            "-7000, it, 1",
            "-15891.68, airline, 1",
            "-7700, restaurant, 1",
            "1999.9999, it, 2",
            "0, finance, 3"})
    public void getSalaryCalculationGrossBelowTrashHold(
            BigDecimal grossMonthlySalary, String departmentName, int jobTitleId) {

        Assertions.assertThrows(NestedServletException.class,
                () -> this.mockMvc.perform(post(
                                CALCULATOR_ENDPOINTS + grossMonthlySalary + DEPARTMENT_NAME_REQUEST_PARAM + departmentName +
                                        JOB_TITLE_ID_REQUEST_PARAM + jobTitleId))
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.content()
                                .string(this.objectMapper.writeValueAsString(salaryCalculatorTest.expectedValue(
                                        grossMonthlySalary,
                                        true)))));
    }


    @ParameterizedTest
    @CsvSource({"6000", "7000", "15891.68", "7700", "2999.9999"})

    public void getSalaryCalculationGrossNoStatistic(BigDecimal grossMonthlySalary) throws Exception {

        this.mockMvc.perform(post(CALCULATOR_ENDPOINTS + grossMonthlySalary))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(objectMapper.writeValueAsString(salaryCalculatorTest.expectedValue(
                                grossMonthlySalary,
                                false))));

    }

}
