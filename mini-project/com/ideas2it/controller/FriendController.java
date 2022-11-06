package com.ideas2it.controller;

import java.util.List;

import com.ideas2it.model.Friend;
import com.ideas2it.service.FriendService;

/**
 * It performs the create, delete and get operation for Friend
 *
 * @version  1.0 05-Nov-2022
 * @author Venkatesh TM
 */
public class FriendController {
    private FriendService friendService;

    public FriendController() {
        friendService = new FriendService();
    }
    
    /**
     * Creates Friend 
     * 
     * @param friend - details of friend
     * @return boolean - true or false based on the response
     */
    public boolean create(Friend friend) {
        return friendService.create(friend);
    }
    
    /**
     * Deletes the friend
     * 
     * @param friend - details of the friend
     * @return boolean - true or false based on the response
     */
    public boolean delete(Friend friend) {
        return friendService.delete(friend);
    }
    
    /**
     * Gets the Friends of the user 
     * 
     * @param userId - id of the user
     * @return lisOfFriends - list of friends of the particuar user
     */
    public List<String> getFriends(String userId) {  
        return friendService.getFriends(userId);
    }
}