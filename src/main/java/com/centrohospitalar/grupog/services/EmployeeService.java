package com.centrohospitalar.grupog.services;


import com.centrohospitalar.grupog.entities.Admin;
import com.centrohospitalar.grupog.entities.Employee;

import javax.management.relation.Role;
import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();
    List<Employee> getEmployeeByActiveAccountTrueAndRole_Name(String role);
    Employee getById(Long id);

}
