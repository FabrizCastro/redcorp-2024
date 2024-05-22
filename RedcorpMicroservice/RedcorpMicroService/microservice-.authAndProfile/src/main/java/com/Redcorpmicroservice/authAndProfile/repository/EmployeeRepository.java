package com.Redcorpmicroservice.authAndProfile.repository;

import com.Redcorpmicroservice.authAndProfile.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsById(Long user_id);

    boolean existsByEmployeeEmail(String user_email);

    Employee findByEmployeeEmail(String email);
    List<Employee> findAllBySectionId(Long courseId);
    List<Employee> findAll();
}
