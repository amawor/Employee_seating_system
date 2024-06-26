package com.esunbank.backend.repository;

import com.esunbank.backend.model.SeatingChart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatingChartRepository extends JpaRepository<SeatingChart, Long> {
}
