package com.esunbank.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.esunbank.backend.service.SeatingChartService;
import com.esunbank.backend.model.SeatingChart;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SeatingChartController {
    @Autowired
    private SeatingChartService seatingChartService;

    @GetMapping("/seatingchart")
    public List<SeatingChart> getAllSeatingChart() {
        return seatingChartService.getAllSeatingChart();
    }

    @GetMapping("/seatingchart/{id}")
    public SeatingChart getSeatingChartById(@PathVariable("id") Long id) {
        System.out.println(seatingChartService.getSeatingChartById(id));
        return seatingChartService.getSeatingChartById(id);
    }
}
