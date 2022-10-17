package com.ideas2it.controller;

import java.util.List;

import com.ideas2it.model.Post;
import com.ideas2it.service.PostService;

/**
 * Perform the Create, View, Update, Delete functionality of the Post
 *
 * @version 1.0 24-SEP-2022
 * @author Venkatesh TM
 */
public class PostController {
    private PostService postService;
     
    public PostController() { 
        this.postService = new PostService();
    }
    
    /**
     * Check the post is empty
     * 
     * @return boolean true or false based on the result
     */
    public boolean isPostEmpty() {
        return postService.isPostEmpty();
    }

    /**
     * Add the post 
     * 
     * @param  postedBy name of the user who posted the post
     * @parma  quotes   post of the user 
     * @return boolean  true after adding the post
     */
    public boolean addPost(String userId, String quotes) {
        return postService.addPost(userId, quotes);
    } 

    /**
     * Get the post uploaded by the user
     *
     * @return post list of post
     */
    public String getUserPost() {
        return postService.getUserPost();
    }   
    
    /**
     * Add like to the post 
     * 
     * @param likedUserName username of the person who liked 
     * @parm postId         id of the post
     */
    public boolean addLike(String likedUserName, String postId) {
        return postService.addLike(likedUserName, postId);
    }
    
    /**
     * gets the userName who liked the post
     *
     * @param  postId - id of the post
     * @return likedUserName userName of the people who liked for the post
     */
    public String getLikedUsers(String postId) {
        return postService.getLikedUsers(postId);
    }

    /** 
     * Add comment to particular post 
     * 
     * @param  postId  id of the post
     * @param  comment comment entered by the user
     * @return boolean true or false after adding the comment
     */
    public boolean addComment(String postId, String commentedBy, String comment) {
        return postService.addComment(postId, commentedBy, comment);
    }
    
    public List<String> getComments(String postId) {
        return postService.getComments(postId);
    }
   
    /**
     * Get post by the username 
     * 
     * @param  userName username of the user
     * @return String   post uploaded on the userName
     */ 
    public String getPostByUserName(String userName) {
        return postService.getPostByUserName(userName);
    }  
    
    /**
     * Delete the post based on the postId 
     * 
     * @param  postId id of the post
     * @return bolean true or false based 
     */
    public boolean deletePost(String postId) {
        return postService.deletePost(postId);
    }   
    
}
 