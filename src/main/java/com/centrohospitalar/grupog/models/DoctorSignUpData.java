package com.centrohospitalar.grupog.models;

import com.centrohospitalar.grupog.entities.DateScalePeriod;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public class DoctorSignUpData {

    private String username;
    private String name;
    private String email;
    private String password;
    private String NIF;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    private String address;
    private Long phoneNumber;
    private Long professionalCertificateNumber;
    private Long[] specialtiesIds;
    private List<DateScalePeriod> dateScalePeriodList;

    public DoctorSignUpData(){}

    public DoctorSignUpData(String username, String name, String email, String password, String NIF, LocalDate birthdate, String address,
                            Long phoneNumber, long professionalCertificateNumber, Long[] specialtiesIds, List<DateScalePeriod> dateScalePeriodList) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.NIF = NIF;
        this.birthdate = birthdate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.professionalCertificateNumber = professionalCertificateNumber;
        this.specialtiesIds = specialtiesIds;
        this.dateScalePeriodList = dateScalePeriodList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getProfessionalCertificateNumber() {
        return professionalCertificateNumber;
    }

    public void setProfessionalCertificateNumber(Long professionalCertificateNumber) {
        this.professionalCertificateNumber = professionalCertificateNumber;
    }

    public Long[] getSpecialtiesIds() {
        return specialtiesIds;
    }

    public void setSpecialtiesIds(Long[] specialtiesIds) {
        this.specialtiesIds = specialtiesIds;
    }

    public List<DateScalePeriod> getVacancyList() {
        return dateScalePeriodList;
    }

    public void setVacancyList(List<DateScalePeriod> dateScalePeriodList) {
        this.dateScalePeriodList = dateScalePeriodList;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public List<DateScalePeriod> getDateScalePeriodList() {
        return dateScalePeriodList;
    }

    public void setDateScalePeriodList(List<DateScalePeriod> dateScalePeriodList) {
        this.dateScalePeriodList = dateScalePeriodList;
    }
}
