package com.centrohospitalar.grupog.controllers;

import com.centrohospitalar.grupog.entities.Doctor;
import com.centrohospitalar.grupog.entities.Specialty;
import com.centrohospitalar.grupog.entities.User;
import com.centrohospitalar.grupog.exceptions.GeneralInformationException;
import com.centrohospitalar.grupog.services.*;
import com.centrohospitalar.grupog.utilities.DateTimeFormater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    UserService userService;
    @Autowired
    SpecialtyService specialtyService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    GeneralInformationService generalInformationService;


    @GetMapping("/")
    public ModelAndView indexPage() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("specialtiesList", specialtyService.getSpecialties());
        return mav;
    }

    @GetMapping("/test")
    public ModelAndView test() {
        ModelAndView mav = new ModelAndView("test");
        User user = this.authenticationService.getAutheticatedUser();
        mav.addObject("wtv",user);
        return mav;
    }
    @GetMapping("/dashboard")
    public ModelAndView dashboardRedirect(){
        User user = this.authenticationService.getAutheticatedUser();
        ModelAndView mav=null;

        if(user.getRole().getName().equals("ROLE_ADMIN")){
            mav = new ModelAndView("adminDashboard");
            mav.addObject("user",user);
            mav.addObject("doctorList", doctorService.getDoctorsByActiveAccountTrue());
            mav.addObject("adminList", employeeService.getEmployeeByActiveAccountTrueAndRole_Name("ROLE_ADMIN"));
            mav.addObject("deskReceptionistList", employeeService.getEmployeeByActiveAccountTrueAndRole_Name("ROLE_DESKRECEPTIONIST"));
            mav.addObject("patientList", userService.getPatientByActiveAccountTrue());
            mav.addObject("specialtyList", specialtyService.getSpecialties());

        } else if(user.getRole().getName().equals("ROLE_UNITMANAGER")){
            mav = new ModelAndView("unitManagerDashboard");
            mav.addObject("user",user);

        } else if(user.getRole().getName().equals("ROLE_DOCTOR")){
            mav = new ModelAndView("doctorDashboard");
            mav.addObject("user",user);
            mav.addObject("appointments", appointmentService.getPendingAppointmentsByDoctorId(user.getId()));
            mav.addObject("totalAppointments", appointmentService.getTotalDailyAppointmentsByDoctorId(user.getId()));

        }else if(user.getRole().getName().equals("ROLE_DESKRECEPTIONIST")){
            mav = new ModelAndView("deskReceptionistDashboard");
            mav.addObject("user",user);

        }else if(user.getRole().getName().equals("ROLE_PATIENT")){
            mav = new ModelAndView("patientDashboard");
            mav.addObject("user",user);
        }

        return mav;
    }

    @GetMapping("/visualiseDoctors")
    public ModelAndView visualiseDoctorsPage() {
        ModelAndView mav = new ModelAndView("visualiseDoctors");
        mav.addObject("user",authenticationService.getAutheticatedUser());
        mav.addObject("doctorList", userService.getDoctors());

        return mav;
    }

    @GetMapping("/visualiseSpecialties")
    public ModelAndView visualiseSpecialtiesPage() {
        ModelAndView mav = new ModelAndView("visualiseSpecialties");
        mav.addObject("user",authenticationService.getAutheticatedUser());
        mav.addObject("specialtyList", specialtyService.getSpecialties());

        return mav;
    }

    @GetMapping("/contacts")
    public ModelAndView contactsPage(){

        ModelAndView mav = new ModelAndView("contacts");
        mav.addObject("user", authenticationService.getAutheticatedUser());

        return mav;
    }

    @GetMapping("/generalInformation")
    public ModelAndView generalInformationPage(){

        ModelAndView mav = new ModelAndView("generalInformation");
        mav.addObject("user", authenticationService.getAutheticatedUser());
        mav.addObject("formatter", new DateTimeFormater());
        mav.addObject("generalInformationList", generalInformationService.findAll());

        return mav;
    }

    @GetMapping("/createGeneralInformation")
    public ModelAndView createGeneralInformationPage(){

        ModelAndView mav = new ModelAndView("createGeneralInformation");
        mav.addObject("user", authenticationService.getAutheticatedUser());

        return mav;
    }

    @PostMapping("/createGeneralInformationProcess")
    public ModelAndView createGeneralInformationProcess(String title, String description, String image){
        try{
            generalInformationService.createGeneralInformation(title,description,image);
        } catch (GeneralInformationException e){
            ModelAndView mav = new ModelAndView("createGeneralInformation");
            mav.addObject("user", authenticationService.getAutheticatedUser());
            mav.addObject("error", e.getMessage());
        }
        ModelAndView mav = generalInformationPage();
        mav.addObject("generalInfo", "Parabéns! Adicionado novo Post!");
        return mav;
    }

}