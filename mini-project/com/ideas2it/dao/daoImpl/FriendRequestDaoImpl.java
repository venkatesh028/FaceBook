package com.ideas2it.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ideas2it.logger.CustomLogger;
import com.ideas2it.connection.DatabaseConnection;
import com.ideas2it.model.FriendRequest;
import com.ideas2it.dao.FriendRequestDao;

public class FriendRequestDaoImpl implements FriendRequestDao {
    private CustomLogger logger;
    Connection connection;  
    String query;    
    PreparedStatement statement;
    
    public FriendRequestDaoImpl() {
        this.logger = new CustomLogger(FriendRequestDaoImpl.class);
    }

    public int create(FriendRequest friendRequest) {
        int noOfRowsAffected = 0;
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "INSERT INTO friend_request (id, user_id, requested_user_id, created_date_time) VALUES(?,?,?,now());";
            statement = connection.prepareStatement(query);
            statement.setString(1,friendRequest.getId());
            statement.setString(2,friendRequest.getUserId());
            statement.setString(3,friendRequest.getRequestedUserId());
            noOfRowsAffected = statement.executeUpdate();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch(SQLException sqlException) {}
        }
        return noOfRowsAffected;
    }
    
    public int update(FriendRequest friendRequest) {
        int noOfRowsUpdated = 0;
    
        try {
            connection = DatabaseConnection.getConnection();
            query = "UPDATE friend_request SET request_status = ?, updated_date_time = now() WHERE id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1, friendRequest.getStatus());
            statement.setString(2, friendRequest.getId());
            noOfRowsUpdated = statement.executeUpdate();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch(SQLException sqlException) {}
        }
        return noOfRowsUpdated;
    }
    
    public FriendRequest get(String requestId) {
        ResultSet resultSet;
        FriendRequest friendRequest = null;
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "SELECT id, requested_user_id, request_status FROM friend_request WHERE id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1, requestId);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                friendRequest = new FriendRequest();
                friendRequest.setId(resultSet.getString("id"));
                friendRequest.setRequestedUserId(resultSet.getString("requested_user_id"));
                friendRequest.setStatus(resultSet.getString("request_status"));
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch(SQLException sqlException) {}
        }
        return friendRequest;
    }
}