package com.example.demo.servise;

import com.example.demo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServise {
    private final EmployeeServise employeeServise;


    public DepartmentServise(EmployeeServise employeeServise) {
        this.employeeServise = employeeServise;
    }

    public Employee findMaxSalary(int department) {
        return employeeServise.getAll().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public Employee findMinSalary(int department) {
        return employeeServise.getAll().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    public Collection<Employee> findByDepartment(int department) {
        return employeeServise.getAll().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }
    public Map<Integer, List<Employee>> groupByDepartment() {

        return employeeServise.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
