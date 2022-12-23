package com.ideas2it.service.serviceimpl;


import java.util.List;
import java.util.UUID;

import com.ideas2it.model.Comment;
import com.ideas2it.model.Like;
import com.ideas2it.model.Post;
import com.ideas2it.service.PostService;
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
public class PostServiceImpl implements PostService {
    private PostDao postDao;
    
    public PostServiceImpl() {
        this.postDao = new PostDaoImpl();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean create(String postedUserId, String content) throws CustomException { 
        boolean isCreated;
        String id = UUID.randomUUID().toString();
        Post post = new Post(id, postedUserId, content);
        isCreated = (postDao.create(post) > 0) ;
        return isCreated;  
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(String id, String content)throws CustomException {
        boolean isUpdated = (postDao.update(id, content) > 0); 
        return isUpdated;        
    } 
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateLikeCount(String postId, int likeCount) throws CustomException {
        boolean isUpdated = (postDao.updateLikeCount(postId,likeCount) > 0);
        return isUpdated;    
    } 
     
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateCommentCount(String postId, int commentCount) throws CustomException {
        boolean isUpdated = (postDao.updateCommentCount(postId, commentCount) > 0);
        return isUpdated;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(String id) throws CustomException { 
        boolean isDeleted = (postDao.delete(id) > 0) ? true : false;
        return isDeleted;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Post> getUserPosts() throws CustomException {
        List<Post> allPosts;
        allPosts = postDao.getUserPosts();
        
        if (allPosts.isEmpty()) { 
            throw new CustomException(Constants.ERROR_03);   
        }
        return allPosts;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Post> getPostOfParticularUser(String userId) throws CustomException {
        List<Post> userPosts = postDao.getPostOfParticularUser(userId);            
        return userPosts;
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public Post getPost(String id) throws CustomException {
        return postDao.getPost(id);
    }
}