package com.appsbykeegan.frontendcrudui.models;

import lombok.Data;

@Data
public class ResponseTemplate {

    private Object statusCode;
    private String message;
    private Object data;
}
