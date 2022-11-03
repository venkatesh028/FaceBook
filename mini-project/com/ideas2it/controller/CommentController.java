package com.ideas2it.controller;

import java.util.List;

import com.ideas2it.model.Comment;
import com.ideas2it.service.CommentService;

public class CommentController {
    CommentService commentService;
    
    public CommentController() {
        commentService = new CommentService();
    }
    
    public boolean addComment(Comment comment) { 
        return commentService.create(comment);
    }
    
    public boolean update(String id, String content) {
        return commentService.update(id, content);
    }
   
    public boolean deleteComment(Comment comment) {   
        return commentService.delete(comment);
    }
    
    public Comment getComment(String id) {
        return commentService.getComment(id);
    }
    
    public List<Comment> getComments(String postId) {
        return commentService.getComments(postId);
    }
}