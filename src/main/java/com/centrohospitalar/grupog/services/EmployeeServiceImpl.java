package com.centrohospitalar.grupog.services;


import com.centrohospitalar.grupog.entities.Admin;
import com.centrohospitalar.grupog.entities.Employee;
import com.centrohospitalar.grupog.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    public List<Employee> getEmployeeByActiveAccountTrueAndRole_Name(String role){
        List <Employee> employees = employeeRepository.getEmployeeByActiveAccountTrueAndRole_Name(role);
        return employees;
    }

    public Employee getById(Long id){
        return employeeRepository.getById(id);
    }
}
