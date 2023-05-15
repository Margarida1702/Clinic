package com.centrohospitalar.grupog.services;

import com.centrohospitalar.grupog.entities.Doctor;

import java.util.List;


public interface DoctorService {
    public List<Doctor> getDoctors();
    List<Doctor> getDoctorsByActiveAccountTrue();

    public Doctor getDoctorById(Long id);

}
