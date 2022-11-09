package com.ideas2it.service;

import java.util.UUID;
import java.util.List;

import com.ideas2it.model.Friend;
import com.ideas2it.dao.FriendDao;
import com.ideas2it.dao.daoImpl.FriendDaoImpl;
import com.ideas2it.exception.CustomException;
import com.ideas2it.constant.Constants;

/**
 * It implements the logic of create, delete and get operation for friend
 * 
 * @version  1.0 05-Nov-2022
 * @author Venkatesh TM
 */
public class FriendService {
    
    FriendDao friendDao;
    ProfileService profileService;

    public FriendService() {
        friendDao = new FriendDaoImpl();
        profileService = new ProfileService();
    }
     
    /**
     * Creates the friend id and set in the friend 
     *
     * @param friend - details of the friend
     * @return isCreated - true or false based on the result
     */
    public boolean create(Friend friend){
        String id;
        boolean isCreated;
        id = UUID.randomUUID().toString();
        friend.setId(id);
        isCreated = friendDao.create(friend) > 0;
        profileService.increaseFriendCount(friend.getUserId());
        return isCreated;
    }
    
    /**
     * Deletes the friend 
     *
     * @param friend - details of the friend 
     * @return isDeleted - true or false based on the result
     */
    public boolean delete(Friend friend) {
        boolean isDeleted;
        isDeleted = friendDao.delete(friend) > 0;
        profileService.decreaseFriendCount(friend.getUserId());
        return isDeleted; 
    }
   
    /**
     * Gets the list of friends
     * 
     * @param userId - id of the user
     * @return listOfFriends - username of the friends
     */
    public List<String> getFriends(String userId) throws CustomException {
        List<String> friends = friendDao.getFriends(userId);
        
        if (friends.isEmpty()) {
            throw new CustomException(Constants.ERRORR_04);
        } 
        return friends;
    }
}