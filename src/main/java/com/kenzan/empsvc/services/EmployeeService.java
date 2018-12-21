package com.kenzan.empsvc.services;

import com.kenzan.empsvc.exceptions.EmployeeException;
import com.kenzan.empsvc.model.Employee;
import com.kenzan.empsvc.model.EmployeeStatusEnum;
import com.kenzan.empsvc.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService() {}

    public Employee getEmployeeById(Long id) throws EmployeeException {
        return employeeRepository.findByIdAndStatus(id, EmployeeStatusEnum.ACTIVE)
                .orElseThrow(() ->
                {
                    return new EmployeeException("Active employee with ID: " + id + " not found");
                });
    }

    public Long createEmployee( Employee emp) {
        Employee retEmp = employeeRepository.save(emp);
        return retEmp.getId();
    }

    public Employee updateExistingEmployee(Employee existEmp) throws EmployeeException {
        if (existEmp.getId() < 1L) throw new EmployeeException("User ID provided is invalid");

        //If user doesn't exist, inform client
        employeeRepository.findById(existEmp.getId()).orElseThrow(() ->
        {
            return new EmployeeException("Cannot update a non-existent user!");
        });
        return employeeRepository.save(existEmp);
    }

    /**Just makes user inactive, does not remove from database **/
    public void deleteEmployee (Employee empToDelete ){
        empToDelete.setStatus(EmployeeStatusEnum.INACTIVE);
        employeeRepository.save(empToDelete);
    }

    /** Only return active employees **/
    public List<Employee> getAllEmployees (){
        return employeeRepository.findAll()
                .stream()
                .filter(emp -> emp.getStatus() == EmployeeStatusEnum.ACTIVE )
                .collect(Collectors.toList());
    }
}
