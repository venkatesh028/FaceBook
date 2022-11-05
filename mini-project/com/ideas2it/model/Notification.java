package com.ideas2it.model;

import java.sql.Timestamp;

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
    private String id;
    private String userId;
    private String requestId;
    private String requestedUserName;
    private Timestamp requestGivenAt;
      
    public Notification() {}
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    
    public void setRequestedUserName(String requestedUserName) {
        this.requestedUserName = requestedUserName;
    }
    
    public void setRequestGivenAt(Timestamp requestGivenAt) {
        this.requestGivenAt = requestGivenAt;
    }

    public String getId() {
        return id;
    }
 
    public String getUserId() {
        return userId;
    }
    
    public String getRequestId() { 
        return requestId;
    }
    
    public String getRequestedUserName() {
        return requestedUserName;
    }
    
    public Timestamp getRequestGivenAt() {
       return requestGivenAt;
    }
    
    public String toString() {
        StringBuilder notification = new StringBuilder();
        notification.append("\nFriend Request given By ").append(requestedUserName)
                    .append("\nAt ").append(requestGivenAt);
        return notification.toString();
    }
}