package com.ideas2it.service;

import java.util.List;

import com.ideas2it.model.Comment;
import com.ideas2it.exception.CustomException;

/**
 * Implements the logic of Create,Update,Delete Operation for Comment
 *
 * @version 1.0 09-DEC-2022
 * @author Venkatesh TM
 */
public interface CommentService {

    /**
     * Creates the comment 
     *
     * @param  comment - details of the comment
     * @return isCreated - true or false based on the response
     */
    public boolean create(Comment comment) throws CustomException;

    /**
     * Updates the comment based on the comment id 
     * 
     * @param id - id of the comment 
     * @param content - updated content of the comment
     * @return isUpdted - true or false based on the response
     */
    public boolean update(String id, String content);

    /**
     * Deletes the comment based on the id 
     * 
     * @param id - id of the comment
     * @return isDeleted - true or false based on the response
     */
    public boolean delete(Comment comment) throws CustomException;

    /**
     * Gets the comments for the particular post
     * 
     * @param  postId - id of the post
     * @return comments - list of comments 
     */
    public List<Comment> getComments(String postId);

    /**
     * Gets the particular comment
     * 
     * @param id - id of the comment
     * @return comment - comment details
     */
    public Comment getComment(String id);
}