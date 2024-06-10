package com.Redcorpmicroservice.authAndProfile.service;

import com.Redcorpmicroservice.authAndProfile.model.Employee;
import com.Redcorpmicroservice.authAndProfile.model.dto.EmployeeResponse;
import com.Redcorpmicroservice.authAndProfile.model.dto.TaskByEmployeeResponse;

import java.util.List;

public interface EmployeeService {
    public abstract EmployeeResponse createEmployee(Employee employee);
    public abstract Employee getEmployeeById(Long employee_id);
    public abstract EmployeeResponse updateEmployee(Employee employee);
    public abstract void deleteEmployee(Long employee_id);
    public abstract List<Employee> getAllEmployees();
    TaskByEmployeeResponse findTasksByEmployeeId(Long employeeId);

    List<Employee> findBySectionId(Long sectionId);
    List<Employee> findByTeamId(Long teamId);
}
