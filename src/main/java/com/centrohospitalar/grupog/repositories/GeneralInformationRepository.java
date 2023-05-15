package com.centrohospitalar.grupog.repositories;

import com.centrohospitalar.grupog.entities.GeneralInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneralInformationRepository extends JpaRepository<GeneralInformation, Long>{

    List<GeneralInformation> findAll();
}

