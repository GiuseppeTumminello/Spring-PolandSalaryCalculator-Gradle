package com.acoustic.SpringPolandSalaryCalculator.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
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
import com.acoustic.SpringPolandSalaryCalculator.jobcategories.JobCategories;
import com.acoustic.SpringPolandSalaryCalculator.service.DataSalaryCalculatorRepository;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@Validated
public class SalaryCalculatorController {

    public static final int MINIMUM_GROSS = 2000;
    private final DataSalaryCalculatorRepository dataSalaryCalculatorRepository;
    private final List<SalaryCalculatorService> salaryCalculatorService;
    private final JobCategories jobCategories;


    @GetMapping("/getJobTitles/{departmentName}")
    public String[] getJobTitles(
            @PathVariable
            String departmentName) {
        return this.jobCategories.getJobDepartmentAndTitles().get(departmentName).split(",");
    }

    @GetMapping("/getJobDepartment")
    public Set<String> getDepartmentName() {
        return this.jobCategories.getJobDepartmentAndTitles().keySet();
    }


    @PostMapping("/calculator/{grossMonthlySalary}")

    public Map<String, BigDecimal> getSalaryCalculation(
            @PathVariable
            @Min(value = MINIMUM_GROSS,
                    message = "Must be Greater than or equal to 2000.00") @NotNull  BigDecimal grossMonthlySalary,
            @RequestParam(defaultValue = "0", required = false)
            @NotNull String departmentName,
            @RequestParam(defaultValue = "0", required = false)
            @NotNull int jobTitleId) {
        var response = this.salaryCalculatorService.stream()
                .collect(Collectors.toMap(SalaryCalculatorService::getDescription, e -> e.apply(grossMonthlySalary)));
        if (this.jobCategories.getJobDepartmentAndTitles().containsKey(departmentName.toLowerCase())) {
            BigDecimal average = statistic(departmentName, jobTitleId, grossMonthlySalary);
            if (average != null) {
                response.put("Average", average);
            }
        }
        return response;
    }


    public BigDecimal statistic(String departmentName, int jobTitleId, BigDecimal grossMonthlySalary) {
        List<String> jobTitlesList = List.of(this.jobCategories.getJobDepartmentAndTitles()
                .get(departmentName)
                .split(","));
        if (jobTitleId <= jobTitlesList.size() && jobTitleId >= 1) {
            this.dataSalaryCalculatorRepository.save(buildDataSalaryCalculator(grossMonthlySalary,
                    jobTitlesList.get(jobTitleId - 1)));
            return this.dataSalaryCalculatorRepository.findAverageByJobTitle(jobTitlesList.get(jobTitleId - 1));

        } else {
            return null;
        }

    }


    private DataSalaryCalculator buildDataSalaryCalculator(BigDecimal grossMonthlySalary, String jobTitle) {
        return DataSalaryCalculator.builder()
                .annualGross(this.salaryCalculatorService.get(0).apply(grossMonthlySalary))
                .annualNet(this.salaryCalculatorService.get(1).apply(grossMonthlySalary))
                .disabilityZus(this.salaryCalculatorService.get(2).apply(grossMonthlySalary))
                .health(this.salaryCalculatorService.get(3).apply(grossMonthlySalary))
                .netMonthly(this.salaryCalculatorService.get(4).apply(grossMonthlySalary))
                .pensionZus(this.salaryCalculatorService.get(5).apply(grossMonthlySalary))
                .sicknessZus(this.salaryCalculatorService.get(6).apply(grossMonthlySalary))
                .tax(this.salaryCalculatorService.get(7).apply(grossMonthlySalary))
                .totalZus(this.salaryCalculatorService.get(8).apply(grossMonthlySalary))
                .grossMonthly(grossMonthlySalary)
                .jobTitle(jobTitle)
                .build();
    }

}
