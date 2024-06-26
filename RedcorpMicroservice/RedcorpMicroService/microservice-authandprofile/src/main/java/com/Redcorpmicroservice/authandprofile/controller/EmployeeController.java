package com.Redcorpmicroservice.authandprofile.controller;

import com.Redcorpmicroservice.authandprofile.model.Employee;
import com.Redcorpmicroservice.authandprofile.model.dto.EmployeePutRequest;
import com.Redcorpmicroservice.authandprofile.model.dto.EmployeeRequest;
import com.Redcorpmicroservice.authandprofile.model.dto.EmployeeResponse;
import com.Redcorpmicroservice.authandprofile.repository.EmployeeRepository;
import com.Redcorpmicroservice.authandprofile.service.EmployeeService;
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
    public ResponseEntity<EmployeeResponse> updateUser(@PathVariable(name = "employeeId") Long employeeId, @RequestBody EmployeePutRequest employeeRequest) {

        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        employee.setEmployeeFirstName(employeeRequest.getEmployeeFirstName());
        employee.setEmployeeLastName(employeeRequest.getEmployeeLastName());
        employee.setEmployeeDni(employeeRequest.getEmployeeDni());
        employee.setEmployeeEmail(employeeRequest.getEmployeeEmail());
        employee.setEmployeeArea(employeeRequest.getEmployeeArea());
        employee.setEmployeeCargo(employeeRequest.getEmployeeCargo());
        employee.setEmployeePhoto(employeeRequest.getEmployeePhoto());
        employee.setSectionId(employeeRequest.getSectionId());
        employee.setTeamId(employeeRequest.getTeamId());

        EmployeeResponse updatedEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<EmployeeResponse>(updatedEmployee, HttpStatus.OK);
    }

    @GetMapping("/search-by-section/{sectionId}")
    public ResponseEntity<?> findByIdSection(@PathVariable Long sectionId){
        return ResponseEntity.ok(employeeService.findBySectionId(sectionId));
    }

    @GetMapping("/search-by-team/{teamId}")
    public ResponseEntity<?> findByIdTeam(@PathVariable Long teamId){
        return ResponseEntity.ok(employeeService.findByTeamId(teamId));
    }

    @GetMapping("/search-task/{employeeId}")
    public ResponseEntity<?> findTasksByEmployeeId(@PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.findTasksByEmployeeId(employeeId));
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
