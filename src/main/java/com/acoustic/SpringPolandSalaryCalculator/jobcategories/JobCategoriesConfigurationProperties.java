package com.acoustic.SpringPolandSalaryCalculator.jobcategories;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;


@Getter
@PropertySource("classpath:jobs.properties")
@ConfigurationProperties(prefix = "jobs")
@Configuration
public class JobCategoriesConfigurationProperties {


    @Value("#{${jobs.jobTitles}}")
    private Map<String, List<String>> jobDepartmentAndTitles;

}
