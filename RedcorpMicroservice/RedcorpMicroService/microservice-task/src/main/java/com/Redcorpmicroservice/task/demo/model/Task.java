package com.Redcorpmicroservice.task.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="task_name", nullable = false, length = 100)
    private String taskName;

    @Column(name="task_description", nullable = false, length = 200)
    private String taskDescription;

    @Column(name="task_initial_date", nullable = false)
    private LocalDate taskInitialDate;

    @Column(name="task_final_date", nullable = false)
    private LocalDate taskFinalDate;

    @Column(name="task_state", nullable = false, length = 50)
    private String taskState;

    @Column(name="project_id", nullable = false)
    private Long projectId;

    @Column(name="employee_id", nullable = false)
    private Long employeeId;
}
