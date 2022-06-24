package com.acoustic.SpringPolandSalaryCalculator.controller;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import com.acoustic.SpringPolandSalaryCalculator.calculatorservice.SalaryCalculatorService;
import com.acoustic.SpringPolandSalaryCalculator.entity.DataSalaryCalculator;
import com.acoustic.SpringPolandSalaryCalculator.jobcategories.JobCategories;
import com.acoustic.SpringPolandSalaryCalculator.service.DataSalaryCalculatorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class SalaryCalculatorControllerPostTest {

    private final String FINANCE_POST_ENDPOINT = "/calculate/7000?departmentName=finance&jobTitleId=2";

    @Autowired
    private DataSalaryCalculatorRepository dataSalaryCalculatorRepository;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;



    @Autowired
    private List<SalaryCalculatorService> salaryCalculatorService;
    private BigDecimal grossMonthlySalary = BigDecimal.valueOf(7000);
    private String jobTitle = "Fund Accountant";


    @Test
    public void getSalaryCalculation() throws Exception {


        //var actual = mockMvc.perform(post(FINANCE_POST_ENDPOINT)).andExpect(status().isOk()).andExpect(content().string(expected)).andReturn();

    }









}
