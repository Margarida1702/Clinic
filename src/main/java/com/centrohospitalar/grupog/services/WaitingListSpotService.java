package com.centrohospitalar.grupog.services;

import com.centrohospitalar.grupog.entities.Doctor;
import com.centrohospitalar.grupog.entities.Patient;
import com.centrohospitalar.grupog.entities.Specialty;
import com.centrohospitalar.grupog.entities.WaitingListSpot;

public interface WaitingListSpotService {

    WaitingListSpot enterWaitingList(Patient patient, Doctor doctor, Specialty specialty);
}
