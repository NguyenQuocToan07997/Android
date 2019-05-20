package com.Model;

public class UserModel {
    private String userId;
    private String username;
    private String email;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserModel(String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }


    private int credits;
    private String description;
    private String subject_code;
    private String subject_name;

    public UserModel(int credits, String description, String subject_code, String subject_name) {
        this.credits = credits;
        this.description = description;
        this.subject_code = subject_code;
        this.subject_name = subject_name;
    }


    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }


    public UserModel() {
    }

}