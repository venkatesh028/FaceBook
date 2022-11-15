package com.ideas2it.controller;

import java.util.List;

import com.ideas2it.model.Like;
import com.ideas2it.service.LikeService;

/**
 * Implements the logic of  add and get operations for the like
 *
 * @version 1.0 03-NOV-2022
 * @author Venkatesh TM
 */
public class LikeController {
    
    LikeService likeService;
  
    public LikeController() {
        likeService = new LikeService();    
    }
    
    /**
     * Adds like
     * 
     * @param like - details of the like
     * @return boolean - true or false based on the response
     */
    public boolean addLike(Like like) {
        return likeService.addLike(like);
    }
    
    /**
     * Gets the liked user names
     *
     * @param postId - id of the post
     * @return listOfLikedUser - list of liked user names
     */
    public List<String> getLikedUserNames(String postId) {
        return likeService.getLikedUserNames(postId);
    } 
}