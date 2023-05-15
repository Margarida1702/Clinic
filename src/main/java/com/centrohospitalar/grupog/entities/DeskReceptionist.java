package com.centrohospitalar.grupog.entities;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

@Entity
public class DeskReceptionist extends Employee{

    public DeskReceptionist() {
    }

    public DeskReceptionist(long id, String username, String name, String email, String password, LocalDate birthdate, String address, Long phoneNumber, String NIF, Role role, boolean activeAccount, String profileImageUrl, String resetPasswordToken, String verificationCode, List<DateScalePeriod> dateScalePeriodList) {
        super(id, username, name, email, password, birthdate, address, phoneNumber, NIF, role, activeAccount, profileImageUrl, resetPasswordToken, verificationCode, dateScalePeriodList);
    }

    public DeskReceptionist(String username, String name, String email, String password, LocalDate birthdate, String address, Long phoneNumber, String NIF, Role role, boolean activeAccount, String profileImageUrl, String resetPasswordToken, String verificationCode, List<DateScalePeriod> dateScalePeriodList) {
        super(username, name, email, password, birthdate, address, phoneNumber, NIF, role, activeAccount, profileImageUrl, resetPasswordToken, verificationCode, dateScalePeriodList);
    }

    @Override
    public String getUserSimpleTypeToString() {
        return "Recepcionista";
    }


}
