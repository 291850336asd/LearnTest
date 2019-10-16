package com.meng.jdk8.lambda.objectfunctioninterface.example4Optional;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class Option2Test {

    public static void main(String[] args) {
        Employee employee = new Employee();
        Employee employee2 = new Employee();
        employee.setName("em1");
        employee2.setName("em2");
        List<Employee> employeeList = Arrays.asList(employee, employee2);
        Company company = new Company();
        company.setName("company");
        company.setEmployeeList(employeeList);

        List<Employee> companyEmployeeList = company.getEmployeeList();
        if(companyEmployeeList == null){
            companyEmployeeList = new ArrayList<>();
        }

        Optional<Company> optionalCompany = Optional.of(company);
        companyEmployeeList =optionalCompany.map(theCompany -> theCompany.getEmployeeList()).orElse(Collections.emptyList());
    }

    @Getter
    @Setter
    private static class Company{
        private String name;
        private List<Employee> employeeList;
    }

    @Getter
    @Setter
    private static class Employee{
        private String name;
    }
}
