package com.centrohospitalar.grupog.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue
    private long id;
    private String username; //adicionei temos de verificar todos os sitios onde é usado
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    private String address;
    private Long phoneNumber;
    private String NIF;
    @ManyToOne
    private Role role;
    private boolean activeAccount;
    private String profileImageUrl;
    private String resetPasswordToken;
    private String verificationCode; //64 varchar

    public User() {
    }

    public User(long id, String username, String name, String email, String password, LocalDate birthdate, String address, Long phoneNumber, String NIF, Role role, boolean activeAccount, String profileImageUrl, String resetPasswordToken, String verificationCode) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.NIF = NIF;
        this.role = role;
        this.activeAccount = activeAccount;
        this.profileImageUrl = profileImageUrl;
        this.resetPasswordToken = resetPasswordToken;
        this.verificationCode = verificationCode;
    }

    public User(String username, String name, String email, String password, LocalDate birthdate, String address, Long phoneNumber, String NIF, Role role, boolean activeAccount, String profileImageUrl, String resetPasswordToken, String verificationCode) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.NIF = NIF;
        this.role = role;
        this.activeAccount = activeAccount;
        this.profileImageUrl = profileImageUrl;
        this.resetPasswordToken = resetPasswordToken;
        this.verificationCode = verificationCode;
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

    public void setName(String fullName) {
        this.name = fullName;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public boolean isActiveAccount() {
        return activeAccount;
    }

    public void setActiveAccount(boolean activeAccount) {
        this.activeAccount = activeAccount;
    }

    public abstract String getUserSimpleTypeToString();

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImage) {
        this.profileImageUrl = profileImage;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }
}
