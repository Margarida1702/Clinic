package com.centrohospitalar.grupog.repositories;

import com.centrohospitalar.grupog.entities.Patient;
import com.centrohospitalar.grupog.entities.WaitingListSpot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitingListSpotRepository extends JpaRepository<WaitingListSpot, Long> {

    WaitingListSpot getById (Long id);
    WaitingListSpot getByPatient(Patient patient);
}
