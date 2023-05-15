package com.centrohospitalar.grupog.controllers;

import com.centrohospitalar.grupog.entities.Patient;
import com.centrohospitalar.grupog.entities.User;
import com.centrohospitalar.grupog.exceptions.AuthenticationException;
import com.centrohospitalar.grupog.exceptions.CustomerNotFoundException;
import com.centrohospitalar.grupog.models.PatientSignUpData;
import com.centrohospitalar.grupog.services.AuthenticationService;
import com.centrohospitalar.grupog.utilities.Utility;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @GetMapping("/signup")
    public ModelAndView registerPage() {
        ModelAndView mav = new ModelAndView("signup");
        return mav;
    }

    @PostMapping("/signupProcess")
    public ModelAndView signupProcess(PatientSignUpData patientSignUpData, HttpServletRequest request) {
        try {
            Patient patient = authenticationService.createPatientAccount(patientSignUpData);

            String siteURL = Utility.getSiteURL(request);
            authenticationService.sendVerificationEmail(patient, siteURL);
        } catch (AuthenticationException | MessagingException | UnsupportedEncodingException e) {
            ModelAndView mav = new ModelAndView("signup");
            mav.addObject("erro", e.getMessage());
            return mav;
        }
        return new ModelAndView("redirect:/login?AccountCreated");
    }

    @GetMapping("/verifyAccount")
    public ModelAndView verifyAccount(@Param("code") String code){

        if(authenticationService.verify(code))
            return new ModelAndView("redirect:/login?ActivatedAccount");
        else
            return new ModelAndView("redirect:/verifyAccount?error");
    }

    @GetMapping("/forgotPassword")
    public ModelAndView forgotPasswordPage() {
        ModelAndView mav = new ModelAndView("forgotPassword");
        mav.addObject("pageTitle", "Forgot Password");
        return mav;
    }

    @PostMapping("/forgotPasswordProcess")
    public ModelAndView forgotPasswordProcess(HttpServletRequest request){ //Class that is passed by http request
        String email = request.getParameter("email");
        String token= RandomString.make(45); //generate random token

        try {
            authenticationService.updateResetPasswordToken(token, email); //sets ResetPasswordToken Attribute in Object "User" and saves it to DB

            String resetPasswordLink = Utility.getSiteURL(request) + "/resetPassword?token=" + token; //generate reset password link

            authenticationService.sendEmail(email, resetPasswordLink); //send email to user

        } catch (CustomerNotFoundException e) {
            return new ModelAndView("redirect:/forgotPassword?error");
        } catch (MessagingException | UnsupportedEncodingException e) {
            return new ModelAndView("redirect:/forgotPassword?emailError");
        }
        return new ModelAndView("redirect:/forgotPassword?emailSent");
    }

    @GetMapping("/resetPassword")
    public ModelAndView showResetPasswordForm(@Param(value = "token") String token){
        ModelAndView mav = new ModelAndView("resetPassword");

        User user = authenticationService.get(token);
        if (user == null){
            return new ModelAndView("redirect:/resetPassword?message");
        }
        mav.addObject("token", token);
        mav.addObject("pageTitle", "Reset Password");
        return mav;
    }

    @PostMapping("/resetPassword")
    public ModelAndView processResetPassword(String token, String password){

        User user = authenticationService.get(token);
        if (user == null){
            return new ModelAndView("redirect:/resetPassword?message");
        } else {
            authenticationService.updatePassword(user, password);
            return new ModelAndView("redirect:/login?resetSucessful");
        }
    }
}
