package com.Redcorpmicroservice.authAndProfile.service;

import com.Redcorpmicroservice.authAndProfile.model.Employee;
import com.Redcorpmicroservice.authAndProfile.model.dto.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    public abstract EmployeeResponse createEmployee(Employee employee);
    public abstract Employee getEmployeeById(Long employee_id);
    public abstract Employee updateEmployee(Employee employee);
    public abstract void deleteEmployee(Long employee_id);
    public abstract List<Employee> getAllEmployees();

    List<Employee> findBySectionId(Long sectionId);
}
