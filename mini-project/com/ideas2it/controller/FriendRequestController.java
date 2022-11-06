package com.ideas2it.controller;

import com.ideas2it.service.FriendRequestService;
import com.ideas2it.model.FriendRequest;

/**
 * It performs the create, update, delete, get operations for the friend request
 *
 * @version 1.0 03-Nov-2022
 * @author Venkatesh TM
 */
public class FriendRequestController {
    FriendRequestService friendRequestService;

    public FriendRequestController() {
        friendRequestService = new FriendRequestService();    
    }
    
    /**
     * Creates the friendRequest
     *
     * @param friendRequest - details of the friendRequest 
     * @return boolean  - true or false based on the response
     */
    public boolean create(FriendRequest friendRequest) {
        return friendRequestService.create(friendRequest);
    }
    
    /**
     * Updates the friendRequest
     *
     * @param friendRequest - details of the friendRequest 
     * @return boolean  - true or false based on the response
     */
    public boolean update(FriendRequest friendRequest) {
        return friendRequestService.update(friendRequest);
    }
    
    /**
     * Deletes the friendRequest
     *
     * @param friendRequest - details of the friendRequest 
     * @return boolean  - true or false based on the response
     */
    public boolean delete(FriendRequest friendRequest) {
        return friendRequestService.delete(friendRequest);
    }
    
    /**
     * Gets the friend request based on the id
     * 
     * @param requestId - id of the friendrequest
     * @return friendRequest - details of the friendRequest
     */
    public FriendRequest get(String requestId) {
        return friendRequestService.get(requestId);
    }
}