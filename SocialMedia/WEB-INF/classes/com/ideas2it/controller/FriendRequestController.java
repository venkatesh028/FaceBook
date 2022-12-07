package com.ideas2it.controller;

import java.io.IOException; 

import com.ideas2it.model.FriendRequest;
import com.ideas2it.service.FriendRequestService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

/**
 * It implements the logic of create, update, delete, 
 * get operations for the friend request
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
                         HttpServletResponse response) throws IOException,
                                                        ServletException {
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
    private boolean create(FriendRequest friendRequest) {
        return friendRequestService.create(friendRequest);
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
        friendRequestService.update(request.getParameter("requestId"),
                                    request.getParameter("requestStatus"));
    }
    
    /**
     * Gets the friend request based on the id
     * 
     * @param requestId - id of the friendrequest
     * @return friendRequest - details of the friendRequest
     */
    private FriendRequest get(String requestId) {
        return friendRequestService.get(requestId);
    }
    
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