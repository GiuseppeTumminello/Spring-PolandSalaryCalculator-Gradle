package com.acoustic.SpringPolandSalaryCalculator.controller;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.acoustic.SpringPolandSalaryCalculator.jobcategories.JobCategoriesConfigurationProperties;
import com.fasterxml.jackson.databind.ObjectMapper;


@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
public class SalaryCalculatorControllerGetTest {

    public static final String AIRLINE = "airline";
    public static final String RESTAURANT = "restaurant";
    public static final String ENGINEER = "engineer";
    public static final String FINANCE = "finance";
    public static final String IT = "it";
    private static final String DEPARTMENT_NAME_ENDPOINT = "/getJobDepartments";
    private static final String IT_JOB_TITLE_ENDPOINT = "/getJobTitles/"+IT;
    private static final String FINANCE_JOB_TITLE_ENDPOINT = "/getJobTitles/"+FINANCE;
    private static final String ENGINEER_JOB_TITLE_ENDPOINT = "/getJobTitles/"+ENGINEER;
    private static final String RESTAURANT_JOB_TITLE_ENDPOINT = "/getJobTitles/"+RESTAURANT;
    private static final String AIRLINE_JOB_TITLE_ENDPOINT = "/getJobTitles/"+AIRLINE;


    @Autowired
    private JobCategoriesConfigurationProperties jobCategoriesConfigurationProperties;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;



    @Test
    public void getDepartmentName() throws Exception {
        String expected = this.objectMapper.writeValueAsString(this.jobCategoriesConfigurationProperties.getJobDepartmentAndTitles()
                .entrySet()
                .stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList()));


        var actual = this.mockMvc.perform(get(DEPARTMENT_NAME_ENDPOINT).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expected))
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertThat(actual.length()).isEqualTo(expected.length());

    }


    @Test
    public void getJobTitlesIt() throws Exception {
        String expected = this.objectMapper.writeValueAsString(List.of(this.jobCategoriesConfigurationProperties.getJobDepartmentAndTitles()
                .get(IT)
                .split(",")));
        var actual = this.mockMvc.perform(get(IT_JOB_TITLE_ENDPOINT).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expected))
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertThat(actual.length()).isEqualTo(expected.length());

    }

    @Test
    public void getJobTitlesFinance() throws Exception {
        String expected = this.objectMapper.writeValueAsString(List.of(this.jobCategoriesConfigurationProperties.getJobDepartmentAndTitles()
                .get(FINANCE)
                .split(",")));
        var actual = this.mockMvc.perform(get(FINANCE_JOB_TITLE_ENDPOINT).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expected))
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertThat(actual.length()).isEqualTo(expected.length());

    }

    @Test
    public void getJobTitlesEngineer() throws Exception {
        String expected = this.objectMapper.writeValueAsString(List.of(this.jobCategoriesConfigurationProperties.getJobDepartmentAndTitles()
                .get(ENGINEER)
                .split(",")));
        var actual = this.mockMvc.perform(get(ENGINEER_JOB_TITLE_ENDPOINT).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expected))
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertThat(actual.length()).isEqualTo(expected.length());

    }

    @Test
    public void getJobTitlesRestaurant() throws Exception {
        String expected = this.objectMapper.writeValueAsString(List.of(this.jobCategoriesConfigurationProperties.getJobDepartmentAndTitles()
                .get(RESTAURANT)
                .split(",")));
        var actual = this.mockMvc.perform(get(RESTAURANT_JOB_TITLE_ENDPOINT).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expected))
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertThat(actual.length()).isEqualTo(expected.length());

    }


    @Test
    public void getJobTitlesAirline() throws Exception {
        String expected = this.objectMapper.writeValueAsString(List.of(this.jobCategoriesConfigurationProperties.getJobDepartmentAndTitles()
                .get(AIRLINE)
                .split(",")));
        var actual = this.mockMvc.perform(get(AIRLINE_JOB_TITLE_ENDPOINT).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expected))
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertThat(actual.length()).isEqualTo(expected.length());

    }

}
