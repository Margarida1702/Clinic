package com.centrohospitalar.grupog.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DateScalePeriod {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @OneToOne
    private Specialty specialty;
    @ManyToOne
    private Employee employee;

    public DateScalePeriod() {
    }

    public DateScalePeriod(LocalDateTime startTime, LocalDateTime endTime, Specialty specialty, com.centrohospitalar.grupog.entities.Employee employee) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.specialty = specialty;
        this.employee = employee;
    }

    public String getWorkDayWeek(){
        return String.valueOf(startTime.getDayOfWeek());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}


