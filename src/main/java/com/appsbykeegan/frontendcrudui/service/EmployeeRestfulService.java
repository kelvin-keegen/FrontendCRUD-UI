package com.appsbykeegan.frontendcrudui.service;

import com.appsbykeegan.frontendcrudui.models.*;
import com.appsbykeegan.frontendcrudui.models.records.EmployeeRequestBody;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeRestfulService {

    private final WebClient.Builder webClient;

    @Value(value = "${backend.url.link}")
    private String apiHost;

    private String backendLink;


    @PostConstruct
    public void postConstruct() {

        backendLink = "http://"+apiHost+"/employee";
    }

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
