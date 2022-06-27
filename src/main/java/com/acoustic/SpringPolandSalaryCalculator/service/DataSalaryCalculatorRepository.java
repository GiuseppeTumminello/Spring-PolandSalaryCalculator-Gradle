package com.acoustic.SpringPolandSalaryCalculator.service;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.acoustic.SpringPolandSalaryCalculator.entity.DataSalaryCalculator;

@Repository
public interface DataSalaryCalculatorRepository extends JpaRepository<DataSalaryCalculator, Integer> {

    @Query(value = "select avg(gross_monthly) from data_salary_calculator where job_title=:jobTitle", nativeQuery = true)
    BigDecimal findAverageByJobTitle(@Param("jobTitle") String jobTitle);





}
