package com.centrohospitalar.grupog.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Ticket {
    @Id
    @GeneratedValue
    private long id;
    private int number;
    private LocalDate arrivalTime;
    private String type;
}
