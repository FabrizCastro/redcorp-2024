package com.Redcorpmicroservice.authAndProfile.service.impl;

import com.Redcorpmicroservice.authAndProfile.exception.ResourceNotFoundException;
import com.Redcorpmicroservice.authAndProfile.model.Employee;
import com.Redcorpmicroservice.authAndProfile.model.dto.EmployeeResponse;
import com.Redcorpmicroservice.authAndProfile.repository.EmployeeRepository;
import com.Redcorpmicroservice.authAndProfile.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponse  createEmployee(Employee employee) {
        existsUserByUserId(employee.getId());
        existsByEmployeeEmail(employee.getEmployeeEmail());
        return convertToDto(employeeRepository.save(employee));
    }

    @Override
    public Employee getEmployeeById(Long employee_id) {
        existsUserByUserId(employee_id);
        return employeeRepository.findById(employee_id).orElse(null);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        existsUserByUserId(employee.getId());
        existsByEmployeeEmail(employee.getEmployeeEmail());
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long employee_id) {
        employeeRepository.deleteById(employee_id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findBySectionId(Long sectionId) {
        return employeeRepository.findAllBySectionId(sectionId);
    }
    
    
    private void existsByEmployeeEmail(String email) {
        if (!employeeRepository.existsByEmployeeEmail(email)) {
            throw new ResourceNotFoundException("No existe un usuario con el email " + email);
        }
    }
    private void existsUserByUserId(Long employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException("No existe un usuario con el id " + employeeId);
        }
    }

    private EmployeeResponse convertToDto(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .employeeFirstName(employee.getEmployeeFirstName())
                .employeeLastName(employee.getEmployeeLastName())
                .employeeDni(employee.getEmployeeDni())
                .employeeEmail(employee.getEmployeeEmail())
                .employeeArea(employee.getEmployeeArea())
                .employeeCargo(employee.getEmployeeCargo())
                .employeePhoto(employee.getEmployeePhoto())
                .sectionId(employee.getSectionId())
                .build();
    }
}
