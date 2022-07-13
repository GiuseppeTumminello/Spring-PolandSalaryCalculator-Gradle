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

import com.acoustic.SpringPolandSalaryCalculator.calculatorservice.SalaryCalculatorService;
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
    private final List<SalaryCalculatorService> salaryCalculatorServices;
    private final JobCategoriesConfigurationProperties jobCategoriesConfigurationProperties;


    @GetMapping("/jobs/{departmentName}")
    public List<String> getJobTitles(
            @PathVariable
            String departmentName) {
        return this.jobCategoriesConfigurationProperties.getJobDepartmentAndTitles().get(departmentName);
    }

    @GetMapping("/departments")
    public Set<String> getDepartmentName() {
        return this.jobCategoriesConfigurationProperties.getJobDepartmentAndTitles().keySet();
    }


    @PostMapping("/salary-calculations/{grossMonthlySalary}")
    public Map<String, BigDecimal> calculateSalary(@PathVariable @Min(value = MINIMUM_GROSS, message = "Must be Greater than or equal to 2000.00") @NotNull BigDecimal grossMonthlySalary, @RequestParam(required = false) String departmentName, @RequestParam(required = false) Integer jobTitleId) {
        var response = responseWithoutStatistics(grossMonthlySalary);
        if (departmentName == null || jobTitleId == null) {
            return response;
        }
        List<String> jobTitlesList = this.jobCategoriesConfigurationProperties.getJobDepartmentAndTitles().get(departmentName);
        if (!this.jobCategoriesConfigurationProperties.getJobDepartmentAndTitles().containsKey(departmentName.toLowerCase())) {
            throw new IllegalArgumentException("Invalid department name");
        }

        if (jobTitleId > jobTitlesList.size() || jobTitleId <= 0) {
            throw new IllegalArgumentException("Wrong job id");
        }

        return getAverage(grossMonthlySalary, jobTitlesList.get(jobTitleId - 1), response);

    }

    private Map<String, BigDecimal> responseWithoutStatistics(BigDecimal grossMonthlySalary) {
        Map<String, BigDecimal> response = new LinkedHashMap<>();
        salaryCalculatorServices.sort(Comparator.comparingInt(SalaryCalculatorService::getCalculationOrder));
        BigDecimal grossMonthlySalaryMinusTaxes = grossMonthlySalary;
        BigDecimal tax = null;
        final int taxesOrder = 3;
        final int netOrder = 4;
        for (var service : salaryCalculatorServices) {
            if (service.getCalculationOrder() > 5) {
                response.put(service.getDescription(), service.apply(grossMonthlySalary).setScale(2, RoundingMode.HALF_EVEN));
            } else {
                response.put(service.getDescription(), service.apply(grossMonthlySalaryMinusTaxes).setScale(2, RoundingMode.HALF_EVEN));

                if (service.getCalculationOrder() == taxesOrder) {
                    tax = service.apply(grossMonthlySalary);
                    response.put(service.getDescription(), tax);
                    continue;
                }
                if (service.getCalculationOrder() == netOrder) {
                    response.put(service.getDescription(), service.apply(grossMonthlySalaryMinusTaxes = grossMonthlySalaryMinusTaxes.subtract(tax)));
                    continue;
                }
                grossMonthlySalaryMinusTaxes = grossMonthlySalaryMinusTaxes.subtract(service.apply(grossMonthlySalaryMinusTaxes));
            }
        }
        return response;
    }

    private Map<String, BigDecimal> getAverage(BigDecimal grossMonthlySalary, String jobTitleId, Map<String, BigDecimal> response) {
        BigDecimal average = statistic(jobTitleId, grossMonthlySalary);
        response.put("Average", average.setScale(2, RoundingMode.HALF_EVEN));
        return response;

    }

    private BigDecimal statistic(String jobTitleId, BigDecimal grossMonthlySalary) {
        this.dataSalaryCalculatorRepository.save(DataSalaryCalculator.builder().grossMonthly(grossMonthlySalary).jobTitle(jobTitleId).build());
        return this.dataSalaryCalculatorRepository.findAverageByJobTitle((jobTitleId));
    }

}
