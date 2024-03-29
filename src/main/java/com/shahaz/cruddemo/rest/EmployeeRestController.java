package com.shahaz.cruddemo.rest;

import com.shahaz.cruddemo.dao.EmployeeDao;
import com.shahaz.cruddemo.entity.Employee;
import com.shahaz.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.EventObject;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {

        List<Employee> employeeList = employeeService.findAll();

        return employeeList;
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null) {
            throw new RuntimeException("Employee not found with id: " + employeeId);
        }

        return theEmployee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {

        //in case of add a new Employee, if they add any id, set it to 0
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }


    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {

        Employee theEmployee = employeeService.findById(employeeId);
        //if given id not found
        if (theEmployee == null) {
            throw  new RuntimeException("Employee not found with id: "+employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee id: "+employeeId;
    }

}
