package com.ideas2it.model;

/**
 * Contain the attributes of the Like
 * Constructor is used to initialize the attributes 
 * and getter and setter method  is used to update and retrive the attributes
 * tostring method is used to return the values
 *
 * @version 1.0 01-NOV-2022
 * @author Venkatesh TM
 */
public class Like {
    private String id;
    private String postId;
    private String likedUserId;
    private String likedUserName;
    
    public Like() {}
 
    public Like(String likedUserId, String postId) {
        this.likedUserId = likedUserId;
        this.postId = postId;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setLikdeUserId(String likedUserId) {
        this.likedUserId = likedUserId;
    }
    
    public void setLikeUserName(String likedUserName) {
        this.likedUserName = likedUserName;
    }
    
    public String getId() {
        return id;
    }
    
    public String getPostId() {
        return postId;
    }
    
    public String getLikedUserId() {
        return likedUserId;
    }
    
    public String getLikedUserName() {
        return likedUserName;
    }
}