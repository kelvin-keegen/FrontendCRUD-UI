package com.appsbykeegan.frontendcrudui.models;


import lombok.Data;

@Data
public class EmployeeModel {

    private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeGender;
    private String startDate;
    private String employeeRole;
    private String emailAddress;
    private DepartmentModel department;

}
