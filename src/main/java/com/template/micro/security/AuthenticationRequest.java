package com.template.micro.security;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String userId;
    private String password;

}
