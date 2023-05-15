package com.centrohospitalar.grupog.services;

import com.centrohospitalar.grupog.entities.*;
import com.centrohospitalar.grupog.exceptions.AuthenticationException;
import com.centrohospitalar.grupog.exceptions.CustomerNotFoundException;
import com.centrohospitalar.grupog.models.DoctorSignUpData;
import com.centrohospitalar.grupog.models.PatientSignUpData;
import com.centrohospitalar.grupog.repositories.RoleRepository;
import com.centrohospitalar.grupog.repositories.SpecialtyRepository;
import com.centrohospitalar.grupog.repositories.UserRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    SpecialtyRepository specialtyRepository;

    @Override
    public User validateLogin(String email, String password){
        User user = userRepository.getByEmail(email);
        if(user != null && passwordEncoder.matches(password, user.getPassword()) && user.isActiveAccount()) {
            return user;
        }
        return null;
    }

    @Override
    public Patient createPatientAccount(PatientSignUpData patientSignUpData) throws AuthenticationException {
        Patient patient = new Patient();

        validateFields(patientSignUpData);

        patient.setUsername(patientSignUpData.getUsername());
        patient.setName(patientSignUpData.getName());
        patient.setEmail(patientSignUpData.getEmail());
        patient.setNIF(patientSignUpData.getNIF());
        String encodedPassword = passwordEncoder.encode(patientSignUpData.getPassword());
        patient.setPassword(encodedPassword);
        patient.setBirthdate(patientSignUpData.getBirthdate());
        patient.setAddress(patientSignUpData.getAddress());
        patient.setPhoneNumber(patientSignUpData.getPhoneNumber());

        //verificacao de conta
        patient.setActiveAccount(false);
        patient.setVerificationCode(RandomString.make(64));

        Role userRole = roleRepository.getByName("ROLE_PATIENT");
        patient.setRole(userRole);
        userRepository.save(patient);

        return patient;
    }

    public void validateFields(PatientSignUpData patientSignUpData) throws AuthenticationException {

        if(patientSignUpData.getName()==null || patientSignUpData.getName().equals(""))
            throw new AuthenticationException("Nome não preenchido");
        if(patientSignUpData.getUsername()==null || patientSignUpData.getUsername().equals(""))
            throw new AuthenticationException("Username não preenchido");
        if(patientSignUpData.getEmail()==null || patientSignUpData.getEmail().equals(""))
            throw new AuthenticationException("Email não preenchido");
        if(patientSignUpData.getPassword()==null || patientSignUpData.getPassword().equals(""))
            throw new AuthenticationException("Password não preenchida");
        if(patientSignUpData.getNIF()==null || patientSignUpData.getNIF().equals(""))
            throw new AuthenticationException("NIF não preenchido");
        if(patientSignUpData.getBirthdate()==null)
            throw new AuthenticationException("Data de Nascimento não preenchida");
        if(userRepository.getByEmail(patientSignUpData.getEmail()) != null)
            throw new AuthenticationException("Email já tem uma conta associada");
    }

    public void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("centro.hospitalar.upskill.grupog@gmail.com","Centro Hospitalar Upskill");
        helper.setTo(user.getEmail());

        String subject = "Ative a sua conta!";
        String verificationLink = siteURL + "/verifyAccount?code=" + user.getVerificationCode();

        String content = "<p>Olá " + user.getUsername() + "!</p>"
                + "<p>Fez um registo no Centro Hospitalar Upskill,</p>"
                + "<p>Clique no link para ativar a conta:</p>"
                + "<p><b><a href=\"" + verificationLink + "\">Ativar conta</a></b></p>"
                + "<p>Obrigada,</p>"
                + "<p>Centro Hospitalar Upskill</p>"
                + "<p>Ignore este email se não fez o pedido</p>";

        helper.setSubject(subject);
        helper.setText(content, true);

        try {
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println("Erro a enviar email.");
            System.out.println("Email:");
            System.out.println(content);
        }
    }

    public boolean verify(String verificationCode){
        User user = userRepository.findByVerificationCode(verificationCode);

        if (user == null) //&& user.isActiveAccount()
            return false;
        else {
            user.setActiveAccount(true);
            userRepository.save(user);
            return true;
        }
    }

    @Override
    public Doctor createDoctorAccount(DoctorSignUpData doctorSignUpData) throws AuthenticationException, MessagingException, UnsupportedEncodingException {
        Doctor doctor = new Doctor();

        if(doctorSignUpData.getName()==null || doctorSignUpData.getName().equals(""))
            throw new AuthenticationException("Nome não preenchido");
        if(doctorSignUpData.getUsername()==null || doctorSignUpData.getUsername().equals(""))
            throw new AuthenticationException("Username não preenchido");
        if(doctorSignUpData.getEmail()==null || doctorSignUpData.getEmail().equals(""))
            throw new AuthenticationException("Email não preenchido");
        if(doctorSignUpData.getPassword()==null || doctorSignUpData.getPassword().equals(""))
            throw new AuthenticationException("Password não preenchida");
        if(doctorSignUpData.getNIF()==null || doctorSignUpData.getNIF().equals(""))
            throw new AuthenticationException("NIF não preenchido");
        if(doctorSignUpData.getBirthdate()==null)
            throw new AuthenticationException("Data de Nascimento não preenchida");
        if(doctorSignUpData.getProfessionalCertificateNumber()==null || doctorSignUpData.getProfessionalCertificateNumber().equals(""))
            throw new AuthenticationException("Cédula profissional não preenchida");
        if(userRepository.getByEmail(doctorSignUpData.getEmail()) != null)
            throw new AuthenticationException("Email já tem uma conta associada");

        /* Obter Specialties */
        List<Specialty> specialties = new ArrayList<>();

        for(Long spId: doctorSignUpData.getSpecialtiesIds()){
            Specialty sp = specialtyRepository.getReferenceById(spId);
            specialties.add(sp);
        }

        doctor.setUsername(doctorSignUpData.getUsername());
        doctor.setName(doctorSignUpData.getName());
        doctor.setEmail(doctorSignUpData.getEmail());
        String encodedPassword = passwordEncoder.encode(doctorSignUpData.getPassword());
        doctor.setPassword(encodedPassword);
        doctor.setNIF(doctorSignUpData.getNIF());
        doctor.setBirthdate(doctorSignUpData.getBirthdate());
        doctor.setAddress(doctorSignUpData.getAddress());
        doctor.setPhoneNumber(doctorSignUpData.getPhoneNumber());
        doctor.setProfessionalCertificateNumber(doctorSignUpData.getProfessionalCertificateNumber());
        doctor.setSpecialtyList(specialties);

        //verificacao de conta
        doctor.setActiveAccount(false);
        doctor.setVerificationCode(RandomString.make(64));

        Role userRole = roleRepository.getByName("ROLE_DOCTOR");
        doctor.setRole(userRole);
        userRepository.save(doctor);

        return doctor;
    }


    //teste
    public User getAutheticatedUser(){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.getByEmail(email);
        return user;
    }

    public void updateResetPasswordToken (String token, String email) throws CustomerNotFoundException {
        User user = userRepository.getByEmail(email);

        if (user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else {
            throw new CustomerNotFoundException("Ooops!.. Não encontramos nenhuma conta com o email " + email);
        }
    }

    public User get(String resetPasswordToken){
        return userRepository.findByResetPasswordToken(resetPasswordToken);
    }

    public void updatePassword (User user, String newPassword){
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }
    public void sendEmail(String email, String resetPasswordLink) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("centro.hospitalar.upskill.grupog@gmail.com","Centro Hospitalar Upskill");
        helper.setTo(email);

        String subject = "Aqui está o link para repor a sua password!";

        String content = "<p>Olá!</p>"
                + "<p>Pediu um link para repor a password.</p>"
                + "<p>Clique no link abaixo para a repor:</p>"
                + "<p><b><a href=\"" + resetPasswordLink + "\">Mudar a Password</a></b></p>"
                + "<p>Ignore este email se não fez o pedido</p>";

        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }
}
