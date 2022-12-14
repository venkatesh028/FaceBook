package com.ideas2it.model;

/**
 * Contain the attributes of the Comment
 * Constructor is used to initialize the attributes 
 * and getter and setter method  is used to update and retrive the attributes
 * tostring method is used to return the values
 *
 * @version 1.0 01-NOV-2022
 * @author Venkatesh TM
 */
public class Comment {
    private String id;
    private String postId;
    private String commentedUserId;
    private String commentedUserName;
    private String content;
    
    public Comment() {}
 
    public Comment(String postId, String commentedUserId, String content) {
        this.postId = postId;
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
    
    public String toString() {
        StringBuilder comment = new StringBuilder();
        
        comment.append("\nComment ID : ").append(id)
               .append("\n").append(commentedUserName)
               .append(" : ").append(content);

        return comment.toString();
    }  
}