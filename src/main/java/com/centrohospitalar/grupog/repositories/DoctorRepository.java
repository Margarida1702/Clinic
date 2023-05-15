package com.centrohospitalar.grupog.repositories;

import com.centrohospitalar.grupog.entities.Doctor;
import com.centrohospitalar.grupog.entities.Role;
import com.centrohospitalar.grupog.entities.Specialty;
import com.centrohospitalar.grupog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Doctor getByEmail(String email);
    Doctor getById(Long id);
    List<Doctor> getDoctorsBySpecialtyListContaining(Specialty specialty);
    Integer countDoctorsBySpecialtyListContaining(Specialty specialty);
    List<Doctor> getDoctorsByActiveAccountTrue();


    Doctor getDoctorById(Long id);
}