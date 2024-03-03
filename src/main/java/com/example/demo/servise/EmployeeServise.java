package com.example.demo.servise;

import com.example.demo.exception.EmployeeAlreadyAddedException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.exception.EmployeeStorageIsFullException;
import com.example.demo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServise {
    private static final int MAX_COUNT = 10;
    private final Map<String, Employee> employees = new HashMap<>(MAX_COUNT);

    public void add(String firstName, String lastName,int salary, int department) {
        if (employees.size() >= MAX_COUNT) {
            throw new EmployeeStorageIsFullException();
        }
        var key = makeKey(firstName, lastName);
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }

        employees.put(key, employee);
    }

    public void remove(String firstName, String lastName) {
        var employee =  new Employee(firstName, lastName);
        var key = makeKey(firstName, lastName);
        var removed = employees.remove(key);
        if (removed != null) {
            throw new EmployeeNotFoundException();
        }
    }

    public Employee find(String firstName, String lastName) {
        var key = makeKey(firstName, lastName);
        var employee = employees.get(key);
        if (employee != null) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    public Collection<Employee> getAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

    private static String makeKey(String firstName, String lastName) {
        return (firstName + "_" + lastName).toLowerCase();
    }
}
