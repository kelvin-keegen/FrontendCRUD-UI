package com.appsbykeegan.frontendcrudui.service;

import com.appsbykeegan.frontendcrudui.models.EmployeeModel;
import com.appsbykeegan.frontendcrudui.models.DepartmentResponseTemplate;
import com.appsbykeegan.frontendcrudui.models.records.EmployeeRequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeRestfulService {

    private final WebClient.Builder webClient;

    private String backendLink = "http://localhost:7070/employee";

    public List<EmployeeModel> getAllEmployees() {

        List<EmployeeModel> employees = new ArrayList<>();

        DepartmentResponseTemplate response = webClient.build()
                .get()
                .uri(backendLink+"/retrieve")
                .retrieve()
                .bodyToMono(DepartmentResponseTemplate.class)
                .block();

        employees = (List<EmployeeModel>) response.getData();

        log.info(Arrays.toString(employees.toArray()));

        return employees;
    }

    public boolean createEmployee(EmployeeRequestBody requestBody) {

        DepartmentResponseTemplate response = webClient.build()
                .post()
                .uri(backendLink+"/create")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(DepartmentResponseTemplate.class)
                .block();

        if (response == null) {

            return false;
        }

        int statusCode = (int) response.getStatusCode();

        if (statusCode != 200) {

            return false;
        }

        return true;
    }

    public boolean deleteEmployee(String emailAddress) {

        DepartmentResponseTemplate response = webClient.build()
                .delete()
                .uri(backendLink+"/delete?name="+emailAddress)
                .retrieve()
                .bodyToMono(DepartmentResponseTemplate.class)
                .block();

        if (response == null) {

            return false;
        }

        int statusCode = (int) response.getStatusCode();

        if (statusCode != 200) {

            return false;
        }

        return true;
    }

    public boolean UpdateEmployee(EmployeeModel employeeModel) {

        EmployeeRequestBody requestBody = new EmployeeRequestBody(
                employeeModel.getEmployeeFirstName(),
                employeeModel.getEmployeeLastName(),
                employeeModel.getEmployeeGender(),
                employeeModel.getEmployeeRole(),
                employeeModel.getEmailAddress(),
                null

        );

        DepartmentResponseTemplate response = webClient.build()
                .put()
                .uri(backendLink+"/update")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(DepartmentResponseTemplate.class)
                .block();

        if (response == null) {

            return false;
        }

        int statusCode = (int) response.getStatusCode();

        if (statusCode != 200) {

            return false;
        }

        return true;

    }

}
