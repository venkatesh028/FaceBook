package com.ideas2it.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ideas2it.model.Post;
import com.ideas2it.service.PostService;
import com.ideas2it.constant.Messages;
import com.ideas2it.exception.CustomException;
import com.ideas2it.logger.CustomLogger;

/**
 * Implements the create, get, update, delete operation for the post
 *
 * @version 1.0 24-SEP-2022
 * @author Venkatesh TM
 */
public class PostController extends HttpServlet {
    private PostService postService;
    private CustomLogger logger;
       
    public PostController() { 
        this.postService = new PostService();
        this.logger = new CustomLogger(PostController.class);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, 
                                                         ServletException {
        String path = request.getServletPath();

        switch (path) {
        case "/newsFeed":      
            getUserPosts(request, response);             
            break;

        case "/addPost":
            addPost(request, response);
            break;
        }        
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, 
                                                        ServletException {
        String path = request.getServletPath();

        switch (path) {
        case "/newsFeed":    
            getUserPosts(request, response);           
            break;
        
        case "/edit-post":
            getPost(request, response);
            break;
        
        case "/update-post":
            update(request, response);
            break;            

        case "/delete-post":
            delete(request, response);
            break;
        }        
    }

    /**
     * Add the post 
     * 
     * @param  userId  - id of the user who posted the post
     * @parma  content  - post of the user 
     * @return boolean -  true after adding the post
     */
    private void addPost(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, 
                                                        ServletException {
        try {
            HttpSession session = request.getSession();
            RequestDispatcher requestDispatcher = request
                                        .getRequestDispatcher("addPost.jsp");
            String userId = (String) session.getAttribute("userId");
            String content = request.getParameter("content");
            postService.create(userId, content);
            request.setAttribute("successMessage", Messages.POST_ADDED);
            requestDispatcher.forward(request, response);
        } catch (CustomException customException) { 
            RequestDispatcher requestDispatcher = request
                                     .getRequestDispatcher("errorPage.jsp");
            request.setAttribute("error", customException.getMessage());
            requestDispatcher.forward(request, response);
        }
    } 
    
    /**
     * Updates the post 
     * 
     * @param postId - id of the post
     * @param content - content updated by the user
     * @return boolean - true or false based on the response
     */
    private void update(HttpServletRequest request,
                        HttpServletResponse response) throws IOException, 
                                                       ServletException {
        try {
            RequestDispatcher requestDispatcher = request
                                    .getRequestDispatcher("viewProfile");
            postService.update(request.getParameter("postId"), 
                                    request.getParameter("content"));
            requestDispatcher.forward(request, response);
        } catch (CustomException customException) {
            RequestDispatcher requestDispatcher = request
                                           .getRequestDispatcher("errorPage.jsp");
            request.setAttribute("error", customException.getMessage());
            requestDispatcher.forward(request, response);            
        }
    }

    /**
     * Gets the post uploaded by the user
     *
     * @return allPosts - all the post 
     */
    private void getUserPosts(HttpServletRequest request,
                              HttpServletResponse response) throws IOException, 
                                                             ServletException {
        try {
            List<Post> listOfPosts = null;
            RequestDispatcher requestDispatcher = request
                                    .getRequestDispatcher("newsFeed.jsp");
            listOfPosts = postService.getUserPosts();
            request.setAttribute("listOfPosts", listOfPosts);
            requestDispatcher.forward(request, response);
        } catch (CustomException customException) {
            RequestDispatcher requestDispatcher = request
                                     .getRequestDispatcher("errorPage.jsp");
            request.setAttribute("error", customException.getMessage());
            requestDispatcher.forward(request, response);
        }        
    }
    
    /**
     * Gets the post based on there userName 
     * 
     * @param  userId   - id of the user
     * @return userPosts - posts of the particular user
     */
    public List<Post> getPostOfParticularUser(String userId) throws CustomException {
        return postService.getPostOfParticularUser(userId);
    } 

    /**
     * Delete the post based on the postId 
     * 
     * @param  postId - id of the post
     * @return bolean - true or false based 
     */
    private void delete(HttpServletRequest request,
                        HttpServletResponse response)throws IOException,
                                                      ServletException {     
        try {   
            RequestDispatcher requestDispatcher = request
                                   .getRequestDispatcher("viewProfile");
            postService.delete(request.getParameter("postId"));
            requestDispatcher.forward(request, response);
        } catch (CustomException customException) {
            RequestDispatcher requestDispatcher = request
                                    .getRequestDispatcher("errorPage.jsp");
            request.setAttribute("error", customException.getMessage());
            requestDispatcher.forward(request, response);            
        }
    }
    
    private void getPost(HttpServletRequest request,
                         HttpServletResponse response) throws  IOException,
                                                         ServletException {
        try {
            request.setAttribute("post", postService
                                    .getPost(request.getParameter("postId")));
            RequestDispatcher requestDispatcher = request
                                           .getRequestDispatcher("addPost.jsp");
            requestDispatcher.forward(request, response);
        } catch (CustomException customException) {
            RequestDispatcher requestDispatcher = request
                                     .getRequestDispatcher("errorPage.jsp");
            request.setAttribute("error", customException.getMessage());    
            requestDispatcher.forward(request, response);        
        }
    }
    
}
 