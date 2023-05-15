package com.centrohospitalar.grupog.services;

import com.centrohospitalar.grupog.entities.*;
import com.centrohospitalar.grupog.exceptions.AuthenticationException;
import com.centrohospitalar.grupog.models.AdminAllSignup;
import com.centrohospitalar.grupog.models.PatientSignUpData;
import com.centrohospitalar.grupog.repositories.*;
import com.centrohospitalar.grupog.utilities.Generator;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    UnitManagerRepository unitManagerRepository;

    @Autowired
    DeskReceptionistRepository deskReceptionistRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    DoctorRepository doctorRepository;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public List<Doctor> getDoctorsBySpecialty(Specialty specialty) {
        return doctorRepository.getDoctorsBySpecialtyListContaining(specialty);
    }

    @Override
    public void updateUser(PatientSignUpData patientSignUpData, String email) throws AuthenticationException {
        Patient patient = (Patient) userRepository.getByEmail(email);

        if(patientSignUpData.getUsername()==null || patientSignUpData.getUsername()=="") {
            throw new AuthenticationException("Nome não deve estar vazio!");
        } else {
            patient.setUsername(patientSignUpData.getUsername());
        }
        if(patientSignUpData.getName()==null || patientSignUpData.getName()=="") {
            throw new AuthenticationException("Nome Completo não deve estar vazio!");
        } else {
            patient.setName(patientSignUpData.getName());
        }
        if (patientSignUpData.getBirthdate()==null) {
            throw new AuthenticationException("Data de nascimento não deve estar vazia!");
        } else {
            patient.setBirthdate(patientSignUpData.getBirthdate());
        }
        if(patientSignUpData.getNIF()==null || patientSignUpData.getNIF()=="") {
            throw new AuthenticationException("NIF não deve estar vazio!");
        } else {
            patient.setNIF(patientSignUpData.getNIF());
        }
        if (patientSignUpData.getPassword()!=null && !patientSignUpData.getPassword().equals("")){
            String encodedPassword = passwordEncoder.encode(patientSignUpData.getPassword());
            patient.setPassword(encodedPassword);
        }
        patient.setAddress(patientSignUpData.getAddress());
        patient.setPhoneNumber(patientSignUpData.getPhoneNumber());

        userRepository.save(patient);
    }

    @Override
    public Admin createAdminAccount(AdminAllSignup adminAllSignup) throws AuthenticationException {
        Admin admin = new Admin();

        if(adminAllSignup.getName()==null || adminAllSignup.getName().equals(""))
            throw new AuthenticationException("Nome não preenchido");
        if(adminAllSignup.getUsername()==null || adminAllSignup.getUsername().equals(""))
            throw new AuthenticationException("Username não preenchido");
        if(adminAllSignup.getEmail()==null || adminAllSignup.getEmail().equals(""))
            throw new AuthenticationException("Email não preenchido");
        if(adminAllSignup.getPassword()==null || adminAllSignup.getPassword().equals(""))
            throw new AuthenticationException("Password não preenchida");
        if(adminAllSignup.getNIF()==null || adminAllSignup.getNIF().equals(""))
            throw new AuthenticationException("NIF não preenchido");
        if(adminAllSignup.getBirthdate()==null)
            throw new AuthenticationException("Data de Nascimento não preenchida");
        if(userRepository.getByEmail(adminAllSignup.getEmail()) != null)
            throw new AuthenticationException("Email já tem uma conta associada");

        admin.setUsername(adminAllSignup.getUsername());
        admin.setName(adminAllSignup.getName());
        admin.setEmail(adminAllSignup.getEmail());
        String encodedPassword = passwordEncoder.encode(adminAllSignup.getPassword());
        admin.setPassword(encodedPassword);
        admin.setBirthdate(adminAllSignup.getBirthdate());
        admin.setAddress(adminAllSignup.getAddress());
        admin.setPhoneNumber(adminAllSignup.getPhoneNumber());
        admin.setNIF(adminAllSignup.getNIF());

        //verificacao de conta
        admin.setActiveAccount(false);
        admin.setVerificationCode(RandomString.make(64));

        Role userRole = roleRepository.getByName("ROLE_ADMIN");
        admin.setRole(userRole);
        userRepository.save(admin);

        return admin;
    }

    @Override
    public List<User> findAll() {
        List users = userRepository.findAll();

        return users;
    }

    @Override
    public void disableUser(Long id) {
        User user = userRepository.getById(id);
        user.setActiveAccount(false);
        userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public List<Patient> getPatientByActiveAccountTrue(){
        List<Patient> patientList = userRepository.getPatientByActiveAccountTrue();
        return patientList;
    }

    @Override
    public DeskReceptionist createDeskReceptionistAccount(PatientSignUpData patientSignUpData) throws AuthenticationException{
        DeskReceptionist deskReceptionist = new DeskReceptionist();

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

        deskReceptionist.setUsername(patientSignUpData.getUsername());
        deskReceptionist.setName(patientSignUpData.getName());
        deskReceptionist.setEmail(patientSignUpData.getEmail());
        String encodedPassword = passwordEncoder.encode(patientSignUpData.getPassword());
        deskReceptionist.setPassword(encodedPassword);
        deskReceptionist.setBirthdate(patientSignUpData.getBirthdate());
        deskReceptionist.setAddress(patientSignUpData.getAddress());
        deskReceptionist.setPhoneNumber(patientSignUpData.getPhoneNumber());
        deskReceptionist.setNIF(patientSignUpData.getNIF());

        //verificacao de conta
        deskReceptionist.setActiveAccount(false);
        deskReceptionist.setVerificationCode(RandomString.make(64));

        Role userRole = roleRepository.getByName("ROLE_DESKRECEPTIONIST");
        deskReceptionist.setRole(userRole);
        userRepository.save(deskReceptionist);

        return deskReceptionist;
    }


    @Override
    public Admin getAdminById(Long id){
        return adminRepository.getAdminById(id);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.getPatientById(id);
    }
    @Override
    public UnitManager getUnitManagerById(Long id){
        return unitManagerRepository.getUnitManagerById(id);
    }
    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.getDoctorById(id);
    }
    @Override
    public DeskReceptionist getDeskReceptionistById(Long id){
        return deskReceptionistRepository.getDeskReceptionistById(id);
    }


    @Override
    public void initUsers() {
        // Patient Creation
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        List<Patient> patientList = new ArrayList<>();
        List<Doctor> doctorList = new ArrayList<>();
        List<Admin> adminList = new ArrayList<>();
        List<DeskReceptionist> deskReceptionistList = new ArrayList<>();
        Random random= new Random();

        adminList.add(new Admin( "Hospital UpSkill", "Hospital UpSkill","hospitalupskill@gmail.com", passwordEncoder.encode("123456"),
                LocalDate.of(2000,5,3), "Rua A", 925451609L,"158647925", roleRepository.getByName("ROLE_ADMIN"),true, "", RandomString.make(45),
                RandomString.make(64),new ArrayList<DateScalePeriod>()));

       /* public Admin(long id, String username, String name, String email, String password, LocalDate birthdate, String address, Long phoneNumber, String NIF, Role role, boolean activeAccount, String profileImageUrl, String resetPasswordToken, String verificationCode, List<DateScalePeriod> dateScalePeriodList) {
            super(id, username, name, email, password, birthdate, address, phoneNumber, NIF, role, activeAccount, profileImageUrl, resetPasswordToken, verificationCode, dateScalePeriodList);
        }*/
        patientList.add(
                new Patient(
                        "João Silva", "João Silva","jpcss@hotmail.com", passwordEncoder.encode("123456"),
                        LocalDate.of(1980,4,29), "Rua A", 925451609L,"217743552",
                        roleRepository.getByName("ROLE_PATIENT"), true, "", RandomString.make(45),
                        RandomString.make(64), "Síndrome de Brugada"));


        /*public Patient(long id, String username, String name, String email, String password, LocalDate birthdate,
        String address, Long phoneNumber, String NIF, Role role, boolean activeAccount, String profileImageUrl, String resetPasswordToken, String verificationCode, String healthRecord)
        */

        doctorList.add(new Doctor(
                "João Silva", "João Silva", "mortiris@gmail.com", passwordEncoder.encode("123456"),
                LocalDate.of(1980,4,29),"Rua A", 925451609L,"217743552",
                roleRepository.getByName("ROLE_DOCTOR"), true, "",RandomString.make(45),
                RandomString.make(64), new ArrayList<DateScalePeriod>(), 123456789L,new ArrayList<Specialty>()));

        /*public Doctor(long id, String username, String name, String email, String password, LocalDate birthdate,
        String address, Long phoneNumber, String NIF, Role role, boolean activeAccount, String profileImageUrl,
        String resetPasswordToken, String verificationCode, List<DateScalePeriod> dateScalePeriodList, long professionalCertificateNumber,
        List<Specialty> specialtyList)
         */


        deskReceptionistList.add(new DeskReceptionist( "Margarida Fonseca","Margarida Fonseca","margarida_96@live.com.pt", passwordEncoder.encode("123456"),
                LocalDate.of(1996,3,26),"Rua A", 912345678L,"123456789", roleRepository.getByName("ROLE_DESKRECEPTIONIST"), true, "", RandomString.make(45),
                RandomString.make(64),new ArrayList<DateScalePeriod>()));

//public DeskReceptionist(long id, String username, String name, String email, String password, LocalDate birthdate, String address, Long phoneNumber, String NIF, Role role, boolean activeAccount, String profileImageUrl, String resetPasswordToken, String verificationCode, List<DateScalePeriod> dateScalePeriodList)

        for(int i=5;i<35;i++){
            Patient patientAux =new Patient();
            String strNames = Generator.generateRandomNameOfUser();
            patientAux.setUsername(strNames);
            patientAux.setName(strNames);
            patientAux.setEmail("patient" + (i-5) + "@gmail.com");
            patientAux.setPassword(passwordEncoder.encode("123456"));
            patientAux.setBirthdate(Generator.generateBirthDay());
            patientAux.setAddress(Generator.generateAddress());
            patientAux.setPhoneNumber(Generator.generatePhoneNumber());
            patientAux.setNIF(Generator.generateNIF());
            patientAux.setRole(roleRepository.getByName("ROLE_PATIENT"));
            patientAux.setActiveAccount(true);
            patientAux.setProfileImageUrl("");
            patientAux.setResetPasswordToken(RandomString.make(45));
            patientAux.setVerificationCode(RandomString.make(64));
            patientAux.setHealthRecord(Generator.generateCondition());


            patientList.add(patientAux);
        }


        for(int i=35;i<44;i++){
            Doctor doctorAux = new Doctor();
            String strNames = Generator.generateRandomNameOfUser();
            doctorAux.setUsername(strNames);
            doctorAux.setName(strNames);
            doctorAux.setEmail("doctor" + (i-35) + "@gmail.com");
            doctorAux.setPassword(passwordEncoder.encode("123456"));
            doctorAux.setBirthdate(Generator.generateBirthDay());
            doctorAux.setAddress(Generator.generateAddress());
            doctorAux.setPhoneNumber(Generator.generatePhoneNumber());
            doctorAux.setNIF(Generator.generateNIF());
            doctorAux.setRole(roleRepository.getByName("ROLE_DOCTOR"));
            doctorAux.setActiveAccount(true);
            doctorAux.setProfileImageUrl("");
            doctorAux.setResetPasswordToken(RandomString.make(45));
            doctorAux.setVerificationCode(RandomString.make(64));
            doctorAux.setDateScalePeriodList(new ArrayList<>());
            doctorAux.setProfessionalCertificateNumber(Generator.generateProfessionalCertificateNumber());
            doctorAux.setSpecialtyList(new ArrayList<Specialty>());

            doctorList.add(doctorAux);
        }

        for(int i=44;i<47;i++){
            DeskReceptionist deskReceptionistAux = new DeskReceptionist();
            String strNames = Generator.generateRandomNameOfUser();
            deskReceptionistAux.setUsername(strNames);
            deskReceptionistAux.setName(strNames);
            deskReceptionistAux.setEmail("deskReceptionist" + (i-44) + "@gmail.com");
            deskReceptionistAux.setPassword(passwordEncoder.encode("123456"));
            deskReceptionistAux.setBirthdate(Generator.generateBirthDay());
            deskReceptionistAux.setAddress(Generator.generateAddress());
            deskReceptionistAux.setPhoneNumber(Generator.generatePhoneNumber());
            deskReceptionistAux.setNIF(Generator.generateNIF());
            deskReceptionistAux.setRole(roleRepository.getByName("ROLE_DESKRECEPTIONIST"));
            deskReceptionistAux.setActiveAccount(true);
            deskReceptionistAux.setProfileImageUrl("");
            deskReceptionistAux.setResetPasswordToken(RandomString.make(45));
            deskReceptionistAux.setVerificationCode(RandomString.make(64));
            deskReceptionistAux.setDateScalePeriodList(new ArrayList<>());


            deskReceptionistList.add(deskReceptionistAux);
        }


        for(Admin admin: adminList){
            userRepository.save(admin);
        }

        for(Patient patient: patientList){
            System.out.println(patient.getId() + "-" + patient.getUsername());
            userRepository.save(patient);
        }

        for(Doctor doctor: doctorList){
            userRepository.save(doctor);
        }

        for(DeskReceptionist deskReceptionist: deskReceptionistList){
            userRepository.save(deskReceptionist);
        }

    }






}
