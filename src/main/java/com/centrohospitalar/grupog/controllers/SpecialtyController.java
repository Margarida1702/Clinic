package com.centrohospitalar.grupog.controllers;

import com.centrohospitalar.grupog.entities.Specialty;
import com.centrohospitalar.grupog.entities.User;
import com.centrohospitalar.grupog.exceptions.SpecialtyException;
import com.centrohospitalar.grupog.services.AppointmentService;
import com.centrohospitalar.grupog.services.AuthenticationService;
import com.centrohospitalar.grupog.services.DoctorService;
import com.centrohospitalar.grupog.services.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class SpecialtyController {

    @Autowired
    SpecialtyService specialtyService;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    AppointmentService appointmentService;

    @GetMapping("/registerSpecialty")
    public ModelAndView registerSpecialtyPage(){
        return new ModelAndView("registerSpecialty");
    }

    @PostMapping("/registerSpecialtyProcess")
    public ModelAndView registerSpecialtyProcess(String name, String description, String imageName) {
        try {

            specialtyService.createSpecialty(name, description, imageName);

        } catch (SpecialtyException e) {
            ModelAndView mav = new ModelAndView("registerSpecialty");
            mav.addObject("erro", e.getMessage());
            return mav;
        }
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/specialtyManagement")
    public ModelAndView specialtyManagementPage(){
        ModelAndView mav = new ModelAndView("specialtyManagement");
        User user = authenticationService.getAutheticatedUser();
        mav.addObject("user",user);

        Map<Specialty,Integer> specialtiesDoctorCount = specialtyService.SpecialtiesDoctorCount();

        mav.addObject("specialtyList",specialtyService.getSpecialties());
        mav.addObject("specialtiesDoctorCount", specialtiesDoctorCount);

        mav.addObject("appointmentList", appointmentService.getAppointments());
        //TODO Check
        mav.addObject("countNumberAppointments", 0);

        return mav;
    }

    @GetMapping("/editSpecialty")
    public ModelAndView editSpecialty(Long id){
        ModelAndView mav = new ModelAndView("editSpecialty");
        mav.addObject("user", authenticationService.getAutheticatedUser());
        mav.addObject("specialty", specialtyService.getSpecialtyById(id));

        return mav;
    }

    @PostMapping("/editSpecialtyProcess")
    public ModelAndView editSpecialtyProcess(Long id, String name, String description, String imageName) {
        try {

            specialtyService.updateSpecialty(id, name, description, imageName);

        } catch (SpecialtyException e) {
            ModelAndView mav = editSpecialty(id);
            mav.addObject("error", e.getMessage());
            return mav;
        }

        ModelAndView mav = specialtyManagementPage();
        mav.addObject("specialtyUpdated", "Especialidade atualizada com sucesso!");
        return mav;
    }
}
