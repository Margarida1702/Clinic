package com.centrohospitalar.grupog.services;

import com.centrohospitalar.grupog.entities.GeneralInformation;
import com.centrohospitalar.grupog.entities.Specialty;
import com.centrohospitalar.grupog.exceptions.GeneralInformationException;
import com.centrohospitalar.grupog.exceptions.SpecialtyException;
import com.centrohospitalar.grupog.repositories.GeneralInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GeneralInformationServiceImpl implements GeneralInformationService{

    @Autowired
    GeneralInformationRepository generalInformationRepository;

    @Override
    public List<GeneralInformation> findAll() {
        return generalInformationRepository.findAll();
    }

    @Override
    public GeneralInformation createGeneralInformation(String name, String description, String image) throws GeneralInformationException {

        GeneralInformation generalInformation = new GeneralInformation();

        if(description.length()>1500)
            throw new GeneralInformationException("Atingido limite de 1500 caracteres na descrição :(");
        if (name=="")
            throw new GeneralInformationException("Título não preenchido");

        generalInformation.setTitle(name);
        generalInformation.setBody(description);
        generalInformation.setImageName(image);
        generalInformation.setRegisterDate(LocalDateTime.now());

        generalInformationRepository.save(generalInformation);

        return generalInformation;
    }
}
