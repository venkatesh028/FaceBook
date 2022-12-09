package com.ideas2it.service.serviceimpl;

import com.ideas2it.dao.LikeDao;
import com.ideas2it.dao.daoImpl.LikeDaoImpl;
import com.ideas2it.exception.CustomException;
import com.ideas2it.model.Like;
import com.ideas2it.service.LikeService;
import com.ideas2it.service.PostService;

import java.util.List;
import java.util.UUID;

/**
 * Implements the logic for Create,Update,Delete Operation of Like
 *
 * @version 1.0 02-NOV-2022
 * @author Venkatesh TM
 */
public class LikeServiceImpl implements LikeService {
    LikeDao likeDao;
    PostService postService;

    public LikeServiceImpl() {
        likeDao = new LikeDaoImpl();  
        postService = new PostServiceImpl();
    }
    
    /** 
     * Creates the like by setting id 
     * 
     * @param  like - details of the like 
     * @return isCreated - true or false based on the response 
     */
    public boolean create(Like like) {
        String id = UUID.randomUUID().toString();
        like.setId(id);
        boolean isCreated = (likeDao.create(like) > 0);
        return isCreated;
    }
    
    /**
     * Deletes the like for the particular post
     *
     * @param  postId - id of the post
     * @param  userId - id of the user
     * @return isDeleted - true or false based on the response
     */
    public boolean delete(String postId, String userId) {
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
    public int getLikeCountOfPost(String postId) {
           return likeDao.getLikeCountOfPost(postId);
    }
    
    /**
     * Gets the list of the liked usernames
     * 
     * @param postId - id of the post
     * @return lisOfLikedUser - list odf liked usernames for that post
     */
    public List<String> getLikedUserNames(String postId) {
        List<String> listOfLikedUsers = likeDao
                             .getLikedUserNamesOfPost(postId);
        
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
    public boolean addLike(Like like) throws CustomException {
        List<String> likedUsers;
        boolean isAdded = false;
        likedUsers = likeDao.getLikedUsersIdOfPost(like.getPostId());
        
        if (!likedUsers.contains(like.getLikedUserId())) {
            isAdded = create(like);  
            postService.updateLikeCount(like.getPostId(), 
                               getLikeCountOfPost(like.getPostId()));
        } else {
            delete(like.getPostId(), like.getLikedUserId()); 
            postService.updateLikeCount(like.getPostId(),
                               getLikeCountOfPost(like.getPostId()));   
        }
        return isAdded;
    }   
}