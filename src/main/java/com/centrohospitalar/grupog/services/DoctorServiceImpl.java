package com.centrohospitalar.grupog.services;

import com.centrohospitalar.grupog.entities.Doctor;
import com.centrohospitalar.grupog.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    public List<Doctor> getDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors;
    }

    public List<Doctor> getDoctorsByActiveAccountTrue(){
        List<Doctor> doctors = doctorRepository.getDoctorsByActiveAccountTrue();
        return doctors;
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.getById(id);
    }


}
