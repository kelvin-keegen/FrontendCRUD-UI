package com.appsbykeegan.frontendcrudui.service;

import com.appsbykeegan.frontendcrudui.models.*;
import com.appsbykeegan.frontendcrudui.models.records.EmployeeRequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeRestfulService {

    private final WebClient.Builder webClient;

    private String backendLink = "http://localhost:7070/employee";

    public EmployeeModel findEmployee(String email) {

        EmployeeResponseTemplate response = webClient.build()
                .get()
                .uri(backendLink+"/retrieve?email="+email)
                .retrieve()
                .bodyToMono(EmployeeResponseTemplate.class)
                .block();

        if (response == null) {
            return null;
        }
        int statusCode = response.getStatusCode();

        if (statusCode != 200) {
            return null;
        }

        log.info(response.getData().toString());
        log.info(response.getData().getDepartment().getDepartmentName());

        return response.getData();
    }
    public List<EmployeeModel> getAllEmployees() {

        List<EmployeeModel> employees;

        EmployeeListResponseTemplate response = webClient.build()
                .get()
                .uri(backendLink+"/retrieve")
                .retrieve()
                .bodyToMono(EmployeeListResponseTemplate.class)
                .block();

        if (response == null) {
            return null;
        }
        int statusCode = response.getStatusCode();

        if (statusCode != 200) {
            return null;
        }
        employees = response.getData();

        return employees;
    }

    public boolean createEmployee(EmployeeRequestBody requestBody) {

        EmployeeResponseTemplate response = webClient.build()
                .post()
                .uri(backendLink+"/create")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(EmployeeResponseTemplate.class)
                .block();

        if (response == null) {

            return false;
        }

        int statusCode = response.getStatusCode();

        if (statusCode != 200) {

            return false;
        }

        return true;
    }

    public boolean deleteEmployee(String email) {

        EmployeeResponseTemplate response = webClient.build()
                .delete()
                .uri(backendLink+"/delete?email="+email)
                .retrieve()
                .bodyToMono(EmployeeResponseTemplate.class)
                .block();

        if (response == null) {

            return false;
        }

        int statusCode = response.getStatusCode();

        if (statusCode != 200) {

            return false;
        }

        return true;
    }

    public boolean UpdateEmployee(EmployeeRequestBody requestBody) {

        EmployeeResponseTemplate response = webClient.build()
                .put()
                .uri(backendLink+"/update")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(EmployeeResponseTemplate.class)
                .block();

        if (response == null) {

            return false;
        }
        int statusCode = response.getStatusCode();

        if (statusCode != 200) {

            return false;
        }
        return true;
    }

}
