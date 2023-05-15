package com.centrohospitalar.grupog.repositories;

import com.centrohospitalar.grupog.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    public List<Appointment> getAppointmentsByDoctorId(Long id);

    public List<Appointment> getAppointmentsByPatientId(Long id);

    public List<Appointment> getAppointmentByDoctorIdAndStatusAndPlannedStartTimeBetweenOrderByPlannedStartTime(Long id, String status, LocalDateTime start, LocalDateTime end);
    public List<Appointment> getAppointmentByDoctorIdAndPlannedStartTimeBetweenOrderByPlannedStartTime(Long id, LocalDateTime start, LocalDateTime end);
    public List<Appointment> getAppointmentByDoctorIdAndStatus(Long id, String status);
    Appointment getAppointmentByDoctorIdAndPlannedStartTimeAndStatus(Long id, LocalDateTime start, String status);

    //queries para a view appointments_main
    public List<Appointment> getAppointmentsByDoctorIdAndPlannedStartTimeAfterOrderByPlannedStartTime(Long id, LocalDateTime startTime);
    public List<Appointment> getAppointmentsByPatientIdAndPlannedStartTimeAfterOrderByPlannedStartTime(Long id, LocalDateTime currentDay);
    public List<Appointment> getAppointmentsByPlannedStartTimeAfterOrderByPlannedStartTime(LocalDateTime currentDay);
    Appointment getAppointmentByStatus(String status);
    public List<Appointment> getAppointmentsByDoctorIdAndPlannedStartTimeGreaterThanEqualAndPlannedEndTimeLessThanEqualAndAndStatusIn(Long id, LocalDateTime startTime, LocalDateTime endTime, List<String> appointmentStatusList);
}
