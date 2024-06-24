package com.Redcorpmicroservice.authandprofile.model;

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
@Table(name="employees")
public class Employee{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="employee_first_name", nullable = false, length = 50)
    private String employeeFirstName;

    @Column(name="employee_last_name", nullable = false, length = 50)
    private String employeeLastName;

    @Column(name="employee_dni", nullable = false, length = 50)
    private String employeeDni;

    @Column(name="employee_email", nullable = false, length = 50)
    private String employeeEmail;

    @Column(name="employee_password", nullable = false, length = 100)
    private String employeePassword;

    @Column(name="employee_area", nullable = false, length = 50)
    private String employeeArea;

    @Column(name="employee_cargo", nullable = false, length = 50)
    private String employeeCargo;

    @Column(name="employee_photo", nullable = true)
    private String employeePhoto;

    @Column(name="employee_active", nullable = false)
    private Boolean employeeActive;

    @Column(name="section_id", nullable = false)
    private Long sectionId;

    @Column(name="team_id", nullable = true)
    private Long teamId;

    @Enumerated(EnumType.STRING)
    private Roles role;



}
