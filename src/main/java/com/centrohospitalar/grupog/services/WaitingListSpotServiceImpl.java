package com.centrohospitalar.grupog.services;

import com.centrohospitalar.grupog.entities.Doctor;
import com.centrohospitalar.grupog.entities.Patient;
import com.centrohospitalar.grupog.entities.Specialty;
import com.centrohospitalar.grupog.entities.WaitingListSpot;
import com.centrohospitalar.grupog.repositories.WaitingListSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WaitingListSpotServiceImpl implements WaitingListSpotService {

    @Autowired
    WaitingListSpotRepository waitingListSpotRepository;

    @Override
    public WaitingListSpot enterWaitingList(Patient patient, Doctor doctor, Specialty specialty) {

        WaitingListSpot waitingListSpot = new WaitingListSpot();
        waitingListSpot.setPatient(patient);
        waitingListSpot.setDoctor(doctor);
        waitingListSpot.setSpecialty(specialty);
        waitingListSpot.setRegisterTime(LocalDateTime.now());
        waitingListSpot.setActive(true);

        waitingListSpotRepository.save(waitingListSpot);

        return waitingListSpot;
    }
}
