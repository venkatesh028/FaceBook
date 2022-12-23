package com.ideas2it.dao;

import java.util.List;

import com.ideas2it.model.Comment;
import com.ideas2it.exception.CustomException;

/**
 * It is interface for the Comment dao impl it contains the method for
 * create, update, delete, get operations for comment
 *
 * @version 1.0 01-Nov-2022
 * @author Venkatesh TM
 */
public interface CommentDao {
    
    /**
     * Creates the comment
     *
     * @param  comment - details of the comment
     * @return noOfRowsAffected - number of rows affected based on the comment creation
     */
    public int create(Comment comment)  throws CustomException;

    /**
     * Updates the comment based on the comment id 
     * 
     * @param id - id of the comment 
     * @param content - updated content of the comment
     * @return noOfRowsUpdated - number of rows updated based on the comment updation
     */
    public int update(String id, String content) throws CustomException;
    
    /**
     * Deletes the comment based on the id 
     * 
     * @param id - id of the comment
     * @return noOfRowsDeleted - number of rows deleted based on the deletion
     */
    public int delete(String id) throws CustomException;
    
    /**
     * Gets the count of comments
     * 
     * @param postId - id of the post
     */
    public int getCommentsCount(String postId) throws CustomException;

    /**
     * Gets the comments for the particular post
     * 
     * @param  postId - id of the post
     * @return comments - list of comments 
     */
    public List<Comment> getComments(String postId) throws CustomException; 
    
    /**
     * Gets the particular comment based on the id 
     *
     * @param id - id of the comment
     * @return comment - details of the comment
     */ 
    public Comment getComment(String id) throws CustomException;    
}
