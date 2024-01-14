package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.servise.EmployeeServise;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
private final EmployeeServise servise;

 public EmployeeController(EmployeeServise servise) {
  this.servise = servise;
 }

 @GetMapping("/add")
    public void add(@RequestParam String firsnName,@RequestParam String lastName){
      servise.add(firsnName, lastName);
    }
    @GetMapping("/remove")
    public void remove(@RequestParam String firsnName,@RequestParam String lastName){
     servise.remove(firsnName, lastName);
    }
    @GetMapping("/find")
    public Employee find(@RequestParam String firsnName,@RequestParam String lastName){
       return servise.find(firsnName, lastName);
    }
 @GetMapping("/all")
 public Collection<Employee> getAll(){
 return servise.getAll();
 }
}
