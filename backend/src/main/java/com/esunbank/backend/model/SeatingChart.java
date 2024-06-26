package com.esunbank.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatingChart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long floor_seat_seq;
    private int floor_no;
    private int seat_no;
}
