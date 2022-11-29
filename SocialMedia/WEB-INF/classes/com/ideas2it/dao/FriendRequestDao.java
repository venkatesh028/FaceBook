package com.ideas2it.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import com.ideas2it.logger.CustomLogger;
import com.ideas2it.connection.DatabaseConnection;
import com.ideas2it.model.FriendRequest;

/**
 * It is interface for the friendrequest dao impl it contains the method for
 * create, update, get operations for friendrequest
 * 
 * @version 1.0 04-NOV-2022
 * @author Venkatesh TM
 */
public interface FriendRequestDao {
    
    /**
     * Creates the friendRequest
     * 
     * @param friendRequest - details of the friendRequest
     * @return noOfRowsAffected - number of rows affected based on the creation
     */
    public int create(FriendRequest friendRequest);
    
    /**
     * Updates the friendrequest 
     * 
     * @param friendRequest - details of the friendRequest
     * @return noOfRowsUpdated - number of rows affected based on the updation
     */
    public int update(FriendRequest friendRequest);
    
    /**
     * Gets the friendrequest 
     *  
     * @param id - id of the request
     * @return friendRequest - details of the friendRequest 
     */
    public FriendRequest get(String requestId);
    
    /**
     * Gets the Friends for the user
     * 
     * @param userId - id of the user 
     * @return friends - list of friends
     */
    public Set<String> getFriends(String userId);
}