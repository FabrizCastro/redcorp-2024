package com.Redcorpmicroservice.authAndProfile.controller;

import com.Redcorpmicroservice.authAndProfile.model.Employee;
import com.Redcorpmicroservice.authAndProfile.model.dto.EmployeeRequest;
import com.Redcorpmicroservice.authAndProfile.model.dto.EmployeeResponse;
import com.Redcorpmicroservice.authAndProfile.repository.EmployeeRepository;
import com.Redcorpmicroservice.authAndProfile.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/redcorp/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // URL: http://localhost:8090/api/redcorp/v1/employee
    // Method: GET

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
        List<Employee> users = employeeService.getAllEmployees();
        return new ResponseEntity<List<EmployeeResponse>>(users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    // URL: http://localhost:8090/api/redcorp/v1/employee
    // Method: POST

    @PostMapping
    public ResponseEntity<EmployeeResponse> createUser(@RequestBody EmployeeRequest employeeRequest) {

        Employee employee = new Employee();
        employee.setEmployeeFirstName(employeeRequest.getEmployeeFirstName());
        employee.setEmployeeLastName(employeeRequest.getEmployeeLastName());
        employee.setEmployeeDni(employeeRequest.getEmployeeDni());
        employee.setEmployeeEmail(employeeRequest.getEmployeeEmail());
        employee.setEmployeePassword(employeeRequest.getEmployeePassword());
        employee.setEmployeeArea(employeeRequest.getEmployeeArea());
        employee.setEmployeeCargo(employeeRequest.getEmployeeCargo());
        return new ResponseEntity<EmployeeResponse>(employeeService.createEmployee(employee), HttpStatus.OK);

    }

    // URL: http://localhost:8090/api/redcorp/v1/employee/{employeeId}
    // Method: GET

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable(name = "employeeId") Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        EmployeeResponse employeeResponse = convertToDto(employee);
        return new ResponseEntity<EmployeeResponse>(employeeResponse, HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/cyclescape/v1/users/{employeeId}
    // Method: PUT

    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateUser(@PathVariable(name = "employeeId") Long employeeId, @RequestBody Employee employee) {

        employee.setId(employeeId);

        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee), HttpStatus.OK);

    }

    @GetMapping("/search-by-section/{sectionId}")
    public ResponseEntity<?> findByIdCourse(@PathVariable Long sectionId){
        return ResponseEntity.ok(employeeService.findBySectionId(sectionId));
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
                .teamId(employee.getTeamId())
                .build();
    }
}
