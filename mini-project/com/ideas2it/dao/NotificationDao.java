package com.ideas2it.dao;

import java.util.List;

import com.ideas2it.model.Notification;

/**
 * Add, update, delete and get the requests notification for the user 
 * 
 * @Version 1.0 06-OCT-2022
 * @author Venkatesh TM
 */ 
public interface NotificationDao {
    
    /**
     * Add the request notification for the particular user
     * 
     * @param  userNam - userName of the user
     * @param  requestedUser - name of the user who gave the request
     * @return noOfRowsAffected - number of rows affected based on creation
     */     
    public int create(Notification notification);
    
    /**
     * udpates the notification
     *
     * @param id - id of the notification
     * @return noOfRowsUpdated - number of the rows updated
     */
    public int update(String id);
    
    /**
     * Deletes the notification 
     * 
     * @param requestId - id of the notification 
     */ 
    public int delete(String id);
    
    /**
     * Get the requests given to the user based on the userName
     * 
     * @param userName userName of the user
     */
    public List<Notification> getNotifications(String userId);    
    
 
}