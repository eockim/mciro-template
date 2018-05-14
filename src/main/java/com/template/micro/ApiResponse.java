package com.template.micro;

import lombok.Data;

@Data
public class ApiResponse {

    private int code;
    private String type;
    private String message;
    private String detail;
}


