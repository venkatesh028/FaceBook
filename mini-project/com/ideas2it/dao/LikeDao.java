package com.ideas2it.dao;

import java.util.List;

import com.ideas2it.model.Like;

/**
 * Perform create, delete and get function for the like
 * 
 * @version 1.0 01-NOV-2022
 * @author Venkatesh TM
 */
public interface LikeDao {
    
    /**
     * Creates the Like 
     *
     * @param like - Like details 
     * @return noOfRowsAffected - number of rows affected based on the creation
     */
    public int create(Like like);
    
    /**
     * Deleted the like of particular user for the particular post 
     *
     * @param postId - id of the post
     * @param unLikedUserId - id of the user who unLikedUserId
     * @return noOfRowsDeleted - number of rows deleted based on the creation
     */
    public int delete(String postId, String unLikedUserId);
    
    /**
     * Gets the like count for the particular post
     * 
     * @param postId - id of the post
     * @return likeCount - count of the like for the post
     */
    public int getLikeCountOfPost(String postId);
    
    /**
     * Gets the id of the liked user 
     * Based on the post id 
     *
     * @param postId - id of the post
     * @return listOfLikedUsersId - liked users id
     */
    public List<String> getLikedUsersIdOfPost(String postId);
    
    /**
     * Gets the username of the user who liked the post
     * Based on the postid 
     * 
     * @param postId - id of the post 
     * @return listOfLikedUserNames - liked usernames
     */
    public List<String> getLikedUserNamesOfPost(String postId);
}