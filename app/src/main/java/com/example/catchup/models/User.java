package com.example.catchup.models;

public class User {

    String userId;
    String userName;
    String name;
    String surName;
    String email;
    Boolean isCustomer;

    public User() {
    }

    public User(String userId, String userName, String name, String surName, String email, Boolean isCustomer) {
        this.userId = userId;
        this.userName = userName;
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.isCustomer = isCustomer;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getCustomer() {
        return isCustomer;
    }

    public void setCustomer(Boolean customer) {
        isCustomer = customer;
    }
}
