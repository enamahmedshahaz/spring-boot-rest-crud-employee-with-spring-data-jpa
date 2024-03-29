package com.shahaz.cruddemo.dao;

import com.shahaz.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeDaoJpaImpl implements EmployeeDao {

    EntityManager entityManager;

    @Autowired
    public EmployeeDaoJpaImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        //create query
        //Here Query represents the Java POJO Employee Class. So in query string
        //'Employee' can't be written as 'employee'
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee", Employee.class);

        //execute query and get result list
        List<Employee> employeeList =   theQuery.getResultList();

        //return list
        return  employeeList;
    }

    @Override
    public Employee findById(int id) {
        Employee theEmployee = entityManager.find(Employee.class, id);
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        //if id==0 the merge function saves/inserts into DB else update
       Employee dbEmployee = entityManager.merge(theEmployee);
       return dbEmployee;
    }

    @Override
    public void deleteById(int id) {
        //find the employee with given id
        Employee theEmployee = entityManager.find(Employee.class, id);

        //remove the found employee
        entityManager.remove(theEmployee);
    }
}
