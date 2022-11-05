package com.ideas2it.service;

import com.ideas2it.model.FriendRequest;
import com.ideas2it.model.Notification;
import com.ideas2it.dao.daoImpl.FriendRequestDaoImpl;
import com.ideas2it.dao.FriendRequestDao;

import java.util.UUID;
public class FriendRequestService {
     
    FriendRequestDao friendRequestDao;
    NotificationService notificationService;

    public FriendRequestService() {
        friendRequestDao = new FriendRequestDaoImpl();
        notificationService = new NotificationService();
    }

    public boolean create(FriendRequest friendRequest) {
        String id;
        boolean isCreated;
        Notification notification = new Notification();
        id = UUID.randomUUID().toString();
        friendRequest.setId(id);
        isCreated = friendRequestDao.create(friendRequest) > 0;
        notification.setRequestId(id);
        if (isCreated) {
            notificationService.create(notification);
        }
        return isCreated;    
    }
    
    public boolean update(FriendRequest friendRequest) {
        boolean isUpdated;
        isUpdated = friendRequestDao.update(friendRequest) > 0;
        return isUpdated;
    } 
    
    public boolean delete(FriendRequest friendRequest) {
        /*boolean isDeleted;
        isDeleted = friendRequestDao.delete(friendRequest) > 0; */
        return true;
    }
    
    public FriendRequest get(String requestId) {
        return friendRequestDao.get(requestId);  
    }
}