package com.shahaz.cruddemo.service;

import com.shahaz.cruddemo.dao.EmployeeRepository;
import com.shahaz.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employeeList = employeeRepository.findAll();
        return employeeList;
    }

    @Override
    public Employee findById(int id) {

        Optional<Employee> result = employeeRepository.findById(id);

        Employee employee;

        if(result.isPresent()){
            employee = result.get();
        }
        else { //did not find the employee
            throw new RuntimeException("Employee not found with id: "+ id);
        }

        return employee;
    }


    @Override // no @Transactional required while using JpaRepository
    public Employee save(Employee theEmployee) {
        Employee dbEmployee =  employeeRepository.save(theEmployee);
        return  dbEmployee;
    }


    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
