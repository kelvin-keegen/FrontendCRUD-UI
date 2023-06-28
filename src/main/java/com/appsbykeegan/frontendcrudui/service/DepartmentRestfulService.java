package com.appsbykeegan.frontendcrudui.service;

import com.appsbykeegan.frontendcrudui.models.DepartmentModel;
import com.appsbykeegan.frontendcrudui.models.DepartmentListResponseTemplate;
import com.appsbykeegan.frontendcrudui.models.DepartmentResponseTemplate;
import com.appsbykeegan.frontendcrudui.models.records.DepartmentRequestBody;
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
public class DepartmentRestfulService {

    private final WebClient.Builder webClient;

    private String backendLink = "http://localhost:7070/department";

    public DepartmentModel findDepartment(String deptName) {

        DepartmentResponseTemplate response = webClient.build()
                .get()
                .uri(backendLink+"/retrieve?name="+deptName)
                .retrieve()
                .bodyToMono(DepartmentResponseTemplate.class)
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
    public List<DepartmentModel> getAllDepartments() {

        List<DepartmentModel> departments;

        DepartmentListResponseTemplate response = webClient.build()
                .get()
                .uri(backendLink+"/retrieve")
                .retrieve()
                .bodyToMono(DepartmentListResponseTemplate.class)
                .block();

        if (response == null) {
            return null;
        }
        int statusCode = response.getStatusCode();

        if (statusCode != 200) {
            return null;
        }
        departments = response.getData();

        return departments;
    }

    public boolean createDepartment(DepartmentRequestBody requestBody) {

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

        int statusCode = response.getStatusCode();

        if (statusCode != 200) {

            return false;
        }

        return true;
    }

    public boolean deleteDepartment(String departmentName) {

        DepartmentResponseTemplate response = webClient.build()
                .delete()
                .uri(backendLink+"/delete?name="+departmentName)
                .retrieve()
                .bodyToMono(DepartmentResponseTemplate.class)
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

    public boolean UpdateDepartment(DepartmentRequestBody requestBody) {

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
        int statusCode = response.getStatusCode();

        if (statusCode != 200) {

            return false;
        }
        return true;
    }
}
