package com.esunbank.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    private String emp_id;
    private String name;
    private String email;

    @OneToOne
    @JoinColumn(name = "FLOOR_SEAT_SEQ", referencedColumnName = "FLOOR_SEAT_SEQ")
    private SeatingChart seatingChart;
}
