package com.centrohospitalar.grupog.services;

import com.centrohospitalar.grupog.entities.*;
import com.centrohospitalar.grupog.exceptions.AppointmentException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    public List<Appointment> getAppointments();

    public Appointment getAppointmentById(Long Id);

    public void checkInApointment(String appointmentIdString) throws AppointmentException;
    public void patientCheckInApointment(String appointmentIdString, Patient patient) throws AppointmentException;

    public List<Appointment> getTotalDailyAppointmentsByDoctorId(Long id);

    public List<Appointment> getPendingAppointmentsByDoctorId(Long id);

    public List<Appointment> getAppointmentsByDoctorId(Long id);

//    public List<Appointment> getAppointmentByDoctorId

    public List<Appointment> getAppointmentsByPatientId(Long id);

    public Appointment getAppointmentByDoctorIdAndStatusAndPlannedStartTimeBetweenOrderByPlannedStartTime(Long id, LocalDate currentDay);


    void startAppointment(Appointment appointment);
    Appointment getAppointmentByStatus(String status);
    void endAppointment(Appointment appointment, String observations, String price) throws AppointmentException;
    public void cancelAppointment(Appointment appointment, User user);
    void payAppointment(Appointment appointment);
    Appointment getCurrentAppointment(Long id, LocalDate currentDay);

    //queries para listagem de consultas na appointments_main.jsp
    public List<Appointment> getAppointmentsByDoctorIdAndPlannedStartTimeAfterOrderByPlannedStartTime(Long id, LocalDate currentDay);
    void changeAppointmentStatus(Appointment appointment, String status);
    public List<Appointment> getAppointmentsByPatientIdAndPlannedStartTimeAfterOrderByPlannedStartTime(Long id, LocalDate currentDay);

    public List<Appointment> getAppointmentsByPlannedStartTimeAfterOrderByPlannedStartTime(LocalDate currentDay);

    public List<Appointment> getAppointmentsByDoctorIdAndPlannedStartTimeGreaterThanEqualAndPlannedEndTimeLessThanEqualAndAndStatusIn(Long id, LocalDateTime startTime, LocalDateTime endTime,List<String> appointmentStatusList);
    Appointment getAppointmentByDoctorIdAndPlannedStartTimeAndStatus(Long id, LocalDateTime start, String status);
    Appointment createAppointment(Patient patient, Specialty specialty, Doctor doctor, Integer hour, Integer day, Integer month, Integer year) throws AppointmentException;
}