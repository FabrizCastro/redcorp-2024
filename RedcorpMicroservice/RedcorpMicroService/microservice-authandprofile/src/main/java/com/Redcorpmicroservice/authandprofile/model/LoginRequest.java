package com.Redcorpmicroservice.authandprofile.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {

    private String employeeEmail;
    private String employeePassword ;
    private String role;
}