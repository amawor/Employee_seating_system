package com.esunbank.backend.controller;

import com.esunbank.backend.model.Employee;
import com.esunbank.backend.model.SeatingChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.esunbank.backend.service.EmployeeService;

import java.util.List;

@RequestMapping("/api")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/employee")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/available")
    public List<Employee> getAvailableEmployees() {
        return employeeService.getAvailableEmployees();
    }

    @PutMapping("/{id}")
    public Employee updateEmployeeSeatingChart(@RequestBody SeatingChart seatingChart,
                                               @PathVariable("id") String id) {
        System.out.println(seatingChart);
        return employeeService.updateEmployeeSeatingChart(id, seatingChart);
    }
    @PutMapping("/remove/{id}")
    public Employee removeEmployeeSeatingChart(@PathVariable("id") String id) {
        return employeeService.removeEmployeeSeatingChart(id);
    }
}
