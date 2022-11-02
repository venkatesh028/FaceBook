package com.ideas2it.dao;

import java.util.List;

import com.ideas2it.model.Comment;

/**
 * Perform the Creation,Updation,Deletion and Get operation of the comment
 *
 * @version 1.0 01-Nov-2022
 * @author Venkatesh TM
 */
public interface CommentDao {
    
    /**
     * Creates comment 
     *
     * @param  comment - details of the comment
     * @return noOfRowsAffected - number of rows affected based on the comment creation
     */
    public int create(Comment comment);

    /**
     * Updates the comment based on the comment id 
     * 
     * @param id - id of the comment 
     * @param content - updated content of the comment
     * @return noOfRowsUpdated - number of rows updated based on the comment updation
     */
    public int update(String id, String content);
    
    /**
     * Deletes the comment based on the id 
     * 
     * @param id - id of the comment
     * @return noOfRowsDeleted - number of rows deleted based on the deletion
     */
    public int delete(String id);

    /**
     * Gets the comments for the particular post
     * 
     * @param  postId - id of the post
     * @return comments - list of comments 
     */
    public List<Comment> getComments(String postId);     
}
