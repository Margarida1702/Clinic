package com.centrohospitalar.grupog.entities;



import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.List;


@Entity
public class Doctor extends Employee {

    private long professionalCertificateNumber;
    @ManyToMany
    private List<Specialty> specialtyList;


    public Doctor(){
    }

    public Doctor(long id, String username, String name, String email, String password, LocalDate birthdate, String address, Long phoneNumber, String NIF, Role role, boolean activeAccount, String profileImageUrl, String resetPasswordToken, String verificationCode, List<DateScalePeriod> dateScalePeriodList, long professionalCertificateNumber, List<Specialty> specialtyList) {
        super(id, username, name, email, password, birthdate, address, phoneNumber, NIF, role, activeAccount, profileImageUrl, resetPasswordToken, verificationCode, dateScalePeriodList);
        this.professionalCertificateNumber = professionalCertificateNumber;
        this.specialtyList = specialtyList;
    }

    public Doctor(String username, String name, String email, String password, LocalDate birthdate, String address, Long phoneNumber, String NIF, Role role, boolean activeAccount, String profileImageUrl, String resetPasswordToken, String verificationCode, List<DateScalePeriod> dateScalePeriodList, long professionalCertificateNumber, List<Specialty> specialtyList) {
        super(username, name, email, password, birthdate, address, phoneNumber, NIF, role, activeAccount, profileImageUrl, resetPasswordToken, verificationCode, dateScalePeriodList);
        this.professionalCertificateNumber = professionalCertificateNumber;
        this.specialtyList = specialtyList;
    }

    @Override
    public String getUserSimpleTypeToString() {
        return "Médico";
    }

    public Long getProfessionalCertificateNumber() {
        return professionalCertificateNumber;
    }

    public void setProfessionalCertificateNumber(Long professionalCertificateNumber) {
        this.professionalCertificateNumber = professionalCertificateNumber;
    }

    public void setProfessionalCertificateNumber(long professionalCertificateNumber) {
        this.professionalCertificateNumber = professionalCertificateNumber;
    }

    public List<Specialty> getSpecialtyList() {
        return specialtyList;
    }

    public void setSpecialtyList(List<Specialty> specialtyList) {
        this.specialtyList = specialtyList;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
