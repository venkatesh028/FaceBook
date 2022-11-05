package com.ideas2it.controller;

import com.ideas2it.service.FriendRequestService;
import com.ideas2it.model.FriendRequest;

public class FriendRequestController {
    FriendRequestService friendRequestService;

    public FriendRequestController() {
        friendRequestService = new FriendRequestService();    
    }
    
    public boolean create(FriendRequest friendRequest) {
        return friendRequestService.create(friendRequest);
    }
    
    public boolean update(FriendRequest friendRequest) {
        return friendRequestService.update(friendRequest);
    }
    
    public boolean delete(FriendRequest friendRequest) {
        return friendRequestService.delete(friendRequest);
    }
    
    public FriendRequest get(String requestId) {
        return friendRequestService.get(requestId);
    }
}