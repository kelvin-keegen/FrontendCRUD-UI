package com.appsbykeegan.frontendcrudui.service;

import com.appsbykeegan.frontendcrudui.models.DepartmentModel;
import com.appsbykeegan.frontendcrudui.models.ResponseTemplate;
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

    public List<DepartmentModel> getAllDepartments() {

        List<DepartmentModel> departments = new ArrayList<>();

        ResponseTemplate response = webClient.build()
                .get()
                .uri(backendLink+"/retrieve")
                .retrieve()
                .bodyToMono(ResponseTemplate.class)
                .block();

        departments = (List<DepartmentModel>) response.getData();

        log.info(Arrays.toString(departments.toArray()));

        return departments;
    }

    public boolean createDepartment(DepartmentRequestBody requestBody) {

        ResponseTemplate response = webClient.build()
                .post()
                .uri(backendLink+"/create")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(ResponseTemplate.class)
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

    public boolean deleteDepartment(String departmentName) {

        ResponseTemplate response = webClient.build()
                .delete()
                .uri(backendLink+"/delete?name="+departmentName)
                .retrieve()
                .bodyToMono(ResponseTemplate.class)
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

    public boolean UpdateDepartment(DepartmentModel departmentModel) {

        DepartmentRequestBody requestBody = new DepartmentRequestBody(
                departmentModel.getDepartmentName(),
                departmentModel.getDepartmentFloorNumber(),
                departmentModel.getDepartmentDescription(),
                departmentModel.getDepartmentBudget()
        );

        ResponseTemplate response = webClient.build()
                .put()
                .uri(backendLink+"/create")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(ResponseTemplate.class)
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
