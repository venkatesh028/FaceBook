package com.ideas2it.controller;

import java.util.List;

import com.ideas2it.model.Post;
import com.ideas2it.service.PostService;
import com.ideas2it.exception.CustomException;
import com.ideas2it.logger.CustomLogger;

/**
 * Implements the create, get, update, delete operation for the post
 *
 * @version 1.0 24-SEP-2022
 * @author Venkatesh TM
 */
public class PostController {
    private PostService postService;
    private CustomLogger logger;
       
    public PostController() { 
        this.postService = new PostService();
        this.logger = new CustomLogger(PostController.class);
    }

    /**
     * Add the post 
     * 
     * @param  userId  - id of the user who posted the post
     * @parma  content  - post of the user 
     * @return boolean -  true after adding the post
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
        List<Post> listOfPosts = null;
         
        try {
            listOfPosts = postService.getUserPosts();
        } catch (CustomException customException) {
            logger.error(customException.getMessage());
        }
        return listOfPosts;
    }
    
    /**
     * Gets the post based on there userName 
     * 
     * @param  userId   - id of the user
     * @return userPosts - posts of the particular user
     */
    public List<Post> getPostOfParticularUser(String userId) {
        List<Post> listOfPosts = null;
         
        try {
            listOfPosts = postService.getPostOfParticularUser(userId);;
        } catch (CustomException customException) {
            logger.error(customException.getMessage());
        }
        return listOfPosts;
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
 