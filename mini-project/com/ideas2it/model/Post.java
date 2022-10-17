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
    private String postId;
    private String postedBy;
    private String quote;
    private List<Like> likes = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
    private int likeCount;
 
    public Post(String postId, String postedBy, String quote) {
        this.postId = postId;
        this.postedBy = postedBy;
        this.quote = quote;  
    }
    
    public void setPostId(String postId) {
        this.postId = postId;
    }
    
    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;    
    }  
    
    public void setQuote(String quote) {
        this.quote = quote;
    }
    
    public void setLike(Like like) {
        this.likes.add(like);
    }
    
    public void setComment(Comment comment) {
        this.comments.add(comment);
    }
    
    public List<Like> getLikedUsers() {
        return likes;
    }
    
    public String getPostId() {
        return postId;
    }
    
    public List<Comment> getComments() {
        return comments;
    }
    
    public int getCommentCount() {
        return getComments().size();
    }
    
    public  int getLikeCount() {
        return likeCount = getLikedUsers().size();        
    }
    
    public String getPostedBy() {
        return postedBy;
    }
    
    public String toString() {
        StringBuilder post = new StringBuilder();
        post.append("\npost Id  : ").append(postId)
            .append("\npostedBy : ").append(postedBy)
            .append("\nquotes   : ").append(quote)
            .append("\nLikes    : ").append(getLikeCount())
            .append("\tComments : ").append(getCommentCount());

        return post.toString();
    }
    
}