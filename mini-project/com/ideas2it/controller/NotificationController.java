package com.ideas2it.controller;

import java.util.Set;
import com.ideas2it.service.NotificationService;
import com.ideas2it.model.Notification;

import java.util.List;

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
    
    public boolean create(Notification notification) {
        return notificationService.create(notification);
    }
    
    public boolean update(String requestId) {
        return notificationService.update(requestId);
    }
    
    public boolean clearNotification(String id) {
        return notificationService.clearNotification(id);
    }  
    
    public List<Notification> getNotifications(String userId) {
        return notificationService.getNotifications(userId);
    }   
    
}