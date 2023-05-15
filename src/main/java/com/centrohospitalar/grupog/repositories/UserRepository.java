package com.centrohospitalar.grupog.repositories;

import com.centrohospitalar.grupog.entities.Patient;
import com.centrohospitalar.grupog.entities.Role;
import com.centrohospitalar.grupog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    User getByEmail(String email);
    User getById(Long id);
    User findByResetPasswordToken(String resetPasswordToken);
    User findByVerificationCode(String verificationCode);
    List<User> getUsersByRole(Role role);
    List<Patient> getPatientByActiveAccountTrue();
    List<User> findAll();
    Patient getPatientById(Long id);

}
