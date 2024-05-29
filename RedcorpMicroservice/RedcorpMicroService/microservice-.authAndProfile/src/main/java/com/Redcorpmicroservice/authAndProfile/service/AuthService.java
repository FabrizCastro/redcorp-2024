package com.Redcorpmicroservice.authAndProfile.service;

import com.Redcorpmicroservice.authAndProfile.model.AuthResponse;
import com.Redcorpmicroservice.authAndProfile.model.LoginRequest;
import com.Redcorpmicroservice.authAndProfile.model.dto.EmployeeRequest;


public interface AuthService {
    public abstract AuthResponse register(EmployeeRequest registerRequest);

    public abstract AuthResponse login(LoginRequest loginRequest);

    public void validateRegisterRequest(EmployeeRequest registerRequest);
    public void existsUserByEmail(EmployeeRequest registerRequest);
}
