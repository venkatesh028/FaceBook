package com.ideas2it.controller;

import java.util.List;

import com.ideas2it.model.Comment;
import com.ideas2it.service.CommentService;

/**
 * It performs the add, update, delete and get operation for the comment
 *
 * @version 1.0 03-Nov-2022
 * @author Venkatesh TM
 */
public class CommentController {
    CommentService commentService;
    
    public CommentController() {
        commentService = new CommentService();
    }
    
    /**
     * Adds comment 
     * 
     * @param  comment - Entire comment details
     * @return boolean - true or false based on the response
     */
    public boolean addComment(Comment comment) { 
        return commentService.create(comment);
    }
    
    
    /**
     * Updates the comment
     * 
     * @param  id - id of the comment
     * @param  content - comment enetered by the user
     * @return boolean - true or false based on the response
     */
    public boolean update(String id, String content) {
        return commentService.update(id, content);
    }
    
    /**
     * Deletes the comment
     * 
     * @param  comment - Entire comment details
     * @return boolean - true or false based on the response
     */
    public boolean deleteComment(Comment comment) {   
        return commentService.delete(comment);
    }
    
    /**
     * Gets the particular comment 
     * 
     * @param id - id of the comment
     * @return comment - entire details of the comment
     */
    public Comment getComment(String id) {
        return commentService.getComment(id);
    }
    
    /**
     * Gets the list of comment
     * 
     * @param  postId - id of the post
     * @return lisComments - list of comments
     */
    public List<Comment> getComments(String postId) {
        return commentService.getComments(postId);
    }
}