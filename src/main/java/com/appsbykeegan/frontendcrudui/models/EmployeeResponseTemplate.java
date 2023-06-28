package com.appsbykeegan.frontendcrudui.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseTemplate {

    private int statusCode;
    private String message;
    private EmployeeModel data;
}
