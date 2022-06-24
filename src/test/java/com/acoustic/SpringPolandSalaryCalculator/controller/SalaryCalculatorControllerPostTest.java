package com.acoustic.SpringPolandSalaryCalculator.controller;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.acoustic.SpringPolandSalaryCalculator.jobcategories.JobCategories;
import com.acoustic.SpringPolandSalaryCalculator.service.DataSalaryCalculatorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class SalaryCalculatorControllerPostTest {

    @Autowired
    private DataSalaryCalculatorRepository dataSalaryCalculatorRepository;
    @Autowired
    private JobCategories jobCategories;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private List<SalaryCalculatorService> salaryCalculatorService;


    private Map<String, BigDecimal> statisticResponseBody(BigDecimal grossMonthlySalary, String departmentName, int jobTitleId) {
        Map<String, BigDecimal> expected = new HashMap<>();
        this.salaryCalculatorService.forEach(service -> expected.put(service.getDescription(), service.apply(grossMonthlySalary)));
        List<String> jobTitlesList = List.of(this.jobCategories.getJobDepartmentAndTitles().get(departmentName).split(","));
            expected.put("Average", this.dataSalaryCalculatorRepository.findAverageByJobTitle(jobTitlesList.get(jobTitleId - 1).trim()));
         return expected;
    }


    private Map<String, BigDecimal> getSalaryCalculationResponseBody(BigDecimal grossMonthlySalary) {
        Map<String, BigDecimal> expected = new HashMap<>();
        this.salaryCalculatorService.forEach(service -> expected.put(service.getDescription(), service.apply(grossMonthlySalary)));
        return expected;
    }


    @ParameterizedTest
    @CsvSource({"6000, finance, 1", "7000, it, 2", "15891.68, airline, 1","7700, restaurant, 6", "12191.68, it, 10", "185891.68, finance, 1" })
    public void getSalaryCalculation(BigDecimal grossMonthlySalary, String departmentName, int jobTitleId) throws Exception {
         this.mockMvc.perform(post("/calculate/" +grossMonthlySalary + "?departmentName=" + departmentName + "&jobTitleId=" +jobTitleId)).andExpect(status().isOk()).andExpect(
                MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(statisticResponseBody(grossMonthlySalary, departmentName, jobTitleId))));


    }

    @ParameterizedTest
    @CsvSource({"6000, finance, -10", "7000, it, -1000", "15891.68, airline, 11","7700, restaurant, 20", "12191.68, it, 30", "185891.68, finance, 40" })
    public void getSalaryCalculationIdOutOfBounds(BigDecimal grossMonthlySalary, String departmentName, int jobTitleId)  {

      Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> this.mockMvc.perform(post("/calculate/" +grossMonthlySalary + "?departmentName=" + departmentName + "&jobTitleId=" +jobTitleId)).andExpect(status().isOk()).andExpect(
             MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(statisticResponseBody(grossMonthlySalary, departmentName, jobTitleId)))));
    }


    @ParameterizedTest
    @CsvSource({"6000, fff, 1", "7000, its, 1", "15891.68, airlines, 1","7700, restaurants, 1", "12191.68, rest, 2", "185891.68, finances, 3" })
    public void getSalaryCalculationWrongDepartmentName(BigDecimal grossMonthlySalary, String departmentName, int jobTitleId) {

        Assertions.assertThrows(NullPointerException.class, () -> this.mockMvc.perform(post("/calculate/" +grossMonthlySalary + "?departmentName=" + departmentName + "&jobTitleId=" +jobTitleId)).andExpect(status().isOk()).andExpect(
                MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(statisticResponseBody(grossMonthlySalary, departmentName, jobTitleId)))));
    }


    @ParameterizedTest
    @CsvSource({"-6000, finance, 1", "-7000, it, 1", "-15891.68, airline, 1","-7700, restaurant, 1", "1999.9999, it, 2", "0, finance, 3" })
    public void getSalaryCalculationGrossBelowTrashHold(BigDecimal grossMonthlySalary, String departmentName, int jobTitleId) {

        Assertions.assertThrows(NestedServletException.class, () -> this.mockMvc.perform(post("/calculate/" +grossMonthlySalary + "?departmentName=" + departmentName + "&jobTitleId=" +jobTitleId)).andExpect(status().isOk()).andExpect(
                MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(statisticResponseBody(grossMonthlySalary, departmentName, jobTitleId)))));
    }


    @ParameterizedTest
    @CsvSource({"6000", "7000", "15891.68", "7700", "2999.9999"})
    public void getSalaryCalculationGrossNoStatistic(BigDecimal grossMonthlySalary) throws Exception {
            this.mockMvc.perform(post("/calculate/" +grossMonthlySalary)).andExpect(status().isOk()).andExpect(
                    MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(getSalaryCalculationResponseBody(grossMonthlySalary))));

    }

}
