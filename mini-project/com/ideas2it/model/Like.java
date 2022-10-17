package com.ideas2it.model;

public class Like {
    private String id;
    private String postId;
    private String likedUserId;
    
     
    public Like(String likedUserId) {
        this.likedUserId = likedUserId;
    }

    public String getLikedUserName() {
        return likedUserId;
    }
}