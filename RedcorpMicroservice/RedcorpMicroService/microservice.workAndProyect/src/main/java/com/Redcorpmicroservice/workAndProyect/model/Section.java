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
@Table(name="sections")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="section_name", nullable = false, length = 50)
    private String sectionName;

    @Column(name="section_description", nullable = false, length = 100)
    private String sectionDescription;


}
