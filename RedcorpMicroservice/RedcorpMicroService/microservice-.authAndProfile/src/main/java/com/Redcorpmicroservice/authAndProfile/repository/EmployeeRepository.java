package com.Redcorpmicroservice.authAndProfile.repository;

import com.Redcorpmicroservice.authAndProfile.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsById(Long userId);

    boolean existsByEmployeeEmail(String employeeEmail);

    @Query("SELECT e FROM Employee e WHERE e.employeeEmail = :employeeEmail")
    Employee findByEmployeeEmail(@Param("employeeEmail") String employeeEmail);

    @Query(value = "SELECT * FROM employees WHERE employee_email = :employeeEmail", nativeQuery = true)
    Employee findByEmployeeEmailNative(@Param("employeeEmail") String employeeEmail);

    List<Employee> findAllBySectionId(Long sectionId);
    List<Employee> findAllByTeamId(Long teamId);
    List<Employee> findAll();
}
