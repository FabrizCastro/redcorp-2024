package com.Redcorpmicroservice.workAndProyect.model.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskByProjectResponse {
    private String projectName;
    private String projectDescription;
    private LocalDate projectInitialDate;
    private LocalDate projectFinalDate;
    private String projectState;
    private Long teamId;
    private List<TaskDto> taskDtoList;
}
