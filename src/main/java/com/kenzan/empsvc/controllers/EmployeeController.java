package com.kenzan.empsvc.controllers;

import com.kenzan.empsvc.exceptions.EmployeeException;
import com.kenzan.empsvc.model.Employee;
import com.kenzan.empsvc.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employee/{employee_number}")
    public Employee getEmployee( @PathVariable(value="employee_number") Long empNum ) throws EmployeeException{
        return employeeService.getEmployeeById(empNum);
    }

    @PostMapping("/employee")
    public Employee createEmployee( @RequestBody Employee newEmployee) throws EmployeeException {
        long empId = employeeService.createEmployee(newEmployee);
        return employeeService.getEmployeeById(empId);
    }

    @PutMapping("/employee")
    public Employee updateEmployee( @RequestBody Employee existingEmployee) throws EmployeeException {
        return employeeService.updateExistingEmployee( existingEmployee);
    }

    @DeleteMapping("/employee")
    public void deleteEmployee( @RequestBody Employee empToDelete ) {
        employeeService.deleteEmployee(empToDelete);
    }

}
