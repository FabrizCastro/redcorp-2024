package com.Redcorpmicroservice.authandprofile.service;

import com.Redcorpmicroservice.authandprofile.model.AuthResponse;
import com.Redcorpmicroservice.authandprofile.model.LoginRequest;
import com.Redcorpmicroservice.authandprofile.model.dto.EmployeeRequest;


public interface AuthService {
    public abstract AuthResponse register(EmployeeRequest registerRequest);

    public abstract AuthResponse login(LoginRequest loginRequest);

    public void validateRegisterRequest(EmployeeRequest registerRequest);
    public void existsUserByEmail(EmployeeRequest registerRequest);
}
