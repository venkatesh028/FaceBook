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
     * {@inheritDoc}
     */
    @Override
    public boolean create(Like like) {
        String id = UUID.randomUUID().toString();
        like.setId(id);
        boolean isCreated = (likeDao.create(like) > 0);
        return isCreated;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(String postId, String userId) {
        boolean isDeleted;
        isDeleted = (likeDao.delete(postId, userId) > 0);
        return isDeleted;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int getLikeCountOfPost(String postId) {
           return likeDao.getLikeCountOfPost(postId);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getLikedUserNames(String postId) {
        List<String> listOfLikedUsers = likeDao
                             .getLikedUserNamesOfPost(postId);
        
        if (!listOfLikedUsers.isEmpty()) {
            return listOfLikedUsers;
        }
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
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