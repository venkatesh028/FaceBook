package com.ideas2it.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.ideas2it.model.Notification;
import com.ideas2it.service.NotificationService;
import com.ideas2it.service.serviceimpl.NotificationServiceImpl;
import com.ideas2it.logger.CustomLogger;

/**
 * Implements the create, update, get and delete operations for the Notification
 * 
 * @version 1.0 05-OCT-2022
 * @author  Venkatesh TM
 */
public class NotificationController extends HttpServlet {    
    private NotificationService notificationService;
    private CustomLogger logger;
        
    public NotificationController() {
        this.notificationService = new NotificationServiceImpl();
        this.logger = new CustomLogger(NotificationController.class);
    }

    
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException,
                                                        ServletException {
        String path = request.getServletPath();
        
        switch (path) {
        case "/notification":
            getNotifications(request, response); 
            break;
        }
            
    }
    
    /** 
     * Creates the notification 
     *
     * @param  notification - details of the notification
     * @return boolean  - true or false based on response;
     */     
    private boolean create(Notification notification) {
        return notificationService.create(notification);
    }
    
    /**
     * Updates the notification 
     * 
     * @param id - id of the notification
     * @return boolean - true or false based on the result
     */ 
    private boolean update(String requestId) {
        return notificationService.update(requestId);
    }
    
    /**
     * Clears the notification based on the responce
     * 
     * @param userName          - username to who the request is given
     * @param requestedUserName - name of the person who gave the request
     */
    private boolean clearNotification(String id) {
        return notificationService.clearNotification(id);
    }  
    
    /**
     * Gets the notifications of the particular user
     * 
     * @param request The request object is used to get the request parameters.
     * @param response This is the response object that is used to send data back to the client.
     */
    private void getNotifications(HttpServletRequest request,
                                  HttpServletResponse response)
                                       throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request
                                    .getRequestDispatcher("notification.jsp");
        HttpSession session = request.getSession();
        List<Notification> listOfNotifications = null;        
        listOfNotifications = notificationService
                          .getNotifications((String) session.getAttribute("userId"));
        request.setAttribute("listOfNotifications",listOfNotifications);
        logger.info(listOfNotifications.toString());
        requestDispatcher.forward(request, response);        
    }      
}