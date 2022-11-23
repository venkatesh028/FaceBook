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

/**
 * Implements the logic of  add and get operations for the like
 *
 * @version 1.0 03-NOV-2022
 * @author Venkatesh TM
 */
public class LikeController extends HttpServlet {
    
    LikeService likeService;
  
    public LikeController() {
        likeService = new LikeService();    
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {

            case "/addLike":
                HttpSession session = request.getSession();
                Like like = new Like();
                like.setLikdeUserId((String) session.getAttribute("userId"));
                like.setPostId(request.getParameter("postId"));
                addLike(like);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("newsFeed");
                requestDispatcher.forward(request, response);
                break;
        }
    }
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("likedUsers", getLikedUserNames(request.getParameter("postId")));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("likedUsersPage.jsp"); 
        requestDispatcher.forward(request, response);       
    }

    /**
     * Adds like
     * 
     * @param like - details of the like
     * @return boolean - true or false based on the response
     */
    public boolean addLike(Like like) { 
        return likeService.addLike(like);
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