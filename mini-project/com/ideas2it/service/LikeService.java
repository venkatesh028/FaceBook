package com.ideas2it.service;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import com.ideas2it.model.Like;
import com.ideas2it.dao.LikeDao;
import com.ideas2it.dao.daoImpl.LikeDaoImpl;

/**
 * Perform is the Create,Update,Delete Operation of Like
 *
 * @version 1.0 02-NOV-2022
 * @author Venkatesh TM
 */
public class LikeService {
    LikeDao likeDao;

    public LikeService() {
        likdeDao = new LikeDaoImpl();   
    }

    private boolean create(Like like) {
        String id;
        boolean isCreated; 
        
        id = UUID.randomUUID().toString();
        like.setId(id);
        isCreated = (likeDao.create(like) > 0);
        return isCreated;
    }
    
    private boolean delete(String postId, String unLikedUserId) {
        boolean isDeleted;
        isDeleted = (likeDao.delete(postId, unLikeUserId) > 0);
        return isDeleted;
    }
    
    public int getLikeCountOfPost(String postId) {
           return likeDao.getLikeCountOfPost(postId);
    }
    
    public List<String> getLikedUserNames(String postId) {
        return likeDao.getLikedUserNamesOfPost(postId);
    }

    public boolean addLike(Like like) {
        List<String> likedUsers;
        boolean isAdded;
        likedUsers = getLikedUsersIdOfPost(like.getPostId());
        
        if (!likedUsers.contains(like.getLikedUserId())) {
            isAdded = create(like);    
        } else {
            delete(like.getPostId(), like.getLikedUserId());    
        }
        return isAdded;
    }   
}