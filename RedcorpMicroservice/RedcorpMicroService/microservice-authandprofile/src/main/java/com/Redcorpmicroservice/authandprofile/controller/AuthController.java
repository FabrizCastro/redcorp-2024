package com.Redcorpmicroservice.authandprofile.controller;

import com.Redcorpmicroservice.authandprofile.model.AuthResponse;
import com.Redcorpmicroservice.authandprofile.model.LoginRequest;
import com.Redcorpmicroservice.authandprofile.model.dto.EmployeeRequest;
import com.Redcorpmicroservice.authandprofile.repository.EmployeeRepository;
import com.Redcorpmicroservice.authandprofile.service.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/redcorp/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final EmployeeRepository employeeRepository;

    public AuthController(AuthService authService, EmployeeRepository employeeRepository){
        this.authService = authService;
        this.employeeRepository = employeeRepository;
    }

    // URL: http://localhost:8080/api/cyclescape/v1/auth/register
    // Method: POST
    @Transactional
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody EmployeeRequest request) {
        authService.existsUserByEmail(request);
        authService.validateRegisterRequest(request);
        AuthResponse registeredUser = authService.register(request);
        return new ResponseEntity<AuthResponse>(registeredUser, HttpStatus.CREATED);
    }

    // URL: http://localhost:8080/api/cyclescape/v1/auth/login
    // Method: POST
    @Transactional(readOnly = true)
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

        AuthResponse loggedUser = authService.login(request);


        return new ResponseEntity<AuthResponse>(loggedUser, HttpStatus.OK);
    }
}
