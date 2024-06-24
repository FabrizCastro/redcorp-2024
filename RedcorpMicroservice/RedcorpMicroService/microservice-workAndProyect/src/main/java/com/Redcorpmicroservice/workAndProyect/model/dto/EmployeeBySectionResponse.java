package com.Redcorpmicroservice.workandproyect.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeBySectionResponse {
    private String sectionName;
    private String sectionDescription;
    private List<EmployeeDto> employeeDtoList;
}
