package com.centrohospitalar.grupog.entities;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class UnitManager extends User{

    public UnitManager() {
    }

    public UnitManager(String username, String name, String email, String password, LocalDate birthdate, String address, Long phoneNumber, String NIF, Role role, boolean activeAccount, String profileImageUrl, String resetPasswordToken, String verificationCode) {
        super(username, name, email, password, birthdate, address, phoneNumber, NIF, role, activeAccount, profileImageUrl, resetPasswordToken, verificationCode);
    }

    @Override
    public String getUserSimpleTypeToString() {
        return this.getClass().getSimpleName();
    }
}
