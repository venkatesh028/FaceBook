package com.ideas2it.controller;

import java.util.Set;
import com.ideas2it.service.NotificationService;
import com.ideas2it.model.Notification;

import java.util.List;

/**
 * Implements the create, update, get and delete operations for the Notification
 * 
 * @version 1.0 05-OCT-2022
 * @author  Venkatesh TM
 */
public class NotificationController {    
    private NotificationService notificationService;
    
    public NotificationController() {
        this.notificationService = new NotificationService();
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
     * @param id - id of the notification
     * @param isDeleted - true or false based on the result
     */
    public List<Notification> getNotifications(String userId) {
        return notificationService.getNotifications(userId);
    }      
}