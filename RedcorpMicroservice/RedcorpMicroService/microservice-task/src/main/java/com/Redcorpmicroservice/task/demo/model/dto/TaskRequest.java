package com.Redcorpmicroservice.task.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequest {

    private String taskName;
    private String taskDescription;
    private LocalDate taskInitialDate;
    private LocalDate taskFinalDate;
    private String taskState;
    private Long projectId;
    private Long employeeId;
}
