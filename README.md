# Rest API Salary Calculator

The rest API has been created to calculate the net amount in PLN and display all the taxation applied to the gross amount.

The application can perform 2 GET and 1 POST HTTP methods. The GET methods show the department name and the job titles list available and they are accessible from the following links:

* http://localhost:8080/calculate/getJobDepartments
* http://localhost:8080/calculate/getJobTitles/{departmentName}

  Replace {departmentName} with the job department name.

The POST HTTP method is used to calculate the net salary and accepts optional query parameters. Go to the following endpoint to calculate your net salary or participate in the statistics:

* http://localhost:8080/calculate/{grossSalaryMonthly}
* http://localhost:8080/calculate/{grossSalaryMonthly}?departmentName={departmentName}&jobTitleId={id}

If the user wants to participate in the statistic, add to the url the department name and the job title id. The endpoint is accessible via http://localhost:8080/calculate/{grossSalaryMonthly}?departmentName={departmentName}&jobTitleId={id}. Replace {grossSalaryAmount} with your gross salary, {departmentName} with one of the department names listed below, and the {jobTitleId} with one of the job titles id listed below.

An example of the endpoint:

* http://localhost:8080/calculate/6000?departmentName=finance&jobTitleId=9

Department name:

* It
* Finance
* Engineer
* Restaurant
* Airline

Job title id:

* It:
    * 1 - DevOps Engineer
    * 2 - Software Developer
    * 3 - Software Engineer
    * 4 - Cloud System Engineer
    * 5 - Cloud Architect
    * 6 - IT Analyst
    * 7 - Network Engineer
    * 8 - IT Support Specialist
    * 9 - Database Administrator
    * 10 - System Architect
    * 11 - Web Administrator

* Finance:

    * 1 - Fund Accountant
    * 2 - Depositary
    * 3 - Accountant
    * 4 - Tax Analyst
    * 5 - Auditor
    * 6 - Risk Analyst
    * 7 - Business Analyst
    * 8 - Billing Administrator
    * 9 - Financial Controller

* Engineer:
    * 1 - Mechanical Engineer
    * 2 - Civil Engineer
    * 3 - Project Engineer
    * 4 - Sales Engineer
    * 5 - R&D Engineer
    * 6 - Thermal Engineer

* Restaurant
    * 1 - Executive Chef
    * 2 - Assistant Manager
    * 3 - General Manager
    * 4 - Sous Chef
    * 5 - Pastry Chef
    * 6 - Kitchen Manager
    * 7 - Line Cook
    * 8 - Bartender
    * 9 - Cashier
    * 10 - Dishwasher
    * 11 - Waitress

* Arline
    * 1 - Air Crew
    * 2 - Airline Captain
    * 3 - Airline Pilot
    * 4 - Airport Manager
    * 5 - Analyst
    * 6 - Chief Pilot
    * 7 - Traffic Manager

After providing the parameters, the API will return the following response body:

* Pension Zus amount
* Disability zus amount
* Sickness zus amount
* Total zus amount
* Health amount
* Tax amount
* Yearly gross amount
* Yearly net amount
* Yearly net amount
* Net amount

If the user wants to participate in the statistics will also display the average value.

* Average

# Setup

* Required:
    * Docker


* To create a container in Docker, follow the below instructions:

    * Go to the folder: Spring-PolandSalaryCalculator-Gradle
    * execute: docker-compose -f docker-compose-salary-calculator.yml up





