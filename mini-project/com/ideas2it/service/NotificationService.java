package com.ideas2it.service;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import com.ideas2it.dao.NotificationDao;
import com.ideas2it.dao.daoImpl.NotificationDaoImpl;
import com.ideas2it.model.Notification;

/**
 * Implements the create, update, get and delete operations for the Notification
 * 
 * @version 1.0 05-OCT-2022
 * @author  Venkatesh TM
 */
public class NotificationService {
    private NotificationDao notificationDao;
    
    public NotificationService () {
        this.notificationDao = new NotificationDaoImpl();
    }
    
    /** 
     * creates the notification 
     *
     * @param  notification - details of the notification
     * @return isCreated  - true or false based on result;
     */     
    public boolean create(Notification notification) {
        String id;
        boolean isCreated;
        id = UUID.randomUUID().toString();
        notification.setId(id);
        isCreated = notificationDao.create(notification) > 0;
        return isCreated;
    }
    
    /**
     * Gets the notifications of the particular user
     * 
     * @param  userId - id of the user
     * @return listOfNotifications all the notifications based on the user
     */
    public List<Notification> getNotifications(String userId) {
        return notificationDao.getNotifications(userId);
    }
    
    /**
     * Updates the notification 
     * 
     * @param id - id of the notification
     * @return isUpdated - true or false based on the result
     */     
    public boolean update(String id) {
        boolean isUpdated;
        isUpdated = notificationDao.update(id) > 0;
        return isUpdated;
    }
    
    /**
     * Clears the notification based on the id
     * 
     * @param id - id of the notification
     * @param isDeleted - true or false based on the result
     */
    public boolean clearNotification(String id) {
        boolean isDeleted;
        isDeleted = notificationDao.delete(id) > 0;
        return true;        
    }
}