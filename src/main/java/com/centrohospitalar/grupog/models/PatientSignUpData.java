package com.centrohospitalar.grupog.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class PatientSignUpData {

    private long id;
    private String username; //adicionei temos de verificar todos os sitios onde é usado
    private String name;
    private String email;
    private String password;
    private String NIF;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    private String address;
    private Long phoneNumber;

    public PatientSignUpData() {

    }
    public PatientSignUpData(String username, String name, String email, String password, String NIF, LocalDate birthdate, String address, Long phoneNumber) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.NIF = NIF;
        this.birthdate = birthdate;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }
}
