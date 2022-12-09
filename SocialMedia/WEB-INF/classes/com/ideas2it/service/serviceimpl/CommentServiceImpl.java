package com.ideas2it.service.serviceimpl;

import java.util.List;
import java.util.UUID;

import com.ideas2it.model.Comment;
import com.ideas2it.service.CommentService;
import com.ideas2it.service.PostService;
import com.ideas2it.service.serviceimpl.PostServiceImpl;
import com.ideas2it.dao.CommentDao;
import com.ideas2it.exception.CustomException;
import com.ideas2it.dao.daoImpl.CommentDaoImpl;

/**
 * Implements the logic of Create,Update,Delete Operation for Comment
 *
 * @version 1.0 02-NOV-2022
 * @author Venkatesh TM
 */
public class CommentServiceImpl implements CommentService {
    CommentDao commentDao;
    PostService postService;

    public CommentServiceImpl() {
        commentDao = new CommentDaoImpl();
        postService = new PostServiceImpl();
    }
    
    /**
     * Creates the comment 
     *
     * @param  comment - details of the comment
     * @return isCreated - true or false based on the response
     */
    public boolean create(Comment comment) throws CustomException {
        String id;
        boolean isCreated;
        
        id = UUID.randomUUID().toString();
        comment.setId(id);
        isCreated = (commentDao.create(comment) > 0);

        if (isCreated) {
            postService.updateCommentCount(comment.getPostId(),
                            commentDao.getCommentsCount(comment.getPostId()));
        }
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
        isUpdated = commentDao.update(id, content) > 0;
        return isUpdated;
    }
    
    /**
     * Deletes the comment based on the id 
     * 
     * @param id - id of the comment
     * @return isDeleted - true or false based on the response
     */
    public boolean delete(Comment comment) throws CustomException  {
        boolean isDeleted;
        isDeleted = commentDao.delete(comment.getId()) > 0;
        postService.updateCommentCount(comment.getPostId(),
                          commentDao.getCommentsCount(comment.getPostId()));
        return isDeleted;
    }  
    
    /**
     * Gets the comments for the particular post
     * 
     * @param  postId - id of the post
     * @return comments - list of comments 
     */
    public List<Comment> getComments(String postId) {
        List<Comment> listOfComments = commentDao.getComments(postId);

        if (listOfComments.isEmpty()) { 
            return null;
        }
        return listOfComments;
    }
    
    /**
     * Gets the particular comment
     * 
     * @param id - id of the comment
     * @return comment - comment details
     */
    public Comment getComment(String id) {
        return commentDao.getComment(id);
    }
}