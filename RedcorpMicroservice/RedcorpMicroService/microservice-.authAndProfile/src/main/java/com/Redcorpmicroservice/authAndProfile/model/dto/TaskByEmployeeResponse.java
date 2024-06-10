package com.Redcorpmicroservice.authAndProfile.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskByEmployeeResponse {
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeDni;
    private String employeeEmail;
    private String employeeArea;
    private String employeeCargo;
    private String employeePhoto;
    private Long sectionId;
    private Long teamId;
    private List<TaskDto> taskDtoList;
}
