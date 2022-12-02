package com.ideas2it.controller;

import java.util.List;

import com.ideas2it.model.Comment;
import com.ideas2it.service.CommentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import com.ideas2it.logger.CustomLogger;


/**
 * It performs the add, update, delete and get operation for the comment
 *
 * @version 1.0 03-Nov-2022
 * @author Venkatesh TM
 */
public class CommentController extends HttpServlet {
    private CommentService commentService;
    private CustomLogger logger;

    public CommentController() {
        commentService = new CommentService();
        this.logger = new CustomLogger(CommentController.class);
    }
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = request.getServletPath();
         
        if (path == "/viewComments") {
            request.setAttribute("root", "newsFeed");
        } else {
            request.setAttribute("root", "profile");
        }
        request.setAttribute("listOfComments", getComments(request.getParameter("postId")));
        request.setAttribute("postId", request.getParameter("postId"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewComments.jsp"); 
        requestDispatcher.forward(request, response);        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Comment comment = new Comment();
        HttpSession session = request.getSession();
        comment.setCommentedUserId((String) session.getAttribute("userId"));
        comment.setPostId(request.getParameter("postId"));
        comment.setContent(request.getParameter("content"));
        addComment(comment);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("newsFeed"); 
        requestDispatcher.forward(request, response);
    }    
    
    /**
     * Adds comment 
     * 
     * @param  comment - Entire comment details
     * @return boolean - true or false based on the response
     */
    private  boolean addComment(Comment comment) { 
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