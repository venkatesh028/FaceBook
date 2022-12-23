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
import com.ideas2it.service.serviceimpl.CommentServiceImpl;
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
        commentService = new CommentServiceImpl();
        this.logger = new CustomLogger(CommentController.class);
    }
    

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException,
                                                        ServletException {
        String path = request.getServletPath();
        
        switch (path) {
        case "/viewComments":
            getComments(request, response);
            break;

        case "/edit-comment":
            getComment(request, response);
            break;

        case "/delete-comment":
            deleteComment(request, response);
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
        
        case "/update-comment":
            update(request, response);
            break;

        }
    }    
    
    /**
     * Adds comment to the particular post 
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
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
            request.setAttribute("error", customException.getMessage());
            requestDispatcher.forward(request, response);          
        } 
    }
 
    /**
     * Gets the list of comment
     * 
     * @param  postId - id of the post
     * @return lisComments - list of comments
     */
    private void getComments(HttpServletRequest request,
                             HttpServletResponse response) throws IOException,
                                                            ServletException {
        try {
            request.setAttribute("root", "newsFeed");
            request.setAttribute("listOfComments", commentService
                                  .getComments(request.getParameter("postId")));
            request.setAttribute("postId", request.getParameter("postId"));
            RequestDispatcher requestDispatcher = request
                                    .getRequestDispatcher("viewComments.jsp"); 
            requestDispatcher.forward(request, response);
        } catch (CustomException customException) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
            request.setAttribute("error", customException.getMessage());
            requestDispatcher.forward(request, response);
        }    
    }   
    
    /**
     * Updates the comment
     * 
     * @param  id - id of the comment
     * @param  content - comment enetered by the user
     * @return boolean - true or false based on the response
     */
    private void update(HttpServletRequest request,
                        HttpServletResponse response) throws IOException,
                                                       ServletException {
        
        try {
             commentService.update(request.getParameter("id"),
                                              request.getParameter("content"));
             response.sendRedirect("newsFeed");
        } catch (CustomException customException) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
            request.setAttribute("error", customException.getMessage());
            requestDispatcher.forward(request, response);
        }
    }
    
    /**
     * Deletes the comment
     * 
     * @param  comment - Entire comment details
     * @return boolean - true or false based on the response
     */
    private void deleteComment(HttpServletRequest request,
                               HttpServletResponse response) throws IOException,
                                                              ServletException {         
        try {
            Comment comment = new Comment();
            comment.setId(request.getParameter("commentId"));   
            comment.setPostId(request.getParameter("postId"));
            commentService.delete(comment);
            response.sendRedirect("newsFeed");
        } catch (CustomException customException) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
            request.setAttribute("error", customException.getMessage());
            requestDispatcher.forward(request, response);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
        }
    }
    
    /**
     * Gets the particular comment 
     * 
     * @param id - id of the comment
     * @return comment - entire details of the comment
     */
    private void getComment(HttpServletRequest request,
                            HttpServletResponse response) throws IOException,
                                                           ServletException {
        try {
            Comment comment = commentService.getComment(request.getParameter("commentId"));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewComments.jsp"); 
            request.setAttribute("comment", comment);
            requestDispatcher.forward(request, response);
        } catch (CustomException customException) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
            request.setAttribute("error", customException.getMessage());
            requestDispatcher.forward(request, response);
        }   
    }
}