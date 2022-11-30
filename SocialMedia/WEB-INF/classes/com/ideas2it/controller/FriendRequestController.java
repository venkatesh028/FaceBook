package com.ideas2it.controller;

import com.ideas2it.service.FriendRequestService;
import com.ideas2it.model.FriendRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * It implements the logic of create, update, delete, get operations for the friend request
 *
 * @version 1.0 03-Nov-2022
 * @author Venkatesh TM
 */
public class FriendRequestController extends HttpServlet{
    FriendRequestService friendRequestService;

    public FriendRequestController() {
        friendRequestService = new FriendRequestService();    
    }
    
    protected void doGet(HttpServletRequest request, 
                         HttpServletResponse response)
              throws ServletException, IOException {
        String path = request.getServletPath();
        
        switch (path) {
        case "/getfriends":
            getFriends(request, response);
            break;
            
        }
        
    }
    
    /**
     * Creates the friendRequest
     *
     * @param friendRequest - details of the friendRequest 
     * @return boolean  - true or false based on the response
     */
    public boolean create(FriendRequest friendRequest) {
        return friendRequestService.create(friendRequest);
    }
    
    /**
     * Updates the request status 
     * 
     * @param request
     * @param response
     */
    public void updateTheRequest(HttpServletRequest request,
                                 HttpServletResponse response)
           throws ServletException, IOException { 

        friendRequestService.update(request.getParameter("requestId"),
                                    request.getParameter("requestStatus"));
    }
    
    /**
     * Gets the friend request based on the id
     * 
     * @param requestId - id of the friendrequest
     * @return friendRequest - details of the friendRequest
     */
    public FriendRequest get(String requestId) {
        return friendRequestService.get(requestId);
    }
    
    public void getFriends(HttpServletRequest request,
                           HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("friends.jsp"); 
        request.setAttribute("friends", friendRequestService.getFriends((String) session.getAttribute("userId")));   
        requestDispatcher.forward(request, response);         
    }        
}