package com.ideas2it.model;

import java.time.LocalDate;

/**
 * Contain the attributes of the Notification
 * Constructor is used to initialize the attributes 
 * and getter and setter method  is used to update and retrive the attributes
 * tostring method is used to return the values
 *
 * @version 1.0 06-OCT-2022
 * @author Venkatesh TM
 */
public class Notification {
    private String    userName;
    private LocalDate requestGivenAt;
    
    public Notification(String userName, LocalDate requestGivenAt) {
        this.userName = userName;
        this.requestGivenAt = requestGivenAt;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public void setLocalDate(LocalDate requestGivenAt) {
        this.requestGivenAt = requestGivenAt;
    }
    
    public String toString() {
        StringBuilder notification = new StringBuilder();
        notification.append("\nFriend Request given By ").append(userName)
                    .append("\nAt ").append(requestGivenAt);
        return notification.toString();
    }
 
}