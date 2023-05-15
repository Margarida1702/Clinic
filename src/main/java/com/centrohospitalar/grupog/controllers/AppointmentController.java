package com.centrohospitalar.grupog.controllers;

import com.centrohospitalar.grupog.entities.*;
import com.centrohospitalar.grupog.exceptions.AppointmentException;
import com.centrohospitalar.grupog.models.Slot;
import com.centrohospitalar.grupog.services.*;
import com.centrohospitalar.grupog.utilities.DateTimeFormater;
import com.centrohospitalar.grupog.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AppointmentController {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserService userService;

    @Autowired
    DoctorService doctorService;

    @Autowired
    SpecialtyService specialtyService;

    @Autowired
    AppointmentService appointmentService;

    @Autowired
    ScheduleService scheduleService;
    @Autowired
    InvoiceService invoiceService;
    @Autowired
    WaitingListSpotService waitingListSpotService;

    @GetMapping("/appointments_main")
    public ModelAndView appointmentsRedirect(){
        ModelAndView mav = null;
        User user = this.authenticationService.getAutheticatedUser();
        if(user.getRole().getName().equals("ROLE_ADMIN")){                          //ADMIN vv
            mav = new ModelAndView("appointments_main_general");
            mav.addObject("user",user);
            mav.addObject("formatter", new DateTimeFormater());
            mav.addObject("appointmentList", appointmentService.getAppointmentsByPlannedStartTimeAfterOrderByPlannedStartTime(LocalDate.now()));
        } else if(user.getRole().getName().equals("ROLE_UNITMANAGER")){             //ADMIN  ^^
            mav = new ModelAndView("appointments_main_general");
            mav.addObject("formatter", new DateTimeFormater());
            mav.addObject("user",user);
            mav.addObject("appointmentList", appointmentService.getAppointmentsByPlannedStartTimeAfterOrderByPlannedStartTime(LocalDate.now()));
        } else if(user.getRole().getName().equals("ROLE_DOCTOR")){                  //DOCTOR vv
            mav = new ModelAndView("appointments_main_doctor");
            mav.addObject("formatter", new DateTimeFormater());
            mav.addObject("user",user);
            mav.addObject("currentAppointment", appointmentService.getAppointmentByStatus("Em curso"));
            mav.addObject("appointmentList", appointmentService.getAppointmentsByDoctorIdAndPlannedStartTimeAfterOrderByPlannedStartTime(user.getId(), LocalDate.now()));
        }else if(user.getRole().getName().equals("ROLE_DESKRECEPTIONIST")){         //DOCTOR  ^^
            mav = new ModelAndView("appointments_main_general");
            mav.addObject("formatter", new DateTimeFormater());
            mav.addObject("user",user);
            mav.addObject("appointmentList", appointmentService.getAppointmentsByPlannedStartTimeAfterOrderByPlannedStartTime(LocalDate.now()));
        }else if(user.getRole().getName().equals("ROLE_PATIENT")){                  //PATIENT vv
            mav = new ModelAndView("appointments_main_patient");
            mav.addObject("formatter", new DateTimeFormater());
            mav.addObject("user",user);
            mav.addObject("appointmentList", appointmentService.getAppointmentsByPatientIdAndPlannedStartTimeAfterOrderByPlannedStartTime(user.getId(), LocalDate.now()));
            mav.addObject("invoiceLinkPrefix", "https://serro.pt/invoices/588260745/get/");

        }                                                                           //PATIENT ^^
        return mav;
    }

    @GetMapping("/appointmentsDetails")
    public ModelAndView appointmentDetailsPage(Long id){
        ModelAndView mav = new ModelAndView("appointmentsDetails");
        User user = this.authenticationService.getAutheticatedUser();
        mav.addObject("user",user);
        mav.addObject("appointment", appointmentService.getAppointmentById(id));

        return mav;
    }

    @GetMapping("/appointments")
    public ModelAndView appointmentPage(){
        User user = this.authenticationService.getAutheticatedUser();
        ModelAndView mav = new ModelAndView();
        mav.addObject("user", user);
        return mav;
    }

    @GetMapping("/appointment_slots")
    public ModelAndView makeAppointmentPage(@Nullable Integer weekOffSet, Specialty specialty, @Nullable Doctor doctor){
        if(weekOffSet == null) {
            weekOffSet = 0;
        }
        ModelAndView mav = new ModelAndView("appointment_slots");
        mav.addObject("user", authenticationService.getAutheticatedUser());
        mav.addObject("weekDay", LocalDate.now().plusWeeks(weekOffSet));
        mav.addObject("localDate", LocalDate.now());
        mav.addObject("currentYear", LocalDate.now().getYear());
        mav.addObject("weekOffSet", weekOffSet);
        mav.addObject("doctorList", userService.getDoctorsBySpecialty(specialty));
        if (doctor != null && doctor.getId() != 0){
            List<Slot> schedulesList = scheduleService.getSpecialtyFreeVacanciesByDoctorAndTimeInterval(specialty,doctor,LocalDateTime.now(),LocalDateTime.now().plusWeeks(weekOffSet+1),Specialty.SPECIALTY_APPOINTMENT_MINUTES_DUR);

            Map<String,Slot> slotMap = new HashMap<>();

            //TODO Tem de ser HashMap
            for(Slot slot: schedulesList){
                slotMap.put(""+ slot.getStartTime().getHour()+"_"+slot.getStartTime().getDayOfMonth()+"_"+slot.getStartTime().getMonth().getValue()+"_"+slot.getStartTime().getYear(),slot);
            }
            mav.addObject("slotMap",slotMap);
            //String value="".+(hora)+("_")+(weekDay.plusDays(day - weekDay.getDayOfWeek().getValue()).getDayOfMonth()).+("_")+(weekDay.getMonth().getValue())+("_")+(weekDay.getYear());

        } else {
            Map<String,Slot> slotMap = new HashMap<>();
            mav.addObject("slotMap",slotMap);

        }
            //mav.addObject("schedulesList", scheduleService.getSpecialtyFreeVacanciesByDoctorAndTimeInterval(specialty,doctor,LocalDateTime.now(),LocalDateTime.now().plusWeeks(weekOffSet),Specialty.SPECIALTY_APPOINTMENT_MINUTES_DUR));

        return mav;
    }

    @GetMapping("/confirm_appointment")
    public ModelAndView confirmAppointmentPage(Specialty specialty, Doctor doctor, Integer hour, Integer day, Integer month, Integer year){
        ModelAndView mav = new ModelAndView("confirm_appointment");
        try {
            if (authenticationService.getAutheticatedUser().getRole().getName().equals("ROLE_PATIENT")){
                Patient patient = (Patient) authenticationService.getAutheticatedUser();
                Appointment appointment = appointmentService.createAppointment(patient, specialty, doctor, hour, day, month,year);
                mav.addObject("appointment", appointment);
                DateTimeFormater dt = new DateTimeFormater();
                mav.addObject("dateFormatter",dt);
                mav.addObject("sucess", ("Consulta de " + appointment.getSpecialty().getName() + " marcada com sucesso para " +
                        dt.dateToPrettyNameString(appointment.getPlannedStartTime()) + "!"));
            }
        } catch (AppointmentException e) {
            mav.addObject("error", e.getMessage());
        }
        return mav;
    }

    @GetMapping("/appointments_choose_specialty")
    public ModelAndView appointmentsChooseSpecialtyPage(){
        User user = this.authenticationService.getAutheticatedUser();
        ModelAndView mav = new ModelAndView("appointments_choose_specialty");
        mav.addObject("user", user);
        mav.addObject("specialties", specialtyService.getSpecialties());
        return mav;
    }

    @GetMapping("/doctor_appointments_list")
    public ModelAndView doctorAppointmentsListPage(){
        ModelAndView mav = new ModelAndView("doctor_appointments_list");
        Long userId = authenticationService.getAutheticatedUser().getId();
        mav.addObject("appointmentsList", appointmentService.getAppointmentsByDoctorId(userId));
        return mav;
    }

    @GetMapping("/patient_appointments_list")
    public ModelAndView patientAppointmentsListPage(){
        ModelAndView mav = new ModelAndView("patient_appointments_list");
        Long userId = authenticationService.getAutheticatedUser().getId();
        mav.addObject("appointmentsList", appointmentService.getAppointmentsByPatientId(userId));
        return mav;
    }
    @GetMapping("/appointmentCheckInProcess")
    public ModelAndView checkInProcess(String appointmentIdString) {
        User user = this.authenticationService.getAutheticatedUser();
        ModelAndView mav=null;
        String appointmentMessageError=null;

        if(user.getRole().getName().equals("ROLE_ADMIN")){
            mav = new ModelAndView("adminDashboard");
            mav.addObject("user",user);
        } else if(user.getRole().getName().equals("ROLE_UNITMANAGER")){
            mav = new ModelAndView("unitManagerDashboard");
            mav.addObject("user",user);
        } else if(user.getRole().getName().equals("ROLE_DOCTOR")){
            mav = new ModelAndView("doctorDashboard");
            mav.addObject("user",user);
        }else if(user.getRole().getName().equals("ROLE_DESKRECEPTIONIST")){
            mav = new ModelAndView("deskReceptionistDashboard");
            mav.addObject("user",user);
        }else if(user.getRole().getName().equals("ROLE_PATIENT")){
            mav = new ModelAndView("patientDashboard");
            mav.addObject("user",user);
        }

        try {
            if (user.getRole().getName().equals("ROLE_PATIENT")) { //se check in for feito por PATIENT
                this.appointmentService.patientCheckInApointment(appointmentIdString, (Patient) user); //converts user to Patient

            } else {                                                //se check in for feito por qualquer role (por exemplo recepcionista) EXCEPTO PATIENT
                this.appointmentService.checkInApointment(appointmentIdString);
            }
            mav.addObject("appointmentSucessMessage", "Checkin feito com sucesso");
            return mav;
        } catch (AppointmentException e) {
            appointmentMessageError=e.getMessage();
            mav.addObject("appointmentMessageError",appointmentMessageError);
            return mav;
        }
    }

    @GetMapping("/view_appointment")
    public ModelAndView viewAppointmentPage(@Nullable Appointment appointment){
        ModelAndView mav = new ModelAndView("view_appointment");
        mav.addObject("user", authenticationService.getAutheticatedUser());

        if(appointment != null && appointment.getId() != 0) {
            mav.addObject("appointment", appointment);
            return mav;
        }
        mav.addObject("appointment", null);
        return mav;
    }

    @GetMapping("/next_appointment")
    public ModelAndView getNextAppointment() {
        User user = authenticationService.getAutheticatedUser();
        LocalDate currentDay = LocalDate.now();
        Appointment nextAppointment = appointmentService.getAppointmentByDoctorIdAndStatusAndPlannedStartTimeBetweenOrderByPlannedStartTime(user.getId(), currentDay);
        if(nextAppointment != null) {
            return new ModelAndView("redirect:/view_appointment?appointment="+nextAppointment.getId());
        }
        return new ModelAndView("redirect:/view_appointment");
    }

    @GetMapping("/current_appointment")
    public ModelAndView getCurrentAppointment() {
        User user = authenticationService.getAutheticatedUser();
        LocalDate currentDay = LocalDate.now();
        Appointment currentAppointment = appointmentService.getCurrentAppointment(user.getId(), currentDay);
        return new ModelAndView("redirect:/view_appointment?appointment="+currentAppointment.getId());
    }

    @PostMapping("/startAppointment")
    public ModelAndView startAppointment(Appointment appointment) {
        ModelAndView mav = new ModelAndView("view_appointment");
        appointmentService.startAppointment(appointment);
        User user = authenticationService.getAutheticatedUser();
        mav.addObject("user", user);
        mav.addObject("appointment", appointment);
        return mav;
    }

    @PostMapping("/endAppointment") //ERRO OBSERVATIONS E PRICE SAO ENVIADOS NULL
    public ModelAndView endAppointment(HttpServletRequest request, Appointment appointment, String description, String price) {
        ModelAndView mav;

        try{
            appointmentService.endAppointment(appointment, description, price);
            mav = appointmentsRedirect();
            mav.addObject("invoiceResponse", invoiceService.createInvoice(appointment));
        } catch (AppointmentException e){
            mav = viewAppointmentPage(appointment);
            mav.addObject("error", e.getMessage());
        }
        return mav;
    }

    @GetMapping("/appointment_write_finish")
    public ModelAndView appointment_write_finish(Appointment appointment){
        ModelAndView mav = new ModelAndView("appointment_write_finish");
        mav.addObject("user", authenticationService.getAutheticatedUser());
        mav.addObject("appointment", appointment);
        mav.addObject("patient", appointment.getPatient());
        return mav;
    }

    @PostMapping("/cancel_appointment")
    public ModelAndView cancelAppointment(Appointment appointment) {
        ModelAndView mav = appointmentsRedirect();
        User user = authenticationService.getAutheticatedUser();
        appointmentService.cancelAppointment(appointment, user);
        mav.addObject("user", user);
        mav.addObject("appointment", appointment);
        return new ModelAndView("redirect:/appointments_main");
    }

    @GetMapping("/appointments_main_bypatient")
    public ModelAndView appointmentsMainByPatientId(Patient patient) {
        ModelAndView mav = new ModelAndView("appointments_main_bypatient");
        User user = authenticationService.getAutheticatedUser();
        mav.addObject("user", user);
        List<Appointment> appointmentList = appointmentService.getAppointmentsByPatientId(patient.getId());
        mav.addObject("patient", patient);
        mav.addObject("appointmentList", appointmentList);
        return mav;
    }
    @GetMapping("/appointments_main_bydoctor")
    public ModelAndView appointmentsMainByDoctorId(Doctor doctor) {
        ModelAndView mav = new ModelAndView("appointments_main_bydoctor");
        User user = authenticationService.getAutheticatedUser();
        mav.addObject("user", user);
        List<Appointment> appointmentList = appointmentService.getAppointmentsByDoctorId(doctor.getId());
        mav.addObject("doctor", doctor);
        mav.addObject("appointmentList", appointmentList);
        return mav;
//        return new RedirectView("/current_appointment");
    }

    @PostMapping("/payInvoice")
    public ModelAndView payInvoice(Appointment appointment) {
        ModelAndView mav = appointmentsRedirect();
        appointmentService.payAppointment(appointment);
        mav.addObject("payResponse", invoiceService.payInvoice(appointment));
        return mav;
    }

    @PostMapping("/seeInvoice")
    public ModelAndView seeInvoice(Appointment appointment) {
        ModelAndView mav = new ModelAndView("redirect:https://serro.pt/invoices/588260745/get/" + appointment.getInvoiceIdentity());
        return mav;
    }

    @PostMapping("/enterWaitingList")
    public ModelAndView enterWaitingList(Specialty specialty, Doctor doctor){
        WaitingListSpot wtls = waitingListSpotService.enterWaitingList(userService.getPatientById(authenticationService.getAutheticatedUser().getId()),doctor, specialty);
        ModelAndView mav = appointmentsRedirect();
        mav.addObject("waitingListObj", wtls);
        mav.addObject("formatter", new DateTimeFormater());
        mav.addObject("waitingListSuccess", ("Entrou na lista de espera para a especialidade de " + wtls.getSpecialty().getName() + ". Contamos ser breves!"));
        return mav;
    }

    @PostMapping("/acceptLateCheckIn")
    public ModelAndView acceptLateCheckIn(Appointment appointment){
        ModelAndView mav = appointmentsRedirect();
        appointmentService.changeAppointmentStatus(appointment, "Em curso");
        return mav;
    }

    @PostMapping("/refuseLateCheckIn")
    public ModelAndView refuseLateCheckIn(Appointment appointment){
        ModelAndView mav = appointmentsRedirect();
        appointmentService.changeAppointmentStatus(appointment, "Paciente faltou");
        return mav;
    }

}
