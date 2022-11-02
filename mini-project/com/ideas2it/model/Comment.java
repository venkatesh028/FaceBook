package com.ideas2it.model;

public class Comment {
    private String id;
    private String postId;
    private String commentedUserId;
    private String commentedUserName;
    private String content;
    
    public Comment() {}
 
    public Comment(String id, String commentedUserId, String content) {
        this.id = id;
        this.commentedUserId = commentedUserId;
        this.content = content;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setCommentedUserId(String commentedUserId) {
        this.commentedUserId = commentedUserId;
    }
    
    public void setCommentedUserName(String commentedUserName) {
        this.commentedUserName = commentedUserName;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getId() {
        return id;
    }
    
    public String getPostId() { 
        return postId;
    }

    public String getCommentedUserId() {
        return commentedUserId;
    }   

    public String getCommentedUserName() {
        return commentedUserName;
    }

    public String getContent() {
        return content;
    }    
}