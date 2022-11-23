package com.ideas2it.service;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import com.ideas2it.model.Like;
import com.ideas2it.dao.LikeDao;
import com.ideas2it.dao.daoImpl.LikeDaoImpl;

/**
 * Implements the logic for Create,Update,Delete Operation of Like
 *
 * @version 1.0 02-NOV-2022
 * @author Venkatesh TM
 */
public class LikeService {
    LikeDao likeDao;
    PostService postService;

    public LikeService() {
        likeDao = new LikeDaoImpl();  
        postService = new PostService();  
    }
    
    /** 
     * Creates the like by setting id 
     * 
     * @param  like - details of the like 
     * @return isCreated - true or false based on the response 
     */
    private boolean create(Like like) {
        String id;
        boolean isCreated; 
        
        id = UUID.randomUUID().toString();
        like.setId(id);
        isCreated = (likeDao.create(like) > 0);
        return isCreated;
    }
    
    /**
     * Deletes the like for the particular post
     *
     * @param  postId - id of the post
     * @param  userId - id of the user
     * @return isDeleted - true or false based on the response
     */
    private boolean delete(String postId, String userId) {
        boolean isDeleted;
        isDeleted = (likeDao.delete(postId, userId) > 0);
        return isDeleted;
    }
    
    /**
     * Gets the like count of the post based on the postId
     *
     * @param  postId - id of the post
     * @return likeCount - count of the like 
     */
    private int getLikeCountOfPost(String postId) {
           return likeDao.getLikeCountOfPost(postId);
    }
    
    /**
     * Gets the list of the liked usernames
     * 
     * @param postId - id of the post
     * @return lisOfLikedUser - list odf liked usernames for that post
     */
    public List<String> getLikedUserNames(String postId) {
        List<String> listOfLikedUsers= likeDao.getLikedUserNamesOfPost(postId);
        
        if (!listOfLikedUsers.isEmpty()) {
            return listOfLikedUsers;
        }
        
        return null;
    }
    
    /**
     * Add like to the post
     * 
     * @param like - details of the like 
     * @return isAdded - true or false based on the response
     */
    public boolean addLike(Like like) {
        List<String> likedUsers;
        boolean isAdded = false;
        likedUsers = likeDao.getLikedUsersIdOfPost(like.getPostId());
        
        if (!likedUsers.contains(like.getLikedUserId())) {
            isAdded = create(like);  
            postService.updateLikeCount(like.getPostId(),getLikeCountOfPost(like.getPostId()));
        } else {
            delete(like.getPostId(), like.getLikedUserId()); 
            postService.updateLikeCount(like.getPostId(),getLikeCountOfPost(like.getPostId()));   
        }
        return isAdded;
    }   
}