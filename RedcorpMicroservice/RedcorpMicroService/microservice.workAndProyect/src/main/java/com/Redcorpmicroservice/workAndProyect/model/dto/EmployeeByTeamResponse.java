package com.Redcorpmicroservice.workAndProyect.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeByTeamResponse {
    private String teamName;
    private String teamDescription;
    private Long sectionId;
    private List<EmployeeDto> employeeDtoList;
}
