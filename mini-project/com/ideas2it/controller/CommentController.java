package com.ideas2it.controller;

import com.ideas2it.model.Comment;
import com.ideas2it.service.CommentService;

public class CommentController {
    CommentService commentService;
    
    public CommentController() {
        commentService = new CommentService();
    }
    
    public boolean create(Comment comment) { 
        return commentService.create(comment);
    }
    
    public boolean update(String id, String content) {
        return commentService.update(id, content);
    }
   
    public boolean delete(String id) {   
        return commentService.delete(id);
    }
    
    public List<Comment> getComments(String postId) {
        return commentService.getComments(postId);
    }
}