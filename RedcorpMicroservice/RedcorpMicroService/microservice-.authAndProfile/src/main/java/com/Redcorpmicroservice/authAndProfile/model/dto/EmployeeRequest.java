package com.Redcorpmicroservice.authAndProfile.model.dto;

import com.Redcorpmicroservice.authAndProfile.model.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRequest {

    private String employeeFirstName;
    private String employeeLastName;
    private String employeeDni;
    private String employeeEmail;
    private String employeePassword;
    private String employeeArea;
    private String employeeCargo;
    private Long sectionId;
    private String role;
}
