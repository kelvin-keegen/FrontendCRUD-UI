package com.appsbykeegan.frontendcrudui.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {

    private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeGender;
    private String startDate;
    private String employeeRole;
    private String emailAddress;
    private DepartmentModel department;

    public String getDepartmentName() {
        return department.getDepartmentName();
    }

}
