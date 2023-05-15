package com.centrohospitalar.grupog.services;


import com.centrohospitalar.grupog.entities.Doctor;
import com.centrohospitalar.grupog.entities.Patient;
import com.centrohospitalar.grupog.entities.User;
import com.centrohospitalar.grupog.exceptions.AuthenticationException;
import com.centrohospitalar.grupog.exceptions.CustomerNotFoundException;
import com.centrohospitalar.grupog.models.DoctorSignUpData;
import com.centrohospitalar.grupog.models.PatientSignUpData;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface AuthenticationService {
    User validateLogin(String email, String Password);
    Patient createPatientAccount(PatientSignUpData patientSignUpData) throws AuthenticationException, MessagingException, UnsupportedEncodingException;
//test!!!!!!!!!!!
    User getAutheticatedUser();
    void updateResetPasswordToken (String token, String email) throws CustomerNotFoundException;
    void updatePassword (User user, String newPassword);
    User get(String resetPasswordToken);

    void sendEmail(String email, String resetPasswordLink) throws MessagingException, UnsupportedEncodingException;
    void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException;
    boolean verify(String verificationCode);
    Doctor createDoctorAccount(DoctorSignUpData doctorSignUpData) throws AuthenticationException, MessagingException, UnsupportedEncodingException;


}
