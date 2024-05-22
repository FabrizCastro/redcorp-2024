package com.Redcorpmicroservice.workAndProyect.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="team_name", nullable = false, length = 50)
    private String teamName;

    @Column(name="team_description", nullable = false, length = 100)
    private String teamDescription;

    @Column(name="employee_Id", nullable = false, length = 50)
    private Long employeeId;

    @Column(name="project_Id", nullable = false)
    private Long projectId;

    @Column(name="task_Id", nullable = false)
    private Long taskId;

}
