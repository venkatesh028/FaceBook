package com.ideas2it.service.serviceimpl;

import java.util.List;
import java.util.UUID;

import com.ideas2it.model.Comment;
import com.ideas2it.service.CommentService;
import com.ideas2it.service.PostService;
import com.ideas2it.service.serviceimpl.PostServiceImpl;
import com.ideas2it.dao.CommentDao;
import com.ideas2it.constant.Messages;
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
     * {@inheritDoc}
     */
    @Override
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
     * {@inheritDoc}
     */
    @Override
    public boolean update(String id, String content) throws CustomException {
        boolean isUpdated;
        isUpdated = commentDao.update(id, content) > 0;
        return isUpdated;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(Comment comment) throws CustomException  {
        boolean isDeleted;
        isDeleted = commentDao.delete(comment.getId()) > 0;
        postService.updateCommentCount(comment.getPostId(),
                          commentDao.getCommentsCount(comment.getPostId()));
        return isDeleted;
    }  
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Comment> getComments(String postId) throws CustomException {
        List<Comment> listOfComments = commentDao.getComments(postId);

        if (listOfComments.isEmpty()) { 
            return null;
        }
        return listOfComments;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Comment getComment(String id) throws CustomException {
        return commentDao.getComment(id);
    }
}