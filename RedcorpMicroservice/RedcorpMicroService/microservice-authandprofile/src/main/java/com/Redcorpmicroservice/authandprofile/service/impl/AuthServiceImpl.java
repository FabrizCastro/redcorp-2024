package com.Redcorpmicroservice.authandprofile.service.impl;

import com.Redcorpmicroservice.authandprofile.exception.ValidationException;
import com.Redcorpmicroservice.authandprofile.model.*;
import com.Redcorpmicroservice.authandprofile.model.dto.EmployeeRequest;
import com.Redcorpmicroservice.authandprofile.repository.EmployeeRepository;
import com.Redcorpmicroservice.authandprofile.service.AuthService;
import lombok.AllArgsConstructor;
import org.apache.sshd.common.config.keys.loader.openssh.kdf.BCrypt;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final EmployeeRepository employeeRepository;

    private final JwtServiceImpl jwtService;

    @Override
    public AuthResponse register(EmployeeRequest registerRequest) {
        var employee = Employee.builder()
                .employeeFirstName(registerRequest.getEmployeeFirstName())
                .employeeLastName(registerRequest.getEmployeeLastName())
                .employeeDni(registerRequest.getEmployeeDni())
                .employeeEmail(registerRequest.getEmployeeEmail())
                .employeePassword(BCrypt.hashpw(registerRequest.getEmployeePassword(), BCrypt.gensalt()))
                .employeeArea(registerRequest.getEmployeeArea())
                .employeeCargo(registerRequest.getEmployeeCargo())
                .employeeActive(true)
                .sectionId(registerRequest.getSectionId())
                .role(Roles.USER)
                .build();
        var savedUser = employeeRepository.save(employee);
        var jwtToken = jwtService.generate(employee.getId().toString(),employee.getRole().toString(),"ACCESS");
        var refreshToken = jwtService.generate(employee.getId().toString(),employee.getRole().toString(),"REFRESH");
        return AuthResponse.builder()
                .user_id(employee.getId())
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();

    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        // Intentar con la consulta JPQL
        Employee user = employeeRepository.findByEmployeeEmail(loginRequest.getEmployeeEmail());

        // Si la consulta JPQL no funciona, intenta con la consulta nativa
        if (user == null) {
            user = employeeRepository.findByEmployeeEmailNative(loginRequest.getEmployeeEmail());
        }

        // Verificar si el usuario es nulo y lanzar un mensaje específico
        if (user == null) {
            throw new ValidationException("Correo electrónico no encontrado");
        }

        // Verificar si se encontró al usuario y si la contraseña coincide
        if (BCrypt.checkpw(loginRequest.getEmployeePassword(), user.getEmployeePassword())) {
            // Generar tokens JWT y de actualización
            var jwtToken = jwtService.generate(user.getId().toString(), user.getRole().toString(), "ACCESS");
            var refreshToken = jwtService.generate(user.getId().toString(), user.getRole().toString(), "REFRESH");

            return AuthResponse.builder()
                    .user_id(user.getId())
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        } else {
            // Si la contraseña no coincide, lanzar una excepción
            throw new ValidationException("Contraseña incorrecta");
        }
    }




    public void validateRegisterRequest(EmployeeRequest registerRequest)
    {
        if(registerRequest.getEmployeeFirstName()==null  ||
                registerRequest.getEmployeeFirstName().isEmpty())
        {
            throw new ValidationException("El nombre del usuario debe ser obligatorio");
        }
        if(registerRequest.getEmployeeFirstName().length()>50)
        {
            throw new ValidationException("El nombre del usuario no debe exceder los 50 caracteres");
        }
        if(registerRequest.getEmployeeLastName()==null || registerRequest.getEmployeeLastName().isEmpty())
        {
            throw new ValidationException("El apellido del usuario debe ser obligatorio");
        }
        if(registerRequest.getEmployeeLastName().length()>50)
        {
            throw new ValidationException("El apellido del usuario no debe exceder los 50 caracteres");
        }
        if (registerRequest.getEmployeeEmail() == null || registerRequest.getEmployeeEmail().isEmpty()) {
            throw new ValidationException("El email del usuario debe ser obligatorio");
        }
        if (registerRequest.getEmployeeEmail().length() > 50) {
            throw new ValidationException("El email del usuario no debe exceder los 50 caracteres");
        }
        if (registerRequest.getEmployeePassword() == null || registerRequest.getEmployeePassword().isEmpty()) {
            throw new ValidationException("La contraseña del usuario debe ser obligatorio");
        }
        if (registerRequest.getEmployeePassword().length() > 100) {
            throw new ValidationException("La contraseña del usuario no debe exceder los 100 caracteres");
        }
    }

    public void existsUserByEmail(EmployeeRequest registerRequest) {
        if (employeeRepository.existsByEmployeeEmail(registerRequest.getEmployeeEmail())) {
            throw new ValidationException("Ya existe un usuario con el email " + registerRequest.getEmployeeEmail());
        }
    }


}