package com.centrohospitalar.grupog.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Patient extends User {

    /*private Long healthNumber;*/
    //não foi colocado o healthNumber não faço ideia porquê, foi usado só o NIF, se houver tempo alterar para o que já tinha desenhado...
    //Também não foram usadas as Listas de Consultas
    private String healthRecord;

    public Patient() {
    }


    public Patient(long id, String username, String name, String email, String password, LocalDate birthdate, String address, Long phoneNumber, String NIF, Role role, boolean activeAccount, String profileImageUrl, String resetPasswordToken, String verificationCode, String healthRecord) {
        super(id, username, name, email, password, birthdate, address, phoneNumber, NIF, role, activeAccount, profileImageUrl, resetPasswordToken, verificationCode);
        this.healthRecord = healthRecord;
    }

    public Patient(String username, String name, String email, String password, LocalDate birthdate, String address, Long phoneNumber, String NIF, Role role, boolean activeAccount, String profileImageUrl, String resetPasswordToken, String verificationCode, String healthRecord) {
        super(username, name, email, password, birthdate, address, phoneNumber, NIF, role, activeAccount, profileImageUrl, resetPasswordToken, verificationCode);
        this.healthRecord = healthRecord;
    }

    public String getHealthRecord() {
        return healthRecord;
    }

    public void setHealthRecord(String healthRecord) {
        this.healthRecord = healthRecord;
    }

    @Override
    public String getUserSimpleTypeToString() {
        return "Utente";
    }


}
