package com.ideas2it.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ideas2it.model.Like;
import com.ideas2it.model.Comment;

/**
 * Contain the attributes of the Post
 * Constructor is used to initialize the attributes 
 * and getter and setter method  is used to update and retrive the attributes
 * tostring method is used to return the values
 *
 * @version 1.0 22-SEP-2022
 * @author Venkatesh TM
 */
public class Post {
    private String id;
    private String postedUserId;
    private String postedUserName;
    private String content;
    private int likeCount;
    private int commentCount;
    
    public Post() {}

    public Post(String id, String postedUserId, String content) {
        this.id = id;
        this.postedUserId = postedUserId;
        this.content = content;  
    }
    
    public void setPostId(String id) {
        this.id = id;
    }
    
    public void setPostedUserId(String postedUserId) {
        this.postedUserId = postedUserId;    
    }  
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public void setPostedUserName(String postedUserName) {
        this.postedUserName = postedUserName;
    }
    
    public String getId() {
        return id;
    }
    
    public String getContent() {
        return content;
    }

    public int getCommentCount() {
        return commentCount;
    }
    
    public  int getLikeCount() {
        return likeCount;        
    }
    
    public String getPostedUserId() {
        return postedUserId;
    }
    
    public String getPostedUserName() {
        return postedUserName;
    }
    
    /*public String toString() {
        StringBuilder post = new StringBuilder();
        post.append("\npostedUserName : ").append(postedUserName)
            .append("\ncontents   : ").append(content)
            .append("\nLikes    : ").append(likeCount)
            .append("\tComments : ").append(CommentCount);

        return post.toString();
    }*/
    
}