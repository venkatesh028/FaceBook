package com.ideas2it.dao;

import java.util.Set;

import com.ideas2it.model.Notification;

/**
 * Add and get the requests notification for the user 
 * 
 * @Version 1.0 06-OCT-2022
 * @author Venkatesh TM
 */ 
public interface NotificationDao {
    
    /**
     * Add the request notification for the particular user
     * 
     * @param  userNam       userName of the user
     * @param  requestedUser name of the user who gave the request
     * @return boolean       true after adding the request
     */     
    public boolean addNotification(String userName, Notification notification);
    
    /**
     * Get the requests given to the user based on the userName
     * 
     * @param userName userName of the user
     */
    public Set<Notification> getNotifications(String userName);    
    
}