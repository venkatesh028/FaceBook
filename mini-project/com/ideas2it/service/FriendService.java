package com.ideas2it.service;

import java.util.UUID;
import java.util.List;

import com.ideas2it.model.Friend;
import com.ideas2it.dao.FriendDao;
import com.ideas2it.dao.daoImpl.FriendDaoImpl;

public class FriendService {
    
    FriendDao friendDao;
    ProfileService profileService;

    public FriendService() {
        friendDao = new FriendDaoImpl();
        profileService = new ProfileService();
    }
     
    public boolean create(Friend friend){
        String id;
        boolean isCreated;
        id = UUID.randomUUID().toString();
        friend.setId(id);
        isCreated = friendDao.create(friend) > 0;
        profileService.increaseFriendCount(friend.getUserId());
        return isCreated;
    }
    
    public boolean delete(Friend friend) {
        boolean isDeleted;
        isDeleted = friendDao.delete(friend) > 0;
        profileService.decreaseFriendCount(friend.getUserId());
        return isDeleted; 
    }
   
    public List<String> getFriends(String userId) {
        return friendDao.getFriends(userId);
    }
}