package com.ideas2it.service;

import java.util.List;

import com.ideas2it.model.FriendRequest;
import com.ideas2it.exception.CustomException;

/**
 * It Implements the logic of create, delete, update, 
 * and get operations for the friend request
 *
 * @version 1.0 09-DEC-2022
 * @author Venkatesh TM
 */
public interface FriendRequestService {

    /**
     * Creates the id and set in the friendrequest
     * 
     * @param friendRequest - details of the friend request
     * @return isCreated - true or false based on the response
     */     
    public boolean create(FriendRequest friendRequest);

    /**
     * Updates the friendRequest
     *
     * @param requestId - id of the friendRequest 
     * @param requestStatus - status for the friendRequest 
     * @return isUpdated - true or false based on the response
     */
    public boolean update(String requestId, String requestStatus);

    /**
     * Deletes the friendRequest
     * 
     * @param friendRequest - details of the friendRequest 
     * @return boolean - true or false based on the result 
     */
    public boolean delete(FriendRequest friendRequest);

    /**
     * Gets the friendrequest 
     * 
     * @param requestId - id of the request
     * @return friendRequest - details of the friendRequest
     */
    public FriendRequest get(String requestId);
    
    /**
     * Get All the friends of the user based on the userId 
     * 
     * @param userId - id of the user 
     * @return friends - list of friends 
     */
    public List<String> getFriends(String userId);
}