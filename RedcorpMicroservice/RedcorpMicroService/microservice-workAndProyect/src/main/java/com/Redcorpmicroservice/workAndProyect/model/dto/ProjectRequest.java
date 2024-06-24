package com.Redcorpmicroservice.workandproyect.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectRequest {
    private String projectName;
    private String projectDescription;
    private LocalDate projectInitialDate;
    private LocalDate projectFinalDate;
    private String projectState;
    private Long teamId;
}
