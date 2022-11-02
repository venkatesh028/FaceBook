package com.ideas2it.service;

import java.util.UUID;

import com.ideas2it.model.comment;
import com.ideas2it.dao.CommentDao;
import com.ideas2it.dao.daoImpl.CommentDaoImpl;

/**
 * Perform is the Create,Update,Delete Operation of Comment
 *
 * @version 1.0 02-NOV-2022
 * @author Venkatesh TM
 */
public class CommentService {
    CommentDao commentDao;
    
    public CommentService() {
        commentDao = new CommentDaoImpl();
    }
    
    /**
     * Creates comment 
     *
     * @param  comment - details of the comment
     * @return isCreated - true or false based on the response
     */
    public boolean create(Comment comment) {
        String id;
        boolean isCreated;
        
        id = UUID.randomUUID().toString();
        comment.setId(id);
        isCreated = (commentDao.create(comment) > 0);
        return isCreated;
    }
    
    /**
     * Updates the comment based on the comment id 
     * 
     * @param id - id of the comment 
     * @param content - updated content of the comment
     * @return isUpdted - true or false based on the response
     */
    public boolean update(String id, String content) {
        boolean isUpdated;
        isUpdated = commentDao.update(id, content);
        return isUpdated;
    }
    
    /**
     * Deletes the comment based on the id 
     * 
     * @param id - id of the comment
     * @return isDeleted - true or false based on the response
     */
    public boolean delete(String id) {
        boolean isDeleted;
        isDeleted = commentDao.Delete(id);
    }  
    
    /**
     * Gets the comments for the particular post
     * 
     * @param  postId - id of the post
     * @return comments - list of comments 
     */
    public List<Comment> getComments(String postId) {
        List<Comment> listOfComments;
        listOfComments = commentDao.getComments(postId);
        return listOfComments;
    }
}