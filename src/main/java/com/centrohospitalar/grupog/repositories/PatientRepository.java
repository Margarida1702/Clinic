package com.centrohospitalar.grupog.repositories;

import com.centrohospitalar.grupog.entities.Doctor;
import com.centrohospitalar.grupog.entities.Patient;
import com.centrohospitalar.grupog.entities.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient getByEmail(String email);
    List<Patient> getDoctorsByActiveAccountTrue();
    Patient getPatientById(Long id);
}