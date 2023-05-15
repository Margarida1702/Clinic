package com.centrohospitalar.grupog.services;

import com.centrohospitalar.grupog.entities.Doctor;
import com.centrohospitalar.grupog.entities.Employee;
import com.centrohospitalar.grupog.entities.Specialty;
import com.centrohospitalar.grupog.exceptions.SpecialtyException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface SpecialtyService {

    List<Specialty> getSpecialties();
    Specialty createSpecialty(String name, String description, String imageName) throws SpecialtyException;
    boolean existsByName(String name);

    Specialty updateSpecialty(Long id, String name, String description, String imageName) throws SpecialtyException;

    Specialty getSpecialtyById(Long id);
    Map<Specialty,Integer> SpecialtiesDoctorCount();
    boolean addSpecialtyToDoctor(Specialty specialty,Doctor doctor);
    void initSpecialties();

    Specialty getSpecialtyByName(String name);

    //List<Specialty> findByNameIn(Collection<String> specialtyList);
}
