package com.example.demo.servise;

import com.example.demo.exception.EmployeeAlreadyAddedException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.exception.EmployeeStorageIsFullException;
import com.example.demo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServise {
    private static final int MAX_COUNT = 10;
    private final List<Employee> employees = new ArrayList<>(MAX_COUNT) ;

    public void add(String firstName, String lastName) {
        if(employees.size() >= MAX_COUNT){
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName);
        if(employees.contains(employee)){
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);
    }
    public void remove (String firstName, String lastName){
        var it = employees.iterator();
        boolean removed= false;
        while (it.hasNext()){
            var employee = it.next();
            if(employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)){
                it.remove();
                removed = true;
            }
        }
        if(!removed){
        throw new EmployeeNotFoundException(); }
    }
    public Employee find(String firstName, String lastName){
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) ;
            {
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }
    public Collection<Employee> getAll(){
        return Collections.unmodifiableCollection(employees);
    }

}
