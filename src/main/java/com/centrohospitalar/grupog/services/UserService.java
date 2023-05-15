package com.centrohospitalar.grupog.services;

import com.centrohospitalar.grupog.entities.*;
import com.centrohospitalar.grupog.exceptions.AuthenticationException;
import com.centrohospitalar.grupog.models.AdminAllSignup;
import com.centrohospitalar.grupog.models.PatientSignUpData;

import java.util.List;

public interface UserService {

    List<Doctor> getDoctors();
    List<Doctor> getDoctorsBySpecialty(Specialty specialty);

    void updateUser(PatientSignUpData patientSignUpData, String email) throws AuthenticationException;
    User getUserById(long id);
    Admin createAdminAccount(AdminAllSignup adminAllSignup) throws AuthenticationException;
    List<User> findAll();
    void disableUser(Long id);
    List<Patient> getPatientByActiveAccountTrue();
    DeskReceptionist createDeskReceptionistAccount(PatientSignUpData patientSignUpData) throws AuthenticationException;
    Admin getAdminById(Long id);
    Patient getPatientById(Long id);
    UnitManager getUnitManagerById(Long id);
    Doctor getDoctorById(Long id);
    DeskReceptionist getDeskReceptionistById(Long id);


    void initUsers();
}
