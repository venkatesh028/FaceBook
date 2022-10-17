package com.ideas2it.model;

import java.time.LocalDate;

import com.ideas2it.model.Profile;

/**
 * Contain the attributes of the User
 * Constructor is used to initialize the attributes 
 * and getter and setter method  is used to update and retrive the attributes
 * tostring method is used to return the values
 *
 * @version 1.0 22-SEP-2022
 * @author Venkatesh TM
 */
public class User {
    private String userId;
    private String email;
    private String password;
    private String gender;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private int    age;
    private Profile profile;
    
    public User() {}

    public User(String userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
   
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setProfile(Profile profile) {
        this.profile = profile;
    } 
    
    public void setGender(String gender) { 
        this.gender = gender;
    }
   
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public Profile getProfile() {
        return profile;
    }
   
    public String getGender() {
        return gender;
    }
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
     
    public String getPhoneNumber() { 
        return phoneNumber;
    }
    
    public String toString() {
        StringBuilder userMessage = new StringBuilder();
        userMessage.append("\nEmail   : ").append(email)
                   .append("\nGender  : ").append(gender)
                   .append("\nDOB     : ").append(dateOfBirth)
                   .append("\nAge     : ").append(age)
                   .append("\nPhNo    : ").append(phoneNumber);

        return userMessage.toString();
     }
}