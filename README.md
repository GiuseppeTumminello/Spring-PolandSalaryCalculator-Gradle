# Rest API Salary Calculator

The rest api has been created to calculate net amount in pln and display all the taxation applied to the gross amount.
The application can perform 2 GET  and 1 POST html method, the first two are accessible in the following links:
* The GET method show the departments and the job title list avaialable, to check them go to the following endpoint:
  * http://localhost:8080/calculate/getJobDepartment
  * http://localhost:8080/calculate/getJobTitles/{departmentName}
 

* The POST html method is used to calculate the salary and accept optional query parameters, go to the following endpoint to calculate your net or participate to the statistics:

  * http://localhost:8080/calculate/{grossSalaryMonthly}
  * http://localhost:8080/calculate/{grossSalaryMonthly}?departmentName={departmentName}&jobTitleId={id}


 
If the user wants to participate to the statistic add on the url the departmentName and jobTitleId, the endpoint is accessible via http://localhost:8080/calculate/{grossSalaryMonthly}?departmentName={departmentName}&jobTitleId={id} and replace {grossSalaryAmount} with your salary, {departmentName} with the department name listed below and the {jobTitleId} with the jobTitleId listed below, example of endpoint:
  * http://localhost:8080/calculate/6000?departmentName=finance&jobTitleId=9

After provided the parameter, the API will return the following response body:

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
 
if the user wants to participate to the stastics will display also the average value:

* Average 


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
  * 4 - Project Engineer
  * 5 - Sales Engineer
  * 6 - R&D Engineer
  * 7 - Thermal Engineer 
  
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
 


# Setup
* Required:
  * Docker
  * Postgres SQL
  * Java 11 or higher
* Optional
  * PgAdmin4

The application uses Postgres SQL Server, which runs in a Docker container. Please execute the following command to create the containers for Postgres SQL and PgAdmin4

* Docker container postgress command line:
  * docker pull postgres
  * docker docker run -p 5432:5432 -d \                                              
    -e POSTGRES_PASSWORD=password \
    -e POSTGRES_USER=postgres \
    -e POSTGRES_DB=stripe-example \
    -v pgdata:/var/lib/postgresql/data \
    postgres
  

* Docker container PgAdmin4: 
  * docker pull dpage/pgadmin4
  * docker run --name my-own-postgres -e POSTGRES_PASSWORD=postgresmaster -p 5432:5432 -d postgres
  
* Docker compose file: docker-compose-salary-calculator.yml
  * Go to the folder: PolandSalaryCalculator
  * execute: docker-compose -f docker-compose-salary-application.yml up

* Create a table in PostgreSQL by executing the create table query.

  * CREATE TABLE IF NOT EXISTS public.data_salary_calculator
(
    id integer NOT NULL DEFAULT nextval('salary_calculator_spring_id_seq'::regclass),
    pension_zus numeric,
    disability_zus numeric,
    sickness_zus numeric,
    total_zus numeric,
    health numeric,
    annual_gross numeric,
    tax numeric,
    net_monthly numeric,
    annual_net numeric,
    gross_monthly numeric,
    job_title character varying COLLATE pg_catalog."default",
    CONSTRAINT salary_calculator_spring_pkey PRIMARY KEY (id)
)

# How to run from the IDE?

Clone the git repository, open the project with your favorite IDE, go to the DatabaseConfig class, and update the following fields according to your setup:

* HOST
* PASSWORD
* PORT
* USER

Once the database configuration is set, go to the SalaryCalculatorApplication class and run the main method.

# How to run in Docker ?

Clone the git repository and run the follwowing command:

* docker run -it --rm -p 8080:8080 spring-polandsalarycalculator-gradle_salary_calculator





