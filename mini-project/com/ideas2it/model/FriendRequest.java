package com.ideas2it.model;

public class FriendRequest {
    private String id;
    private String userId;
    private String requestedUserId;     
    private String status;

    public FriendRequest() {}
    
    public FriendRequest(String userId, String requestedUserId) {
        this.userId = userId;
        this.requestedUserId = requestedUserId;
    } 
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public void setRequestedUserId(String requestedUserId) {
        this.requestedUserId = requestedUserId;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getId() {
        return id;   
    } 
 
    public String getUserId() {
        return userId;
    }
    
    public String getRequestedUserId() {
        return requestedUserId;
    }
    
    public String getStatus() { 
        return status;
    }
}