package com.ideas2it.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ideas2it.logger.CustomLogger;
import com.ideas2it.connection.DatabaseConnection;
import com.ideas2it.model.FriendRequest;

public interface FriendRequestDao {

    public int create(FriendRequest friendRequest);
    public int update(FriendRequest friendRequest);
    
    public FriendRequest get(String requestId);
}