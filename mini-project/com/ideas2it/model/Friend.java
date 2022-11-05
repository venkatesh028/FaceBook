package com.ideas2it.model;

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