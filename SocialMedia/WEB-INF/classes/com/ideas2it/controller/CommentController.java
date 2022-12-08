package com.ideas2it.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.ideas2it.model.Comment;
import com.ideas2it.service.CommentService;
import com.ideas2it.exception.CustomException;
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
    

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException,
                                                        ServletException {
        String path = request.getServletPath();
        
        switch (path) {
        case "/viewComments":
            getComment(request, response, path);
            break;

        case "/edit-comment":
            break;
        
        case "/delete-commet":
            break;
        }      
    }
    
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException,
                                                         ServletException {
        String path = request.getServletPath();
        
        switch (path) {
        case "/addComment":
            addComment(request, response);
            break;
        }
    }    
    
    /**
     * Adds comment 
     * 
     * @param  comment - Entire comment details
     * @return boolean - true or false based on the response
     */
    private void addComment(HttpServletRequest request,
                            HttpServletResponse response) throws IOException,
                                                           ServletException {
        try {
            Comment comment = new Comment();
            HttpSession session = request.getSession();
            comment.setCommentedUserId((String) session.getAttribute("userId"));
            comment.setPostId(request.getParameter("postId"));
            comment.setContent(request.getParameter("content"));
            commentService.create(comment);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("newsFeed"); 
            requestDispatcher.forward(request, response);
        } catch (CustomException customException) {
          
        } 
    }    
    
    /**
     * Updates the comment
     * 
     * @param  id - id of the comment
     * @param  content - comment enetered by the user
     * @return boolean - true or false based on the response
     */
    private boolean update(String id, String content) {
        return commentService.update(id, content);
    }
    
    /**
     * Deletes the comment
     * 
     * @param  comment - Entire comment details
     * @return boolean - true or false based on the response
     */
    private boolean deleteComment(Comment comment) {   
        boolean isDeleted = false;
         
        try {
           isDeleted = commentService.delete(comment);
        } catch (CustomException customException) {
        }
        return isDeleted;
    }
    
    /**
     * Gets the particular comment 
     * 
     * @param id - id of the comment
     * @return comment - entire details of the comment
     */
    private void getComment(HttpServletRequest request,
                               HttpServletResponse response, 
                               String path) throws IOException,
                                             ServletException {
        request.setAttribute("root", "newsFeed");
        request.setAttribute("listOfComments", commentService
                             .getComment(request.getParameter("postId")));
        request.setAttribute("postId", request.getParameter("postId"));
        RequestDispatcher requestDispatcher = request
                             .getRequestDispatcher("viewComments.jsp"); 
        requestDispatcher.forward(request, response);          
    }
    
    /**
     * Gets the list of comment
     * 
     * @param  postId - id of the post
     * @return lisComments - list of comments
     */
    private List<Comment> getComments(String postId) {
        return commentService.getComments(postId);
    }
}