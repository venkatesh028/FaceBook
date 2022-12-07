package com.ideas2it.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ideas2it.model.FriendRequest;
import com.ideas2it.model.Notification;
import com.ideas2it.dao.daoImpl.FriendRequestDaoImpl;
import com.ideas2it.dao.FriendRequestDao;

/**
 * It Implements the logic of create, delete, update, 
 * and get operations for the friend request
 *
 * @version 1.0 04-NOV-2022
 * @author Venkatesh TM
 */
public class FriendRequestService {
    FriendRequestDao friendRequestDao;
    NotificationService notificationService;
    ProfileService profileService;

    public FriendRequestService() {
        friendRequestDao = new FriendRequestDaoImpl();
        notificationService = new NotificationService();
        profileService = new ProfileService();
    }
    
    /**
     * Creates the id and set in the friendrequest
     * 
     * @param friendRequest - details of the frien request
     * @return isCreated - true or false based on the response
     */       
    public boolean create(FriendRequest friendRequest) {
        Notification notification = new Notification();
        String id = UUID.randomUUID().toString();
        friendRequest.setId(id);
        boolean isCreated = friendRequestDao.create(friendRequest) > 0;
        notification.setRequestId(id);

        if (isCreated) {
            notificationService.create(notification);
        }
        return isCreated;    
    }
    
    /**
     * Updates the friendRequest
     *
     * @param requestId - id of the friendRequest 
     * @param requestStatus - status for the friendRequest 
     * @return isUpdated - true or false based on the response
     */
    public boolean update(String requestId, String requestStatus) {
        boolean isUpdated; 
        FriendRequest friendRequest = get(requestId);
        friendRequest.setStatus(requestStatus);
        isUpdated = friendRequestDao.update(friendRequest) > 0;
        profileService.updateFriendCount(friendRequest.getUserId(), 
                             getFriends(friendRequest.getUserId()).size());
        profileService.updateFriendCount(friendRequest.getRequestedUserId(), 
                             getFriends(friendRequest.getRequestedUserId()).size());
        return isUpdated;
    } 
    
    /**
     * Deletes the friendRequest
     * 
     * @param friendRequest - details of the friendRequest 
     * @return boolean - true or false based on the result 
     */
    public boolean delete(FriendRequest friendRequest) {
        /*boolean isDeleted;
        isDeleted = friendRequestDao.delete(friendRequest) > 0; */
        return true;
    }
     
    /**
     * Gets the friendrequest 
     * 
     * @param requestId - id of the request
     * @return friendRequest - details of the friendRequest
     */
    public FriendRequest get(String requestId) {
        return friendRequestDao.get(requestId);  
    }
   
    public List<String> getFriends(String userId) {
        return friendRequestDao.getFriends(userId);                     
    }
  
}