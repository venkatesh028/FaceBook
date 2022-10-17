package com.ideas2it.service;

import java.util.Set;
import java.util.HashSet;

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
    public boolean addNotification(String userName, Notification notification) {
        return notificationDao.addNotification(userName, notification);
    }
    
    /**
     * Get the requests of the particular user
     * 
     * @param  userName userName of the user
     * @return requests all the requests based on the user
     */
    public Set<Notification> getNotifications(String userName) {
        return notificationDao.getNotifications(userName);
    }
    
    public boolean clearNotification(String userName, String requestedUserName) {
        Set<Notification> notifications = getNotifications(userName);
        
        for (Notification notification : notifications) {

            if (notification.getUserName().equals(requestedUserName)) {
                notifications.remove(notification);
                break;
            }   
        }
        return true;        
    }
}