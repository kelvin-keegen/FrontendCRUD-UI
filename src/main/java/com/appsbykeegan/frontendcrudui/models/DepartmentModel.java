package com.appsbykeegan.frontendcrudui.models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class DepartmentModel {

    private Long departmentId;
    private String departmentName;
    private int departmentFloorNumber;
    private String departmentDescription;
    private BigDecimal departmentBudget;
    private String departmentCreationDate;
    private Set<EmployeeModel> employees;
}
