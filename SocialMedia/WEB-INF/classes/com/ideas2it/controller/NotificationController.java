package com.ideas2it.controller;

import java.util.List;
import java.util.Set;
import com.ideas2it.service.NotificationService;
import com.ideas2it.model.Notification;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Implements the create, update, get and delete operations for the Notification
 * 
 * @version 1.0 05-OCT-2022
 * @author  Venkatesh TM
 */
public class NotificationController extends HttpServlet {    
    private NotificationService notificationService;
    
    public NotificationController() {
        this.notificationService = new NotificationService();
    }

    
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
              throws ServletException, IOException {
        getNotifications(request, response);     
    }
    
    /** 
     * Creates the notification 
     *
     * @param  notification - details of the notification
     * @return boolean  - true or false based on response;
     */     
    public boolean create(Notification notification) {
        return notificationService.create(notification);
    }
    
    /**
     * Updates the notification 
     * 
     * @param id - id of the notification
     * @return boolean - true or false based on the result
     */ 
    public boolean update(String requestId) {
        return notificationService.update(requestId);
    }
    
    /**
     * Clears the notification based on the responce
     * 
     * @param userName          - username to who the request is given
     * @param requestedUserName - name of the person who gave the request
     */
    public boolean clearNotification(String id) {
        return notificationService.clearNotification(id);
    }  
    
    /**
     * Gets the notifications of the particular user
     * 
     * @param request
     * @param response
     */
    public void getNotifications(HttpServletRequest request,
                                 HttpServletResponse response)
           throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("notification.jsp");
        HttpSession session = request.getSession();
        List<Notification> listOfNotifications = null;
        listOfNotifications = notificationService.getNotifications((String)session.getAttribute("userId")); 
        request.setAttribute("listOfNotifications",listOfNotifications);
        requestDispatcher.forward(request, response);        
    }      
}