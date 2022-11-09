package com.ideas2it.model;

/**
 * Contain the attributes of the Friend
 * Constructor is used to initialize the attributes 
 * and getter and setter method  is used to update and retrive the attributes
 * tostring method is used to return the values
 *
 * @version 1.0 02-NOV-2022
 * @author Venkatesh TM
 */
public class Friend {
    private String id;
    private String userId;
    private String friendId;
    
    
    public Friend (String userId, String friendId) {
        this.userId = userId;
        this.friendId = friendId;
    } 
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setUserId(String userId) { 
        this.userId = userId;
    }
    
    public void setFriendId(String friendId) { 
        this.friendId = friendId;
    }
    
    public String getId() {
        return id;
    }
    
    public String getUserId() { 
        return userId;
    }
    
    public String getFriendId() {     
        return friendId;
    }
}