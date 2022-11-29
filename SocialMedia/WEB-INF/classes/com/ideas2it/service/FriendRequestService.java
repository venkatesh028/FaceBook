package com.ideas2it.service;

import java.util.Set;
import java.util.HashSet;

import com.ideas2it.model.FriendRequest;
import com.ideas2it.model.Notification;
import com.ideas2it.dao.daoImpl.FriendRequestDaoImpl;
import com.ideas2it.dao.FriendRequestDao;

/**
 * It Implements the logic of create, delete, update, and get operations for the friendrequest
 *
 * @version 1.0 04-NOV-2022
 * @author Venkatesh TM
 */
import java.util.UUID;
public class FriendRequestService {
     
    FriendRequestDao friendRequestDao;
    NotificationService notificationService;

    public FriendRequestService() {
        friendRequestDao = new FriendRequestDaoImpl();
        notificationService = new NotificationService();
    }
    
    /**
     * Creates the id and set in the friendrequest
     * 
     * @param friendRequest - details of the frien request
     * @return isCreated - true or false based on the response
     */       
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
    
    /**
     * Updates the friendRequest
     *
     * @param friendRequest - updated details of the friendRequest
     * @return isUpdated - true or false based on the response
     */
    public boolean update(FriendRequest friendRequest) {
        boolean isUpdated;
        isUpdated = friendRequestDao.update(friendRequest) > 0;
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
        Set<String> friends = friendRequestDao.getFriends(userId);
       
        if (null != friends) {
            friends.remove(userId);
        } 
        
        return friendRequestDao.getFriendsName(friends);                        
    }
  
}