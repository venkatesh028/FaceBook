package com.ideas2it.service;

import java.util.List;

import com.ideas2it.model.Notification;

/**
 * Implements the create, update, get 
 * and delete operations for the Notification
 * 
 * @version 1.0 09-DEC-2022
 * @author  Venkatesh TM
 */
public interface NotificationService {

    /** 
     * creates the notification 
     *
     * @param  notification - details of the notification
     * @return isCreated  - true or false based on result;
     */     
    public boolean create(Notification notification);

    /**
     * Gets the notifications of the particular user
     * 
     * @param  userId - id of the user
     * @return listOfNotifications all the notifications based on the user
     */
    public List<Notification> getNotifications(String userId);

    /**
     * Updates the notification 
     * 
     * @param id - id of the notification
     * @return isUpdated - true or false based on the result
     */     
    public boolean update(String id);

    /**
     * Clears the notification based on the id
     * 
     * @param id - id of the notification
     * @param isDeleted - true or false based on the result
     */
    public boolean clearNotification(String id);
}