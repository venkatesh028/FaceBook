package com.ideas2it.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import com.ideas2it.model.FriendRequest;
import com.ideas2it.model.Profile;
import com.ideas2it.service.FriendRequestService;
import com.ideas2it.service.serviceimpl.FriendRequestServiceImpl;
import com.ideas2it.exception.CustomException;
import com.ideas2it.logger.CustomLogger;

/**
 * It implements the logic of create, update, delete, 
 * get operations for the friend request
 *
 * @version 1.0 03-Nov-2022
 * @author Venkatesh TM
 */
public class FriendRequestController extends HttpServlet{
    FriendRequestService friendRequestService;
    CustomLogger logger;

    public FriendRequestController() {
        friendRequestService = new FriendRequestServiceImpl();
        logger = new CustomLogger(UserController.class);
    }
    
    protected void doGet(HttpServletRequest request, 
                         HttpServletResponse response) throws IOException,
                                                        ServletException {
        String path = request.getServletPath();
        
        switch (path) {
        case "/getfriends":
            getFriends(request, response);
            break;

        case "/add-friend":
            create(request, response);
            break;

        case "/get-request":
            getRequest(request, response);
            break;
        
        case "/update-request":
            updateTheRequest(request, response);
        }        
    }
    
    /**
     * Creates the friendRequest
     *
     * @param friendRequest - details of the friendRequest 
     * @return boolean  - true or false based on the response
     */
    private void create(HttpServletRequest request,
                        HttpServletResponse response)throws IOException,
                                                      ServletException {
        HttpSession session = request.getSession();
        String requestedUserId = (String) session.getAttribute("userId");
        FriendRequest friendRequest = new FriendRequest(request.getParameter("searchedProfileId"),
                                                        requestedUserId); 
        friendRequestService.create(friendRequest);
        response.sendRedirect("newsFeed");
    }
    
    /**
     * Updates the request status 
     * 
     * @param request
     * @param response
     */
    private void updateTheRequest(HttpServletRequest request,
                                  HttpServletResponse response) 
                                      throws IOException, ServletException {
        try {
            friendRequestService.update(request.getParameter("requestId"),
                                        request.getParameter("requestStatus"));
            response.sendRedirect("notification");
        } catch (CustomException customException) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
            request.setAttribute("error", customException.getMessage());
            requestDispatcher.forward(request, response);
        }
    }
    
    /**
     * Gets the friend request based on the id
     * 
     * @param request
     * @param response
     */
    private void getRequest(HttpServletRequest request,
                            HttpServletResponse response) throws IOException,
                                                           ServletException {
        
        try {
            Profile requestedProfile = friendRequestService.getRequestedProfile(request.getParameter("requestId"));    
            FriendRequest friendRequest = friendRequestService.get(request.getParameter("requestId"));   
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("friendRequest.jsp");        
            request.setAttribute("friendRequest", friendRequest);
            request.setAttribute("profile", requestedProfile);
            requestDispatcher.forward(request, response); 
        } catch (CustomException customException) { 
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
            request.setAttribute("error", customException.getMessage());
            requestDispatcher.forward(request, response);
        }              
    }
    
    /**
     * Gets Friends of the paritcular user based on the id 
     * 
     * @param request
     * @param response
     */
    private void getFriends(HttpServletRequest request,
                            HttpServletResponse response) throws IOException,
                                                           ServletException {
        HttpSession session = request.getSession();
        RequestDispatcher requestDispatcher = request
                                    .getRequestDispatcher("friends.jsp"); 
        request.setAttribute("friends", friendRequestService
                        .getFriends((String) session.getAttribute("userId")));
        requestDispatcher.forward(request, response);         
    }        
}