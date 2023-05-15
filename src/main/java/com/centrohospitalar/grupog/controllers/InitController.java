package com.centrohospitalar.grupog.controllers;


import com.centrohospitalar.grupog.entities.Doctor;
import com.centrohospitalar.grupog.entities.Patient;
import com.centrohospitalar.grupog.entities.Role;
import com.centrohospitalar.grupog.entities.Specialty;
import com.centrohospitalar.grupog.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class InitController {


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

    @PostConstruct
    void initDB(){
        Random random = new Random();
        if(userService.findAll().isEmpty() || userService.findAll()==null){
            specialtyService.initSpecialties();
            userService.initUsers();

          /*  for(Doctor doctor: doctorService.getDoctors()){
                List<Specialty> specialties= specialtyService.getSpecialties();
                Specialty randomSpecialty = specialties.get()
                specialtyService.addSpecialtyToDoctor(doctor)


            }*/

        }else {
            System.out.println("Did Nothing");
        }


    }


}
