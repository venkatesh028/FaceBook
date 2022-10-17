package com.ideas2it.model;

public class Comment {
    private String commentId;
    private String commentedBy;
    private String comment;
    
    public Comment(String commentId, String commentedBy, String comment) {
        this.commentId = commentId;
        this.commentedBy = commentedBy;
        this.comment = comment;
    }
    
    public void setCommentedBy(String commentedBy) {
        this.commentedBy = commentedBy;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
    
    public String getCommentedBy() {
        return commentedBy;
    }       
}