package com.ideas2it.model;

import java.time.LocalDate;
import java.util.List;

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
    private String id;
    private String email;
    private String password;
    private LocalDate dateOfBirth;
    private int    age;
    private long phoneNumber;
    private Profile profile;
    private List<Post> listOfPosts;
    private List<User> friends;
    
    public User() {}

    public User(String email, String password, LocalDate dateOfBirth, int age) {
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
    }
    
    public void setId(String id) {
        this.id = id;
    }
   
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
   
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    
    public void setListOfPosts(List<Post> listOfPosts) {
        this.listOfPosts = listOfPosts;
    }
   
    public void setFriends(List<User> friends) { 
        this.friends = friends;
    }

    public String getId() {
        return id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    public int getAge() {
        return age;
    }
     
    public long getPhoneNumber() { 
        return phoneNumber;
    }

    public Profile getProfile() {
        return profile;
    }
    
    public List<Post> getListOfPosts() {
        return listOfPosts;
    }
    
    public List<User> getfriends() {
        return friends;
    }

    public String toString() {
        StringBuilder userMessage = new StringBuilder();
        userMessage.append("\nEmail   : ").append(email)
                   .append("\nDOB     : ").append(dateOfBirth)
                   .append("\nAge     : ").append(age)
                   .append("\nPhNo    : ").append(phoneNumber);

        return userMessage.toString();
     }
}