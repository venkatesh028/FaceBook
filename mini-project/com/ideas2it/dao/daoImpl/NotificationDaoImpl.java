package com.ideas2it.dao.daoImpl;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import com.ideas2it.dao.NotificationDao;
import com.ideas2it.model.Notification;

public class NotificationDaoImpl implements NotificationDao  {
    private static Map<String, Set<Notification>> userNotifications;

    static {
        userNotifications = new HashMap<>(); 
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addNotification(String userName, Notification notification) {
        Set<Notification> notifications; 

        if (userNotifications.containsKey(userName)) {
            notifications = userNotifications.get(userName);
            notifications.add(notification);        
        } else {
            notifications = new HashSet<>();
            notifications.add(notification); 
        }
        userNotifications.put(userName, notifications);
        return true;  
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Notification> getNotifications(String userName) {
        return userNotifications.get(userName);
    }
    
   
}