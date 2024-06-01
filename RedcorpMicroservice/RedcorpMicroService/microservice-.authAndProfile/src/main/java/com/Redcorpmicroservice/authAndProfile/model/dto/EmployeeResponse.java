package com.Redcorpmicroservice.authAndProfile.model.dto;

import com.Redcorpmicroservice.authAndProfile.model.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponse {
    private Long id;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeDni;
    private String employeeEmail;
    private String employeeArea;
    private String employeeCargo;
    private String employeePhoto;
    private Long sectionId;
    private Long teamId;

}
