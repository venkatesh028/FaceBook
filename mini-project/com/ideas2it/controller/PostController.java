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
     * Add the post 
     * 
     * @param  postedBy name of the user who posted the post
     * @parma  content   post of the user 
     * @return boolean  true after adding the post
     */
    public boolean addPost(String userId, String content) {
        return postService.create(userId, content);
    } 
    
    /**
     * Updates the post 
     * 
     * @param postId - id of the post
     * @param content - content updated by the user
     * @return boolean - true or false based on the response
     */
    public boolean update(String postId, String content) { 
        return postService.update(postId, content);
    }

    /**
     * Gets the post uploaded by the user
     *
     * @return allPosts - all the post 
     */
    public List<Post> getUserPosts() {
        return postService.getUserPosts();
    }
    
    /**
     * Gets the post based on there userName 
     * 
     * @param  userId   - id of the user
     * @return userPosts - posts of the particular user
     */
    public List<Post> getPostOfParticularUser(String userId) {
        return postService.getPostOfParticularUser(userId);
    } 

    /**
     * Delete the post based on the postId 
     * 
     * @param  postId - id of the post
     * @return bolean - true or false based 
     */
    public boolean delete(String postId) {
        return postService.delete(postId);
    }       
}
 