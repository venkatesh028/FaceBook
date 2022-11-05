package com.ideas2it.controller;

import java.util.List;

import com.ideas2it.model.Friend;
import com.ideas2it.service.FriendService;


public class FriendController {
    private FriendService friendService;

    public FriendController() {
        friendService = new FriendService();
    }
    
    public boolean create(Friend friend) {
        return friendService.create(friend);
    }
    
    public boolean delete(Friend friend) {
        return friendService.delete(friend);
    }
    
    public List<String> getFriends(String userId) {  
        return friendService.getFriends(userId);
    }
}