package com.centrohospitalar.grupog.repositories;

import com.centrohospitalar.grupog.entities.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    Specialty getSpecialtyById(Long id);

    Specialty getSpecialtyByName(String name);

    //List<Specialty> findByNameExistsIn(Collection<String> specialtyList);
}
