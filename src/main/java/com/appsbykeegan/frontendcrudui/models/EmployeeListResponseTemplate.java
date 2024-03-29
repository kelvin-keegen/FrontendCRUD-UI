package com.appsbykeegan.frontendcrudui.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeListResponseTemplate {

    private int statusCode;
    private String message;
    private List<EmployeeModel> data;
}
