package com.centrohospitalar.grupog.services;

import com.centrohospitalar.grupog.entities.GeneralInformation;
import com.centrohospitalar.grupog.exceptions.GeneralInformationException;

import java.util.List;

public interface GeneralInformationService {

    List<GeneralInformation> findAll();
    GeneralInformation createGeneralInformation(String name, String description, String image) throws GeneralInformationException;
}
