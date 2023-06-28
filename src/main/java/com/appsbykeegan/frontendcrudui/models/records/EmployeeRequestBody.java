package com.appsbykeegan.frontendcrudui.models.records;


public record EmployeeRequestBody(
        String employeeFirstName,
        String employeeLastName,
        String employeeGender,
        String employeeRole,
        String emailAddress,
        String departmentName
) {
}
