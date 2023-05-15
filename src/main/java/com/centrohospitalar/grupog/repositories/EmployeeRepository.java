package com.centrohospitalar.grupog.repositories;


import com.centrohospitalar.grupog.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee getByEmail(String email);
    Employee getById(Long id);
    List<Employee> getEmployeeByActiveAccountTrueAndRole_Name(String role);


}