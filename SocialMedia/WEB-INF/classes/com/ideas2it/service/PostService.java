package com.ideas2it.service;

import com.ideas2it.exception.CustomException;
import com.ideas2it.model.Post;

import java.util.List;

public interface PostService {

    /**
     * Creates the post 
     * 
     * @param  postedUserId - id of the user who posted the post
     * @param  content   - post of the user
     * @return boolean  - true or false based on the reponse
     */
    public boolean create(String postedUserId, String content) throws CustomException;

    /**
     * Updates the content posted by the user
     * Based on the id 
     *
     * @param id - id of the post
     * @param content - content uploaded by the user
     * @return isUpdated - true or false based on the response
     */
    public boolean update(String id, String content)throws CustomException;

    /**
     * Updates the like count of post based on the post id
     * 
     * @param postId - id of the post 
     * @param likeCount - count of the likes
     * @return isUpdated - true or false based on the response
     */
    public boolean updateLikeCount(String postId, int likeCount) throws CustomException;

    /**
     * Updates the comment count of post based on the post id
     * 
     * @param postId - id of the post 
     * @param commentCount - count of the comment
     * @return isUpdated - true or false based on the response
     */
    public boolean updateCommentCount(String postId, int commentCount) throws CustomException;

    /**
     * Deletes the particular post of the user
     * Based on the postId
     * 
     * @param  id - id of the post
     * @return isDeleted - true or false based on the response
     */
    public boolean delete(String id) throws CustomException;

    /**
     * Gets the post uploaded by the user
     *
     * @return allPosts - all the post 
     */
    public List<Post> getUserPosts() throws CustomException;

    /**
     * Gets the post based on there userId 
     * 
     * @param  userId   - id of the user
     * @return userPosts - posts of the particular user
     */
    public List<Post> getPostOfParticularUser(String userId) throws CustomException;
 
    /**
     * Gets the post based on the id 
     * 
     * @param id - id of the post 
     * @return post - post based on the id
     */
    public Post getPost(String id) throws CustomException;
   
}