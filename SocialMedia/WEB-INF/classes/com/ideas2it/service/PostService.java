package com.ideas2it.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ideas2it.model.Comment;
import com.ideas2it.model.Like;
import com.ideas2it.model.Post;
import com.ideas2it.dao.PostDao;
import com.ideas2it.dao.daoImpl.PostDaoImpl;
import com.ideas2it.exception.CustomException;
import com.ideas2it.constant.Constants;

/**
 * Implements the create, get, update, delete operation for the post 
 * 
 * @version 1.0 23-SEP-2022
 * @author Venkatesh TM
 */
public class PostService {
    private PostDao postDao;
    
    public PostService() {
        this.postDao = new PostDaoImpl();
    }
    
    /**
     * Creates the post 
     * 
     * @param  postedUserId - id of the user who posted the post
     * @parma  content   - post of the user 
     * @return boolean  - true or false based on the reponse
     */
    public boolean create(String postedUserId, String content) throws CustomException { 
        boolean isCreated;
        String id = UUID.randomUUID().toString();
        Post post = new Post(id, postedUserId, content);
        isCreated = (postDao.create(post) > 0) ;
        return isCreated;  
    }

    /**
     * Updates the content posted by the user
     * Based on the id 
     *
     * @param id - id of the post
     * @param content - content uploaded by the user
     * @return isUpdated - true or false based on the response
     */
    public boolean update(String id, String content)throws CustomException {
        boolean isUpdated = (postDao.update(id, content) > 0); 
        return isUpdated;        
    } 
    
    /**
     * Updates the like count of post based on the post id
     * 
     * @param postId - id of the post 
     * @param likeCount - count of the likes
     * @return isUpdated - true or false based on the response
     */
    public boolean updateLikeCount(String postId, int likeCount) throws CustomException {
        boolean isUpdated = (postDao.updateLikeCount(postId,likeCount) > 0);
        return isUpdated;    
    } 
     
    /**
     * Updates the comment count of post based on the post id
     * 
     * @param postId - id of the post 
     * @param commentCount - count of the comment
     * @return isUpdated - true or false based on the response
     */
    public boolean updateCommentCount(String postId, int commentCount) throws CustomException {
        boolean isUpdated = (postDao.updateCommentCount(postId, commentCount) > 0);
        return isUpdated;
    }

    /**
     * Deletes the particular post of the user
     * Based on the postId
     * 
     * @param  id - Id of the post
     * @return isDeleted - true or false based on the response
     */
    public boolean delete(String id) throws CustomException { 
        boolean isDeleted = (postDao.delete(id) > 0) ? true : false;
        return isDeleted;
    }

    /**
     * Gets the post uploaded by the user
     *
     * @return allPosts - all the post 
     */
    public List<Post> getUserPosts() throws CustomException {
        List<Post> allPosts;
        allPosts = postDao.getUserPosts();
        
        if (allPosts.isEmpty()) { 
            throw new CustomException(Constants.ERROR_03);   
        }
        return allPosts;
    }
    
    /**
     * Gets the post based on there userId 
     * 
     * @param  userId   - id of the user
     * @return userPosts - posts of the particular user
     */
    public List<Post> getPostOfParticularUser(String userId) throws CustomException {
        List<Post> userPosts = postDao.getPostOfParticularUser(userId);            
        return userPosts;
    }
 
    /**
     * Gets the post based on the id 
     * 
     * @param id - id of the post 
     * @return post - post based on the id
     */
    public Post getPost(String id) throws CustomException {
        return postDao.getPost(id);
    }
}