package com.dele.my.project.crudandroid.operations.pojo;

public class Customers {
    private Long id;
    private String fullName, emailAddress, gender, dateCreated, phoneNumber, referral, uuid;

    public Customers() {
    }

    public Customers(String fullName, String emailAddress, String gender, String dateCreated, String phoneNumber, String referral, String uuid) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.gender = gender;
        this.dateCreated = dateCreated;
        this.phoneNumber = phoneNumber;
        this.referral = referral;
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReferral() {
        return referral;
    }

    public void setReferral(String referral) {
        this.referral = referral;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
