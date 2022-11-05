package com.ideas2it.dao;

import java.util.List;

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
    public int create(Notification notification);
    
    public int update(String requestId);
     
    public int delete(String requestId);
    
    /**
     * Get the requests given to the user based on the userName
     * 
     * @param userName userName of the user
     */
    public List<Notification> getNotifications(String userId);    
    
 
}