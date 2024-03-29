package com.shahaz.cruddemo.dao;

import ch.qos.logback.core.model.conditional.ElseModel;
import com.shahaz.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee theEmployee);

    void deleteById(int id);

}
