package com.appsbykeegan.frontendcrudui.models.records;

import java.math.BigDecimal;

public record DepartmentRequestBody(
        String departmentName,
        int departmentFloorNumber,
        String departmentDescription,
        BigDecimal departmentBudget

) {
}
