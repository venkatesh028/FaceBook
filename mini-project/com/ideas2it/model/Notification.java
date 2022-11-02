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
    private String    id;
    private String    userId;
    private LocalDate requestGivenAt;
    
    public Notification(String userId, LocalDate requestGivenAt) {
        this.userId = userId;
        this.requestGivenAt = requestGivenAt;
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public void setLocalDate(LocalDate requestGivenAt) {
        this.requestGivenAt = requestGivenAt;
    }
    
   /* public String toString() {
        StringBuilder notification = new StringBuilder();
        notification.append("\nFriend Request given By ").append(userName)
                    .append("\nAt ").append(requestGivenAt);
        return notification.toString();
    } */ 
}