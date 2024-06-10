package com.Redcorpmicroservice.authAndProfile.service.impl;

import com.Redcorpmicroservice.authAndProfile.client.TaskClient;
import com.Redcorpmicroservice.authAndProfile.exception.ResourceNotFoundException;
import com.Redcorpmicroservice.authAndProfile.exception.ValidationException;
import com.Redcorpmicroservice.authAndProfile.model.Employee;
import com.Redcorpmicroservice.authAndProfile.model.Roles;
import com.Redcorpmicroservice.authAndProfile.model.dto.*;
import com.Redcorpmicroservice.authAndProfile.repository.EmployeeRepository;
import com.Redcorpmicroservice.authAndProfile.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaskClient taskClient;

    @Override
    public EmployeeResponse  createEmployee(Employee employee) {
        existsUserByUserId(employee.getId());
        existsByEmployeeEmail(employee.getEmployeeEmail());
        if (employee.getRole() == null) {
            throw new ValidationException("El rol del usuario debe ser obligatorio");
        }
        if (employee.getRole() == Roles.USER && employee.getRole() == Roles.ADMIN) {
            throw new ValidationException("El rol no es válido");
        }
        return convertToDto(employeeRepository.save(employee));
    }

    @Override
    public Employee getEmployeeById(Long employee_id) {
        existsUserByUserId(employee_id);
        return employeeRepository.findById(employee_id).orElse(null);
    }

    @Override
    public EmployeeResponse updateEmployee(Employee employee) {
        existsUserByUserId(employee.getId());
        existsByEmployeeEmail(employee.getEmployeeEmail());
        validateEmployee(employee);
        Employee employeeFind = employeeRepository.findById(employee.getId()).orElse(new Employee());

        if(employeeFind.getEmployeeEmail() != employee.getEmployeeEmail()){
            throw new ResourceNotFoundException("A este usuario no le pertenece el email: " +employee.getEmployeeEmail());
        }

        if(employee.getEmployeePhoto() == null){
            employee.setEmployeePhoto(employeeFind.getEmployeePhoto());
        }
        if(employee.getEmployeePassword() == null){
            employee.setEmployeePassword(employeeFind.getEmployeePassword());
        }
        if(employee.getTeamId() == null){
            employee.setTeamId(employeeFind.getTeamId());
        }

        return convertToDto(employeeRepository.save(employee));
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
    public TaskByEmployeeResponse findTasksByEmployeeId(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(new Employee());

        List<TaskDto> taskDtoList = taskClient.findAllTasksByEmployeeId(employeeId);

        return TaskByEmployeeResponse.builder()
                .employeeFirstName(employee.getEmployeeFirstName())
                .employeeLastName(employee.getEmployeeLastName())
                .employeeDni(employee.getEmployeeDni())
                .employeeEmail(employee.getEmployeeEmail())
                .employeeArea(employee.getEmployeeArea())
                .employeeCargo(employee.getEmployeeCargo())
                .employeePhoto(employee.getEmployeePhoto())
                .sectionId(employee.getSectionId())
                .teamId(employee.getTeamId())
                .taskDtoList(taskDtoList)
                .build();
    }

    @Override
    public List<Employee> findBySectionId(Long sectionId) {
        return employeeRepository.findAllBySectionId(sectionId);
    }

    @Override
    public List<Employee> findByTeamId(Long teamId) {
        return employeeRepository.findAllByTeamId(teamId);
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

    public void validateEmployee(Employee employee)
    {
        List<String> validCargos = Arrays.asList(
                "Supervisor",
                "Ejecutiva de ventas",
                "Gerente",
                "Especialista Digital",
                "Analista Financiero",
                "Desarrollador",
                "Reclutamiento",
                "Coordinador",
                "Representante",
                "Analista",
                "Junior"
        );
        List<String> validAreas = Arrays.asList(
                "Area de Sistemas",
                "Finanzas y Contabilidad",
                "Recursos Humanos",
                "Marketing y Ventas",
                "Producción",
                "Desarrollo de Software",
                "Administración"
        );

        if(employee.getEmployeeFirstName()==null  ||
                employee.getEmployeeFirstName().isEmpty())
        {
            throw new ValidationException("El nombre del usuario debe ser obligatorio");
        }
        if(employee.getEmployeeFirstName().length()>50 && employee.getEmployeeFirstName().length()<3)
        {
            throw new ValidationException("El nombre del usuario es muy corto o muy largo");
        }
        if(employee.getEmployeeLastName()==null || employee.getEmployeeLastName().isEmpty())
        {
            throw new ValidationException("El apellido del usuario debe ser obligatorio");
        }
        if(employee.getEmployeeLastName().length()>50&& employee.getEmployeeLastName().length()<3)
        {
            throw new ValidationException("El apellido del usuario es muy corto o muy largo");
        }
        if(employee.getEmployeeDni()==null || employee.getEmployeeDni().isEmpty())
        {
            throw new ValidationException("El dni del usuario debe ser obligatorio");
        }
        if(employee.getEmployeeDni().length()== 7)
        {
            throw new ValidationException("El dni tiene que tener 8 caracteres");
        }
        if (employee.getEmployeeEmail() == null || employee.getEmployeeEmail().isEmpty()) {
            throw new ValidationException("El email del usuario debe ser obligatorio");
        }
        if (employee.getEmployeeEmail().length() > 50) {
            throw new ValidationException("El email del usuario no debe exceder los 50 caracteres");
        }
        if (employee.getEmployeeCargo() == null || employee.getEmployeeCargo().isEmpty()) {
            throw new ValidationException("El cargo del usuario es obligatorio");
        }
        if (!validCargos.contains(employee.getEmployeeCargo())) {
            throw new ValidationException("El cargo no es válido");
        }
        if (employee.getEmployeeArea() == null || employee.getEmployeeArea().isEmpty()) {
            throw new ValidationException("El área del usuario es obligatorio");
        }
        if (!validAreas.contains(employee.getEmployeeArea())) {
            throw new ValidationException("El área no es válida");
        }
        if (employee.getSectionId() == null) {
            throw new ValidationException("La sección es obligatoria");
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
                .teamId(employee.getTeamId())
                .build();
    }
}
