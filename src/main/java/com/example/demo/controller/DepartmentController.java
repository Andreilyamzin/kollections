package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.servise.DepartmentServise;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentServise servise;

    public DepartmentController(DepartmentServise servise) {
        this.servise = servise;
    }
    @GetMapping("/max-salary")
    public Employee max(@RequestParam int departmentId){
        return servise.findMaxSalary(departmentId);
    }
    @GetMapping("/max-salary")
    public Employee min(@RequestParam int departmentId){
        return servise.findMinSalary(departmentId);
    }
    @GetMapping(value = "/all", params = {"departmentId"})
    public Collection<Employee> findAllByDepartment(@RequestParam int departmentId){
        return servise.findByDepartment(departmentId);
    }
    @GetMapping("/all")
    public Map<Integer, List<Employee>> groupBy(){
        return servise.groupByDepartment();
    }

}
