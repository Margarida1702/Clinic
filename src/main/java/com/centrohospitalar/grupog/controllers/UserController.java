package com.centrohospitalar.grupog.controllers;

import com.centrohospitalar.grupog.entities.*;
import com.centrohospitalar.grupog.exceptions.AuthenticationException;
import com.centrohospitalar.grupog.models.AdminAllSignup;
import com.centrohospitalar.grupog.models.DoctorSignUpData;
import com.centrohospitalar.grupog.models.PatientSignUpData;
import com.centrohospitalar.grupog.services.*;
import com.centrohospitalar.grupog.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {


        @Autowired
        AuthenticationService authenticationService;
        @Autowired
        SpecialtyService specialtyService;
        @Autowired
        UserService userService;
        @Autowired
        EmployeeService employeeService;
        @Autowired
        DoctorService doctorService;
        @Autowired
        ScheduleService scheduleService;

        @GetMapping("/registerDoctor")
        public ModelAndView registerDoctor(){
                ModelAndView mav = new ModelAndView("registerDoctor");
                mav.addObject("user",authenticationService.getAutheticatedUser());
                mav.addObject("specialties",specialtyService.getSpecialties());

                return mav;
        }

        @PostMapping("/doctorRegisterProcess")
        public ModelAndView doctorRegisterProcess(DoctorSignUpData doctorSignUpData, HttpServletRequest request) {
                try {
                        Doctor doctor = authenticationService.createDoctorAccount(doctorSignUpData);
                        request.getParameter("specialities");
                        String siteURL = Utility.getSiteURL(request);
                        authenticationService.sendVerificationEmail(doctor, siteURL);

                } catch (AuthenticationException | MessagingException | UnsupportedEncodingException e) {
                        ModelAndView mav = new ModelAndView("registerDoctor");
                        mav.addObject("erro", e.getMessage());
                        return mav;
                }
                return new ModelAndView("redirect:/doctorManagement");
        }

        @GetMapping("/editAccountDetails")
        public ModelAndView editAccountDetails(){
                ModelAndView mav = new ModelAndView("editAccountDetails");
                mav.addObject("user",authenticationService.getAutheticatedUser());

                return mav;
        }

        @PostMapping("editAccountDetailsPatientProcess")
        public ModelAndView editAccountDetailsPatientProcess(PatientSignUpData patientSignUpData){
                User user = this.authenticationService.getAutheticatedUser();
                try {
                        userService.updateUser(patientSignUpData, user.getEmail());
                        ModelAndView mav = new ModelAndView("editAccountDetails");
                        mav.addObject("user",user);
                        mav.addObject("accountUpdated","Dados actualizados com sucesso! :)");
                        return mav;
                } catch (AuthenticationException e) {
                        ModelAndView mav = new ModelAndView("editAccountDetails");
                        mav.addObject("user",user);
                        mav.addObject("erro",e.getMessage());
                        return mav;
                }
        }

        @GetMapping("/registerAdmin")
        public ModelAndView registerAdminPage(){
                ModelAndView mav = new ModelAndView("registerAdmin");
                mav.addObject("user",authenticationService.getAutheticatedUser());
                return mav;
        }

        @PostMapping("/registerAdminProcess")
        public ModelAndView registerAdminProcess(AdminAllSignup adminAllSignup, HttpServletRequest request) {
                try {
                        Admin admin = userService.createAdminAccount(adminAllSignup);
                        String siteURL = Utility.getSiteURL(request);
                        authenticationService.sendVerificationEmail(admin, siteURL);
                } catch (AuthenticationException | MessagingException | UnsupportedEncodingException e) {
                        ModelAndView mav = new ModelAndView("registerAdmin");
                        mav.addObject("erro", e.getMessage());
                        return mav;
                }
                return new ModelAndView("redirect:/adminDashboard");
        }

        @GetMapping("/patient_list")
        public ModelAndView PatientListPage(){
                ModelAndView mav = new ModelAndView("patient_list");
                mav.addObject("user",authenticationService.getAutheticatedUser());
                mav.addObject("allUsers", userService.findAll());
                return mav;
        }

        @GetMapping("/userManagement")
        public ModelAndView userManagementPage(){
                ModelAndView mav = new ModelAndView("userManagement");
                mav.addObject("user",authenticationService.getAutheticatedUser());
                mav.addObject("allUsers", userService.findAll());
                return mav;
        }

        @PostMapping("/disableUser")
        public RedirectView disableUser(Long id) {
                userService.disableUser(id);
                return new RedirectView("/userManagement");
        }

        @PostMapping("/userProfilePage")
        public ModelAndView userProfilePage(Long profileUserId){
                ModelAndView mav = new ModelAndView("userProfilePage");
                mav.addObject("user", authenticationService.getAutheticatedUser());
                User userAux = userService.getUserById(profileUserId);

                if(userAux.getRole().getName().equals("ROLE_ADMIN")){
                        Admin profileUser = userService.getAdminById(userAux.getId());
                        mav.addObject("profileUser", profileUser);
                }else
                if(userAux.getRole().getName().equals("ROLE_PATIENT")){
                        Patient profileUser = userService.getPatientById(userAux.getId());
                        mav.addObject("profileUser", profileUser);
                }else
                if(userAux.getRole().getName().equals("ROLE_UNITMANAGER")){
                        UnitManager profileUser = userService.getUnitManagerById(userAux.getId());
                        mav.addObject("profileUser", profileUser);
                }else
                if(userAux.getRole().getName().equals("ROLE_DOCTOR")){
                        Doctor profileUser = userService.getDoctorById(userAux.getId());
                        mav.addObject("profileUser", profileUser);
                }else
                if(userAux.getRole().getName().equals("ROLE_DESKRECEPTIONIST")){
                        DeskReceptionist profileUser = userService.getDeskReceptionistById(userAux.getId());
                        mav.addObject("profileUser", profileUser);
                }

                return mav;
        }
//        @GetMapping("/edit_own_user_profile")
//        public ModelAndView editOwnUserProfilePage(){
//                ModelAndView mav = new ModelAndView("edit_own_user_profile");
//                mav.addObject("user", authenticationService.getAutheticatedUser());
//                return mav;
//        }
        @GetMapping("/edit_user_profile")
        public ModelAndView editUserProfilePage(Long profileUserId){
                ModelAndView mav = new ModelAndView("edit_user_profile");
                mav.addObject("user", authenticationService.getAutheticatedUser());
                User userAux = userService.getUserById(profileUserId);

                if(userAux.getRole().getName().equals("ROLE_ADMIN")){
                        Admin profileUser = userService.getAdminById(userAux.getId());
                        mav.addObject("profileUser", profileUser);
                }else
                if(userAux.getRole().getName().equals("ROLE_PATIENT")){
                        Patient profileUser = userService.getPatientById(userAux.getId());
                        mav.addObject("profileUser", profileUser);
                }else
                if(userAux.getRole().getName().equals("ROLE_UNITMANAGER")){
                        UnitManager profileUser = userService.getUnitManagerById(userAux.getId());
                        mav.addObject("profileUser", profileUser);
                }else
                if(userAux.getRole().getName().equals("ROLE_DOCTOR")){
                        Doctor profileUser = userService.getDoctorById(userAux.getId());
                        mav.addObject("profileUser", profileUser);
                }else
                if(userAux.getRole().getName().equals("ROLE_DESKRECEPTIONIST")){
                        DeskReceptionist profileUser = userService.getDeskReceptionistById(userAux.getId());
                        mav.addObject("profileUser", profileUser);
                }

                return mav;
        }


        @PostMapping("/schedules")
        public ModelAndView schedulesPage(@Nullable Long employeeID){
                if(employeeID==null) {
                        ModelAndView mav = new ModelAndView("schedules");
                        mav.addObject("user", authenticationService.getAutheticatedUser());
                        return mav;
                } else {
                        ModelAndView mav = new ModelAndView("schedules");
                        mav.addObject("user", authenticationService.getAutheticatedUser());
                        Doctor employee = (Doctor) userService.getUserById(employeeID);
                        List scalesList = employee.getDateScalePeriodList();
                        mav.addObject("scale", scalesList);

                        return mav;
                }
        }

        @GetMapping("/registerDeskReceptionist")
        public ModelAndView registerDeskReceptionistPage(){
                ModelAndView mav = new ModelAndView("registerDeskReceptionist");
                mav.addObject("user",authenticationService.getAutheticatedUser());
                return mav;
        }

        @PostMapping("/registerDeskReceptionistProcess")
        public ModelAndView registerDeskReceptionistProcess(PatientSignUpData patientSignUpData, HttpServletRequest request) {
                try {
                        DeskReceptionist deskReceptionist = userService.createDeskReceptionistAccount(patientSignUpData);
                        String siteURL = Utility.getSiteURL(request);
                        authenticationService.sendVerificationEmail(deskReceptionist, siteURL);
                } catch (AuthenticationException | MessagingException | UnsupportedEncodingException e) {
                        ModelAndView mav = new ModelAndView("registerDeskReceptionist");
                        mav.addObject("erro", e.getMessage());
                        mav.addObject("user", authenticationService.getAutheticatedUser());
                        return mav;
                }
                return userManagementPage();
        }

        @GetMapping("/createSchedule") //createSchedule?employeeId=6
        public ModelAndView createSchedule(Long employeeId){
                ModelAndView mav = new ModelAndView("createSchedule");
                mav.addObject("user", authenticationService.getAutheticatedUser());

                Employee employee = employeeService.getById(employeeId);
                Doctor doctor;

                mav.addObject("employee", employee);

                if(employee.getRole().getName().equals("ROLE_DOCTOR")) {
                        doctor = doctorService.getDoctorById(employeeId);
                        mav.addObject("doctor", doctor);
                }
                return mav;
        }

        @PostMapping("/createScheduleProcess")
        public ModelAndView createScheduleProcess(Long employeeId, String [] weekDays, String startDay, String finishDay, String startHour,
                                                  String finishHour, String[] specialties){
                Employee employee = employeeService.getById(employeeId);
                //List <String> specialtyStringList = new ArrayList<>();
                List <Specialty> specialtyList = new ArrayList<>();

               for(String specialtyName: specialties){
                        specialtyList.add(specialtyService.getSpecialtyByName(specialtyName));
                }


                String[] startDayAux = startDay.split("-");
                String[] finishDayAux = finishDay.split("-");
                String[] startHourAux = startHour.split(":");
                String[] finishHourAux = finishHour.split(":");


                System.out.println("x");
                LocalDate startDayLocalDate = LocalDate.of(Integer.parseInt(startDayAux[0]),Integer.parseInt(startDayAux[1]),Integer.parseInt(startDayAux[2]));
                LocalDate finishDayLocalDate = LocalDate.of(Integer.parseInt(finishDayAux[0]),Integer.parseInt(finishDayAux[1]),Integer.parseInt(finishDayAux[2]));

                LocalTime startHourTime = LocalTime.of(Integer.parseInt(startHourAux[0]),Integer.parseInt(startHourAux[1]));
                LocalTime finishHourTime = LocalTime.of(Integer.parseInt(finishHourAux[0]),Integer.parseInt(finishHourAux[1]));

                List<DateScalePeriod> dateScalePeriodList = scheduleService.createDateScalePeriodsFromSchedule(employee, weekDays, startDayLocalDate, finishDayLocalDate, startHourTime, finishHourTime, specialtyList);

                Map<DateScalePeriod,String> reportScheduleCreationStatus = scheduleService.addDateScalePeriodListToEmployee(dateScalePeriodList, employee);


                return userManagementPage();
        }
}
