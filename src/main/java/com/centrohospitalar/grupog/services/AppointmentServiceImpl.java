package com.centrohospitalar.grupog.services;


import com.centrohospitalar.grupog.entities.*;
import com.centrohospitalar.grupog.exceptions.AppointmentException;
import com.centrohospitalar.grupog.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    AppointmentRepository appointmentRepository;
    static int i = 0;

    public List<Appointment> getAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments;
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.getReferenceById(id);
    }

    @Override
    public void checkInApointment(String appointmentIdString) throws AppointmentException { //função de checkin para todos os roles excepto PATIENT
        Long appointmentId = null;

        try {
            appointmentId = Long.parseLong(appointmentIdString);
        } catch (NumberFormatException e) {
            throw new AppointmentException("Formato de número de consulta incorrecto");
        }
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
        if(!appointmentOptional.isPresent()){ //is empty
            throw new AppointmentException("Número de consulta não encontrado");
        }
        if(appointmentOptional.get().getArrivalDateTime()!=null){
            throw new AppointmentException("Já foi foi feito o checkin da consulta " + appointmentId);
        }else{
            long toleranceMinutes = 10;
            appointmentOptional.get().setArrivalDateTime(LocalDateTime.now());
            if (appointmentOptional.get().getArrivalDateTime().isAfter(appointmentOptional.get().getPlannedStartTime())){ //se chegar depois do tempo
                appointmentOptional.get().setStatus(Appointment.AppointmentStatus.LATE_CHECKIN.getPrettyName());
            } else if (appointmentOptional.get().getArrivalDateTime().isBefore(appointmentOptional.get().getPlannedStartTime()) || appointmentOptional.get().getArrivalDateTime().isEqual(appointmentOptional.get().getPlannedStartTime())){
                appointmentOptional.get().setStatus(Appointment.AppointmentStatus.CHECKIN.getPrettyName()); //se chegar antes do tempo ou a na hora
            }
            appointmentRepository.save(appointmentOptional.get());
        }
    }

    public void patientCheckInApointment(String appointmentIdString, Patient patient) throws AppointmentException { //função de checkin para o UTENTE
        Long appointmentId = null;

        try {
            appointmentId = Long.parseLong(appointmentIdString);
        } catch (NumberFormatException e) {
            throw new AppointmentException("Formato de número de consulta incorrecto");
        }
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
        if(!appointmentOptional.isPresent()){ // == is empty
            throw new AppointmentException("Número de consulta não encontrado");
        }
        if(!appointmentOptional.get().getPatient().equals(patient)){ //se utente tentar fazer check in de appointment que não é seu
            throw new AppointmentException("Ação não autorizada");
        }
        if(appointmentOptional.get().getArrivalDateTime()!=null){
            throw new AppointmentException("Já foi foi feito o checkin da consulta " + appointmentId);
        }else{
            long toleranceMinutes = 10;
            appointmentOptional.get().setArrivalDateTime(LocalDateTime.now());
            if (appointmentOptional.get().getArrivalDateTime().isAfter(appointmentOptional.get().getPlannedStartTime())){ //se chegar depois do tempo
                appointmentOptional.get().setStatus(Appointment.AppointmentStatus.LATE_CHECKIN.getPrettyName());
            } else if (appointmentOptional.get().getArrivalDateTime().isBefore(appointmentOptional.get().getPlannedStartTime()) || appointmentOptional.get().getArrivalDateTime().isEqual(appointmentOptional.get().getPlannedStartTime())){
                appointmentOptional.get().setStatus(Appointment.AppointmentStatus.CHECKIN.getPrettyName()); //se chegar antes do tempo ou a na hora
            }
            appointmentRepository.save(appointmentOptional.get());
        }
    }

    @Override
    public List<Appointment> getTotalDailyAppointmentsByDoctorId(Long id) {
        LocalDate currentDay = LocalDate.now();
        return appointmentRepository.getAppointmentByDoctorIdAndPlannedStartTimeBetweenOrderByPlannedStartTime(id, currentDay.atStartOfDay(), currentDay.atTime(23,59));
    }

    @Override
    public List<Appointment> getPendingAppointmentsByDoctorId(Long id) {
        LocalDate currentDay = LocalDate.now();
        List<Appointment> appointments = appointmentRepository.getAppointmentByDoctorIdAndStatusAndPlannedStartTimeBetweenOrderByPlannedStartTime(id, Appointment.AppointmentStatus.SCHEDULED.getPrettyName(), currentDay.atStartOfDay(), currentDay.atTime(23,59));
        appointments.addAll(appointmentRepository.getAppointmentByDoctorIdAndStatusAndPlannedStartTimeBetweenOrderByPlannedStartTime(id, Appointment.AppointmentStatus.CHECKIN.getPrettyName(), currentDay.atStartOfDay(), currentDay.atTime(23,59)));
        return appointments;
    }

    public List<Appointment> getAppointmentsByDoctorId(Long id) {
        List<Appointment> appointmentsById = appointmentRepository.getAppointmentsByDoctorId(id);
        return appointmentsById;
    }

    public List<Appointment> getAppointmentsByPatientId(Long id) {
        List<Appointment> appointmentsById = appointmentRepository.getAppointmentsByPatientId(id);
        return appointmentsById;
    }

    public Appointment getAppointmentByDoctorIdAndStatusAndPlannedStartTimeBetweenOrderByPlannedStartTime(Long id, LocalDate currentDay){
        List<Appointment> appointmentsToday = appointmentRepository.getAppointmentByDoctorIdAndStatusAndPlannedStartTimeBetweenOrderByPlannedStartTime(id, Appointment.AppointmentStatus.CHECKIN.getPrettyName(), currentDay.atStartOfDay(), currentDay.atTime(23,59));

        if (appointmentsToday.size() == 0)
            return null;
        return appointmentsToday.get(0);
    }
    public void startAppointment(Appointment appointment) {
        appointment.setRealStartTime(LocalDateTime.now());
        appointment.setStatus(Appointment.AppointmentStatus.STARTED.getPrettyName());
        appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointmentByStatus(String status) {
        return appointmentRepository.getAppointmentByStatus(status);
    }

    public void endAppointment(Appointment appointment, String observations, String price) throws AppointmentException {

        if(observations.isEmpty() || observations.equals("") || price.equals("") || price.isEmpty())
            throw new AppointmentException("Ooops! Campos obrigatórios devem ser preenchidos");
        try{
            double aux = Double.parseDouble(price);
        } catch (NumberFormatException | NullPointerException e){
            throw new AppointmentException("Ooops! Preço deve estar em formato numérico");
        }

        appointment.setRealEndTime(LocalDateTime.now());
        appointment.setDescription(observations);
        appointment.setPrice(Double.valueOf(price));
        appointment.setStatus(Appointment.AppointmentStatus.ENDED.getPrettyName());
        appointmentRepository.save(appointment);
    }
    public void cancelAppointment(Appointment appointment, User user) {
        if (user.getRole().getName().equals("ROLE_PATIENT")){
            appointment.setStatus(Appointment.AppointmentStatus.CANCELED_BY_PATIENT.getPrettyName());
        } else if (user.getRole().getName().equals("ROLE_DOCTOR")){
            appointment.setStatus(Appointment.AppointmentStatus.CANCELED_BY_DOCTOR.getPrettyName());
        }
        appointmentRepository.save(appointment);
    }

    @Override
    public void payAppointment(Appointment appointment){
        appointment.setPaid(true);
        appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getCurrentAppointment(Long id, LocalDate currentDay) {
        List<Appointment> startedAppointments = appointmentRepository.getAppointmentByDoctorIdAndStatus(id, Appointment.AppointmentStatus.STARTED.getPrettyName());
        if(startedAppointments.isEmpty()) {
            return null;
        }
        return startedAppointments.get(0);
    }
    //queries para listagem de consultas na appointments_main.jsp
    public List<Appointment> getAppointmentsByDoctorIdAndPlannedStartTimeAfterOrderByPlannedStartTime(Long id, LocalDate currentDay){
        List<Appointment> appointmentsToday = appointmentRepository.getAppointmentsByDoctorIdAndPlannedStartTimeAfterOrderByPlannedStartTime(id, currentDay.atStartOfDay());

        if (appointmentsToday.size() == 0)
            return null;
        return appointmentsToday;
    }
    public List<Appointment>getAppointmentsByPatientIdAndPlannedStartTimeAfterOrderByPlannedStartTime(Long id, LocalDate currentDay){
        List<Appointment> appointmentsToday = appointmentRepository.getAppointmentsByPatientIdAndPlannedStartTimeAfterOrderByPlannedStartTime(id, currentDay.atStartOfDay());

        if (appointmentsToday.size() == 0)
            return null;
        return appointmentsToday;
    }

    public List<Appointment> getAppointmentsByPlannedStartTimeAfterOrderByPlannedStartTime(LocalDate currentDay){
        List<Appointment> appointmentsToday = appointmentRepository.getAppointmentsByPlannedStartTimeAfterOrderByPlannedStartTime(currentDay.atStartOfDay());
        if (appointmentsToday.size() == 0)
            return null;
        return appointmentsToday;
    }

    public List<Appointment> getAppointmentsByDoctorIdAndPlannedStartTimeGreaterThanEqualAndPlannedEndTimeLessThanEqualAndAndStatusIn(Long id, LocalDateTime startTime, LocalDateTime endTime,List<String> appointmentStatusList){
        return this.appointmentRepository.getAppointmentsByDoctorIdAndPlannedStartTimeGreaterThanEqualAndPlannedEndTimeLessThanEqualAndAndStatusIn(id, startTime, endTime, appointmentStatusList);
    }

    @Override
    public Appointment getAppointmentByDoctorIdAndPlannedStartTimeAndStatus(Long id, LocalDateTime start, String status) {
        return appointmentRepository.getAppointmentByDoctorIdAndPlannedStartTimeAndStatus(id, start, status);
    }

    @Override
    public Appointment createAppointment(Patient patient, Specialty specialty, Doctor doctor, Integer hour, Integer day, Integer month, Integer year) throws AppointmentException {

        if((getAppointmentByDoctorIdAndPlannedStartTimeAndStatus(doctor.getId(), LocalDateTime.of(year,month,day,hour,0), Appointment.AppointmentStatus.CHECKIN.name()) != null) ||
                (getAppointmentByDoctorIdAndPlannedStartTimeAndStatus(doctor.getId(), LocalDateTime.of(year,month,day,hour,0), Appointment.AppointmentStatus.ENDED.name()) != null) ||
                (getAppointmentByDoctorIdAndPlannedStartTimeAndStatus(doctor.getId(), LocalDateTime.of(year,month,day,hour,0), Appointment.AppointmentStatus.LATE_CHECKIN.name()) != null) ||
                (getAppointmentByDoctorIdAndPlannedStartTimeAndStatus(doctor.getId(), LocalDateTime.of(year,month,day,hour,0), Appointment.AppointmentStatus.SCHEDULED.name()) != null) ||
                (getAppointmentByDoctorIdAndPlannedStartTimeAndStatus(doctor.getId(), LocalDateTime.of(year,month,day,hour,0), Appointment.AppointmentStatus.STARTED.name()) != null) ||
                (getAppointmentByDoctorIdAndPlannedStartTimeAndStatus(doctor.getId(), LocalDateTime.of(year,month,day,hour,0), Appointment.AppointmentStatus.AUTHORIZED.name()) != null) ||
                (getAppointmentByDoctorIdAndPlannedStartTimeAndStatus(doctor.getId(), LocalDateTime.of(year,month,day,hour,0), Appointment.AppointmentStatus.DOCTOR_ABSENCE.name()) != null) ||
                (getAppointmentByDoctorIdAndPlannedStartTimeAndStatus(doctor.getId(), LocalDateTime.of(year,month,day,hour,0), Appointment.AppointmentStatus.PATIENT_ABSENCE.name()) != null) ||
                (getAppointmentByDoctorIdAndPlannedStartTimeAndStatus(doctor.getId(), LocalDateTime.of(year,month,day,hour,0), Appointment.AppointmentStatus.PATIENT_CALLED_ABSENCE.name()) != null) ||
                (getAppointmentByDoctorIdAndPlannedStartTimeAndStatus(doctor.getId(), LocalDateTime.of(year,month,day,hour,0), Appointment.AppointmentStatus.RESERVED_24H.name()) != null)){

            throw new AppointmentException("Não é possível marcar a consulta dado que a vaga já está ocupada :(");
        }

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setStatus(Appointment.AppointmentStatus.SCHEDULED.getPrettyName());
        appointment.setSpecialty(specialty);
        appointment.setRegisterTime(LocalDateTime.now());
        appointment.setPlannedStartTime(LocalDateTime.of(year,month,day,hour,0));
        appointment.setPlannedEndTime(LocalDateTime.of(year,month,day,hour+1,0));
        appointment.setPaid(false);

        appointmentRepository.save(appointment);

        return appointment;
    }

    public void changeAppointmentStatus(Appointment appointment, String status){
        Appointment appointmentAux = appointmentRepository.getReferenceById(appointment.getId());
        appointmentAux.setStatus(status);
        appointmentRepository.save(appointmentAux);
    }


}
