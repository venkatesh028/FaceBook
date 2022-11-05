package com.ideas2it.service;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import com.ideas2it.dao.NotificationDao;
import com.ideas2it.dao.daoImpl.NotificationDaoImpl;
import com.ideas2it.model.Notification;

/**
 * Add the requests of the user and shows to the user
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
     * Add the requests of the particular user with requested user details
     *
     * @param userName      userName of the user for who the request is given
     * @param requestedUser the user who gave the request
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
     * Get the requests of the particular user
     * 
     * @param  userName userName of the user
     * @return requests all the requests based on the user
     */
    public List<Notification> getNotifications(String userId) {
        return notificationDao.getNotifications(userId);
    }
    
    public boolean update(String requestId) {
        boolean isUpdated;
        isUpdated = notificationDao.update(requestId) > 0;
        return isUpdated;
    }
    
    /**
     * Clears the request notification based on the responce
     * 
     * @param userName          - username to who the request is given
     * @param requestedUserName - name of the person who gave the request
     */
    public boolean clearNotification(String id) {
        boolean isDeleted;
        isDeleted = notificationDao.delete(id) > 0;
        return true;        
    }
}