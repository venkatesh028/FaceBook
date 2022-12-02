package com.ideas2it.controller;

import java.io.IOException;
import java.util.List;

import com.ideas2it.model.Like;
import com.ideas2it.service.LikeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ideas2it.logger.CustomLogger;

/**
 * Implements the logic of  add and get operations for the like
 *
 * @version 1.0 03-NOV-2022
 * @author Venkatesh TM
 */
public class LikeController extends HttpServlet {
    
    LikeService likeService;
    private CustomLogger logger;

    public LikeController() {
        likeService = new LikeService();
        logger = new CustomLogger(LikeController.class);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
              throws ServletException, IOException {

        String path = request.getServletPath();
        HttpSession session = request.getSession();
        Like like = new Like();
        like.setLikdeUserId((String) session.getAttribute("userId"));
        like.setPostId(request.getParameter("postId"));
        likeService.addLike(like);

        RequestDispatcher requestDispatcher = null;

        if (path == "/profileAddLike") {
            requestDispatcher = request.getRequestDispatcher("viewProfile");            
        } else { 
            requestDispatcher = request.getRequestDispatcher("newsFeed");
        }   
        requestDispatcher.forward(request, response);
    }
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
       
        if (path == "/likedUsers") {
            request.setAttribute("root", "newsFeed");
    
        } else {
             request.setAttribute("root","profilePage");
        }

        request.setAttribute("likedUsers", getLikedUserNames(request.getParameter("postId")));        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("likedUsersPage.jsp");
         
        requestDispatcher.forward(request, response);       
    }


    
    /**
     * Gets the liked user names
     *
     * @param postId - id of the post
     * @return listOfLikedUser - list of liked user names
     */
    public List<String> getLikedUserNames(String postId) {
        return likeService.getLikedUserNames(postId);
    } 
}