package com.centrohospitalar.grupog.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;
    private String status;
    private LocalDateTime arrivalDateTime;
    @ManyToOne
    private Specialty specialty;

    private LocalDateTime registerTime;
    private LocalDateTime plannedStartTime;
    private LocalDateTime plannedEndTime;
    private LocalDateTime realStartTime;
    private LocalDateTime realEndTime;
    private Double price;
    private String description;
    private String invoiceIdentity;
    private boolean isPaid;
//    public static final Time APPOINTMENT_DURATION = new Time()

    public Appointment() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public LocalDateTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalDateTime registerTime) {
        this.registerTime = registerTime;
    }

    public LocalDateTime getPlannedStartTime() {
        return plannedStartTime;
    }

    public void setPlannedStartTime(LocalDateTime planedStartTime) {
        this.plannedStartTime = planedStartTime;
    }

    public LocalDateTime getPlannedEndTime() {
        return plannedEndTime;
    }

    public void setPlannedEndTime(LocalDateTime planedEndTime) {
        this.plannedEndTime = planedEndTime;
    }

    public LocalDateTime getRealStartTime() {
        return realStartTime;
    }

    public void setRealStartTime(LocalDateTime realStartTime) {
        this.realStartTime = realStartTime;
    }

    public LocalDateTime getRealEndTime() {
        return realEndTime;
    }

    public void setRealEndTime(LocalDateTime realEndTime) {
        this.realEndTime = realEndTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getInvoiceIdentity() {
        return invoiceIdentity;
    }

    public void setInvoiceIdentity(String invoiceIdentity) {
        this.invoiceIdentity = invoiceIdentity;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", status='" + status + '\'' +
                ", arrivalDateTime=" + arrivalDateTime +
                ", specialty=" + specialty +
                ", registerTime=" + registerTime +
                ", planedStartTime=" + plannedStartTime +
                ", planedEndTime=" + plannedEndTime +
                ", realStartTime=" + realStartTime +
                ", realEndTime=" + realEndTime +
                ", invoice=" + invoiceIdentity +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

    public enum AppointmentStatus {

        RESERVED_24H ("Reservada por 24h",0),
        RESERVED_CANCELED ("Reserva Cancelada",24*60),
        SCHEDULED ("Marcada",0),
        PATIENT_ABSENCE ("Paciente faltou",0),  // não fez checkin antes da hora planeada
        CHECKIN("Check-In efectuado",0), // fez CHECKIN dentro do tempo
        LATE_CHECKIN("Check-In feito com atraso",0),
        STARTED("Em curso",0),
        PATIENT_CALLED_ABSENCE("Paciente não respondeu à chamada",10), //paciente fora dos 10min de tolerância depois de chamado pelo médico
        ENDED("Finalizada",0),

        AUTHORIZED ("Autorizada",0),
        DOCTOR_ABSENCE ("Doutor faltou",0),
        CANCELED_BY_PATIENT("Cancelada pelo paciente",0),
        CANCELED_BY_DOCTOR("Cancelada pelo médico",0);

        String prettyName;
        int minuteRequirementTime;


        private AppointmentStatus(String prettyName, int minuteRequirementTime) {
            this.prettyName = prettyName;
            this.minuteRequirementTime = minuteRequirementTime;
        }

        public String getPrettyName() {
            return this.prettyName;
        }

        public int getMinuteRequirementTime() {
            return minuteRequirementTime;
        }
    }


}