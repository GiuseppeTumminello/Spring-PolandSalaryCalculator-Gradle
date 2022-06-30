package com.acoustic.SpringPolandSalaryCalculator.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acoustic.SpringPolandSalaryCalculator.calculatorservicetest.SalaryCalculatorService;
import com.acoustic.SpringPolandSalaryCalculator.entity.DataSalaryCalculator;
import com.acoustic.SpringPolandSalaryCalculator.jobcategories.JobCategoriesConfigurationProperties;
import com.acoustic.SpringPolandSalaryCalculator.service.DataSalaryCalculatorRepository;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@Validated
public class SalaryCalculatorController {

    public static final int MINIMUM_GROSS = 2000;
    private final DataSalaryCalculatorRepository dataSalaryCalculatorRepository;
    private final List<SalaryCalculatorService> salaryCalculatorService;
    private final JobCategoriesConfigurationProperties jobCategoriesConfigurationProperties;


    @GetMapping("/getJobTitles/{departmentName}")
    public String[] getJobTitles(
            @PathVariable
            String departmentName) {
        return this.jobCategoriesConfigurationProperties.getJobDepartmentAndTitles().get(departmentName).split(",");
    }

    @GetMapping("/getJobDepartments")
    public Set<String> getDepartmentName() {
        return this.jobCategoriesConfigurationProperties.getJobDepartmentAndTitles().keySet();
    }


    @PostMapping("/calculator/{grossMonthlySalary}")
    public Map<String, BigDecimal> calculateSalary(
            @PathVariable
            @Min(value = MINIMUM_GROSS,
                    message = "Must be Greater than or equal to 2000.00") @NotNull BigDecimal grossMonthlySalary,
            @RequestParam(required = false)
            String departmentName,
            @RequestParam(required = false)
            Integer jobTitleId) {
        Map<String, BigDecimal> response = new LinkedHashMap<>();
        responseWithoutStatistics(grossMonthlySalary, response);
        if (departmentName == null || jobTitleId == null) {
            return response;
        }
        return getAverage(grossMonthlySalary, departmentName, jobTitleId, response);

    }

    private void responseWithoutStatistics(BigDecimal grossMonthlySalary, Map<String, BigDecimal> response) {
        salaryCalculatorService.sort(Comparator.comparingInt(SalaryCalculatorService::getOrder));
        BigDecimal grossMonthlySalaryMinusTaxes = grossMonthlySalary;
        BigDecimal tax = null;
        final int taxesOrder = 3;
        final int netOrder = 4;
        for (var service : salaryCalculatorService) {
            if (service.getOrder() > 5) {
                response.put(service.getDescription(), service.apply(grossMonthlySalary).setScale(2, RoundingMode.HALF_EVEN));
            } else {
                response.put(service.getDescription(), service.apply(grossMonthlySalaryMinusTaxes).setScale(2, RoundingMode.HALF_EVEN));

                if (service.getOrder() == taxesOrder) {
                    response.put(service.getDescription(), tax = service.apply(grossMonthlySalary));
                    continue;
                }
                if (service.getOrder() == netOrder) {
                    response.put(service.getDescription(), service.apply(grossMonthlySalaryMinusTaxes = grossMonthlySalaryMinusTaxes.subtract(tax)));
                    continue;
                }
                grossMonthlySalaryMinusTaxes = grossMonthlySalaryMinusTaxes.subtract(service.apply(grossMonthlySalaryMinusTaxes));
            }
        }
    }

    private Map<String, BigDecimal> getAverage(
            BigDecimal grossMonthlySalary, String departmentName, int jobTitleId, Map<String, BigDecimal> response) {
        if (this.jobCategoriesConfigurationProperties.getJobDepartmentAndTitles()
                .containsKey(departmentName.toLowerCase())) {
            BigDecimal average = statistic(departmentName, jobTitleId, grossMonthlySalary);
            if (average != null) {
                response.put("Average", average.setScale(2, RoundingMode.HALF_EVEN));
                return response;
            }
        }
        throw new IllegalArgumentException("Invalid department name");
    }


    public BigDecimal statistic(String departmentName, int jobTitleId, BigDecimal grossMonthlySalary) {
        List<String> jobTitlesList = List.of(this.jobCategoriesConfigurationProperties.getJobDepartmentAndTitles()
                .get(departmentName)
                .split(","));
        if (jobTitleId <= jobTitlesList.size() && jobTitleId >= 1) {
            this.dataSalaryCalculatorRepository.save(DataSalaryCalculator.builder()
                    .grossMonthly(grossMonthlySalary)
                    .jobTitle(jobTitlesList.get(jobTitleId - 1))
                    .build());
            return this.dataSalaryCalculatorRepository.findAverageByJobTitle(jobTitlesList.get(jobTitleId - 1));

        }
        throw new IllegalArgumentException("Wrong job id");

    }

}
