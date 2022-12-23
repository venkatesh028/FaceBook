package com.ideas2it.service;

import com.ideas2it.exception.CustomException;
import com.ideas2it.model.Like;

import java.util.List;

/**
 * Implements the logic for Create,Update,Delete Operation of Like
 *
 * @version 1.0 02-NOV-2022
 * @author Venkatesh TM
 */
public interface LikeService {

    /** 
     * Creates the like by setting id 
     * 
     * @param  like - details of the like 
     * @return isCreated - true or false based on the response 
     */
    public boolean create(Like like);

    /**
     * Deletes the like for the particular post
     *
     * @param  postId - id of the post
     * @param  userId - id of the user
     * @return isDeleted - true or false based on the response
     */
    public boolean delete(String postId, String userId);

    /**
     * Gets the like count of the post based on the postId
     *
     * @param  postId - id of the post
     * @return likeCount - count of the like 
     */
    public int getLikeCountOfPost(String postId);

    /**
     * Gets the list of the liked usernames
     * 
     * @param postId - id of the post
     * @return lisOfLikedUser - list odf liked usernames for that post
     */
    public List<String> getLikedUserNames(String postId);

    /**
     * Add like to the post
     * 
     * @param like - details of the like 
     * @return isAdded - true or false based on the response
     */
    public boolean addLike(Like like) throws CustomException;
}