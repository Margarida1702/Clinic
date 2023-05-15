package com.centrohospitalar.grupog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserAuthenticationProvider userAuthenticationProvider;


    @Override
    protected void configure(HttpSecurity http) throws Exception { //!!!!!!tem de ser alterado
        http
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticationProcess")
                .usernameParameter("email")
                .defaultSuccessUrl("/dashboard")//!!!!!!!!!!tem de reencaminhar conforme user
        .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")//!!!!!!!! mudar para o home?
                .deleteCookies("JSESSIONID")
        .and()
            .csrf()
                .disable()
            .authorizeRequests()
                .antMatchers("/", "/login", "/signup", "/signupProcess", "/forgotPassword", "/forgotPasswordProcess", "/resetPassword",
                        "/verifyAccount").permitAll()
                .antMatchers("/dashboard", "/appointments_choose_specialty", "/appointment_slots", "/contacts", "/visualiseDoctors", "/visualiseSpecialties",
                        "/edit_own_user_profile", "/generalInformation", "cancel_appointment", "/appointments_main", "/patient_appointments_list",
                        "/appointmentsDetails", "/seeInvoice", "/view_appointment", "/userProfilePage", "/editAccountDetails", "/edit_user_profile",
                        "/editAccountDetailsPatientProcess").authenticated()
                .antMatchers("/appointmentCheckInProcess", "/enterWaitingList", "/confirm_appointment", "/payInvoice").hasAnyRole("PATIENT", "DESKRECEPCIONIST")
                .antMatchers("/dashboardEmployee").hasAnyRole("DESKRECEPCIONIST", "UNITMANAGER")
                .antMatchers("/userManagement","/specialtyManagement", "/editSpecialty", "/editSpecialtyProcess", "/registerDeskReceptionist",
                        "/registerDeskReceptionistProcess", "/disableUser", "/createGeneralInformation", "/createGeneralInformationProcess","/createSchedule",
                        "/createScheduleProcess", "/registerDoctor", "/doctorRegisterProcess", "/registerSpecialty", "/registerSpecialtyProcess",
                        "/schedules", "/registerAdminProcess", "/registerAdmin", "/adminDashboard").hasRole("ADMIN")
                .antMatchers("/patientDashboard", "/appointments_main_patient",
                        "/editMyAccountDetailsPatient").hasRole("PATIENT")
                .antMatchers("/doctorDashboard", "/appointment_write_finish", "/acceptLateCheckIn", "/refuseLateCheckIn", "/startAppointment",
                        "/endAppointment","/current_appointment", "/appointments_main_doctor", "/next_appointment").hasRole("DOCTOR")
                .antMatchers("/doctor_appointments_list", "/patient_list").hasAnyRole("ADMIN", "DOCTOR")
                .antMatchers("/appointments_main_bypatient", "/appointments_main_bydoctor").hasAnyRole("ADMIN", "DOCTOR", "DESKRECEPCIONIST", "UNITMANAGER")
                .antMatchers("/styles/**", "/images/**", "/scripts/**","/landing/**").permitAll()
                .antMatchers("**").denyAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userAuthenticationProvider);
    }


}
