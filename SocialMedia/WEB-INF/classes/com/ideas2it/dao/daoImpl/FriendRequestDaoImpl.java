package com.ideas2it.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.ideas2it.model.FriendRequest;
import com.ideas2it.dao.FriendRequestDao;
import com.ideas2it.connection.DatabaseConnection;
import com.ideas2it.logger.CustomLogger;

/**
 * Performs the Create, get, Update, delete operations for the FriendRequest 
 * 
 * @version 1.0 04-NOV-2022
 * @author Venkatesh TM
 */
public class FriendRequestDaoImpl implements FriendRequestDao {
    private CustomLogger logger;
    Connection connection;     
    PreparedStatement statement;
    
    public FriendRequestDaoImpl() {
        this.logger = new CustomLogger(FriendRequestDaoImpl.class);
    }
   
    /**
     * {@inheritDoc}
     */
    @Override
    public int create(FriendRequest friendRequest) {
        int noOfRowsAffected = 0;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO friend_request ")
             .append("(id, user_id, requested_user_id, created_date_time)")
             .append(" VALUES(?, ?, ?, now());");

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, friendRequest.getId());
            statement.setString(2, friendRequest.getUserId());
            statement.setString(3, friendRequest.getRequestedUserId());
            noOfRowsAffected = statement.executeUpdate();
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return noOfRowsAffected;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(FriendRequest friendRequest) {
        int noOfRowsUpdated = 0;
        StringBuilder query = new StringBuilder();
        query.append("UPDATE friend_request SET ")
             .append("request_status = ?, updated_date_time = now()")
             .append(" WHERE id = ?;");

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, friendRequest.getStatus());
            statement.setString(2, friendRequest.getId());
            noOfRowsUpdated = statement.executeUpdate();
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return noOfRowsUpdated;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public FriendRequest get(String requestId) {
        ResultSet resultSet;
        FriendRequest friendRequest = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT id, requested_user_id, request_status")
             .append(" FROM friend_request WHERE id = ?;");
        
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, requestId);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                friendRequest = new FriendRequest();
                friendRequest.setId(resultSet.getString("id"));
                friendRequest.setRequestedUserId(resultSet
                                     .getString("requested_user_id"));
                friendRequest.setStatus(resultSet
                                     .getString("request_status"));
            }
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return friendRequest;
    } 
   
    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getFriends(String userId) {
        ResultSet resultSet;
        List<String> friends = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT username AS friends FROM profile")
             .append(" WHERE profile.user_id IN")
             .append(" (SELECT (CASE WHEN user_id = ? THEN")
             .append(" requested_user_id WHEN requested_user_id = ?")
             .append(" THEN user_Id END) FROM friend_request")
             .append(" WHERE request_status = 'accepted');"); 
        
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, userId);
            statement.setString(2, userId); 
            resultSet = statement.executeQuery();            
            friends = new ArrayList<>();

            while(resultSet.next()) {
                friends.add(resultSet.getString("friends"));             
            }
            logger.info(friends.toString());
            statement.close();
        } catch (SQLException sqlException) { 
            logger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return friends;        
    }  
}