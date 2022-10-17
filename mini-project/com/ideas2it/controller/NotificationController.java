package com.ideas2it.controller;

import java.util.Set;
import com.ideas2it.service.NotificationService;
import com.ideas2it.model.Notification;

/**
 * Add the requests of the user and shows to the user
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
     * Add the requests of the particular user with requested user details
     *
     * @param userName      userName of the user for who the request is given
     * @param requestedUser the user who gave the request
     */ 
    public boolean addNotification(String userName, Notification notification) {         
        return notificationService.addNotification(userName, notification);    
    } 
    
    /**
     * Get the requests of the particular user
     * 
     * @param  userName userName of the user
     * @return requests all the requests based on the user
     */
    public Set<Notification> getNotifications(String userName) {
        return notificationService.getNotifications(userName);
    }
    
    /**
     * Clear the request of the user based on the userName
     *
     * @param  userName username of the user whose request need to removed
     * @return boolean  true or false based on the result
     */
    public boolean clearNotification(String userName, String requestedUserName) {
        return notificationService.clearNotification(userName, requestedUserName);
    }
}