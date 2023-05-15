package com.centrohospitalar.grupog.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public abstract class Employee extends User{

    @OneToMany
    private List<DateScalePeriod> dateScalePeriodList;
    public Employee() {

    }

    public Employee(long id, String username, String name, String email, String password, LocalDate birthdate, String address, Long phoneNumber, String NIF, Role role, boolean activeAccount, String profileImageUrl, String resetPasswordToken, String verificationCode, List<DateScalePeriod> dateScalePeriodList) {
        super(id, username, name, email, password, birthdate, address, phoneNumber, NIF, role, activeAccount, profileImageUrl, resetPasswordToken, verificationCode);
        this.dateScalePeriodList = dateScalePeriodList;
    }

    public Employee(String username, String name, String email, String password, LocalDate birthdate, String address, Long phoneNumber, String NIF, Role role, boolean activeAccount, String profileImageUrl, String resetPasswordToken, String verificationCode, List<DateScalePeriod> dateScalePeriodList) {
        super(username, name, email, password, birthdate, address, phoneNumber, NIF, role, activeAccount, profileImageUrl, resetPasswordToken, verificationCode);
        this.dateScalePeriodList = dateScalePeriodList;
    }

    public List<DateScalePeriod> getDateScalePeriodList() {
        return dateScalePeriodList;
    }

    public void setDateScalePeriodList(List<DateScalePeriod> dateScalePeriodList) {
        this.dateScalePeriodList = dateScalePeriodList;
    }
}
