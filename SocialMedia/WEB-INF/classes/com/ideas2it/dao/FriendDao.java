package com.ideas2it.dao;

import java.util.List;

import com.ideas2it.model.Friend;

/** 
 * It is interface for the friend dao impl it contains the method for
 * create, delete, get operations for friend
 *
 * @version 1.0 01-Nov-2022
 * @author Venkatesh TM
 */
public interface FriendDao {
    
    /**
     * Creates the friend
     * 
     * @param friend - details of friend
     * @return noOfRowsAffected - number of rows affected  
     */
    public int create(Friend friend);
     
    /**
     * Deletes the friend
     * 
     * @param friend - details of friend
     * @return noOfRowsDeleted - number of rows Deleted
     */
    public int delete(Friend friend);
    
    /**
     * Gets the friends list
     * 
     * @param userId - id of the user
     * @return listOfFriends - list of friends
     */
    public List<String> getFriends(String userId);
}