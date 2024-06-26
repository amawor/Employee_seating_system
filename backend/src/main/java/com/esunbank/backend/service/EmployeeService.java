package com.esunbank.backend.service;

import com.esunbank.backend.model.Employee;
import com.esunbank.backend.model.SeatingChart;
import com.esunbank.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public List<Employee> getAvailableEmployees() {

        return employeeRepository.findBySeatingChartIsNull();
    }

    public Employee updateEmployeeSeatingChart(String id, SeatingChart seatingChart) {
        Employee employee = employeeRepository.findById(id).get();

        employee.setSeatingChart(seatingChart);
        employeeRepository.save(employee);

        return employeeRepository.findById(id).get();
    }

    public Employee removeEmployeeSeatingChart(String id) {
        Employee employee = employeeRepository.findById(id).get();

        employee.setSeatingChart(null);
        employeeRepository.save(employee);

        return employeeRepository.findById(id).get();
    }
}
