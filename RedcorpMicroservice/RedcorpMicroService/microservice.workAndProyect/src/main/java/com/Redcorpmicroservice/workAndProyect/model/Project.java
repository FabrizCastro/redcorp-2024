package com.Redcorpmicroservice.workAndProyect.model;

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
@Table(name="projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="project_name", nullable = false, length = 50)
    private String projectName;

    @Column(name="project_description", nullable = false, length = 100)
    private String projectDescription;

    @Column(name="project_initial_date", nullable = false)
    private LocalDate projectInitialDate;

    @Column(name="project_final_date", nullable = false)
    private LocalDate projectFinalDate;

    @Column(name="project_state", nullable = false, length = 100)
    private String projectState;
}
