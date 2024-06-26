package com.esunbank.backend.service;

import com.esunbank.backend.model.SeatingChart;
import com.esunbank.backend.repository.SeatingChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatingChartService {
    @Autowired
    private SeatingChartRepository seatingChartRepository;

    public List<SeatingChart> getAllSeatingChart() {
        return seatingChartRepository.findAll();
    }

    public SeatingChart getSeatingChartById(Long id) {
        return seatingChartRepository.findById(id).get();
    }

}
