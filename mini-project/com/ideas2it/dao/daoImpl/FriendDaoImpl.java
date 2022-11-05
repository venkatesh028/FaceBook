package com.ideas2it.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.ideas2it.logger.CustomLogger;
import com.ideas2it.model.Friend;
import com.ideas2it.connection.DatabaseConnection;
import com.ideas2it.dao.FriendDao;

public class FriendDaoImpl implements FriendDao {
    private CustomLogger logger;
    private Connection connection;  
    private String query;    
    private PreparedStatement statement;

    
    public FriendDaoImpl() {
        this.logger = new CustomLogger(FriendDaoImpl.class);
    }

    public int create(Friend friend) {
        int noOfRowsAffected = 0;
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "INSERT INTO friend(id, user_id, friend_id, created_date_time) VALUES(?,?,?,now());";
            statement = connection.prepareStatement(query);
            statement.setString(1,friend.getId());
            statement.setString(2,friend.getUserId());
            statement.setString(3,friend.getFriendId());
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
    
    public int delete(Friend friend) {
        int noOfRowsDeleted = 0;
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "DELETE FROM friend WHERE user_id = ? AND friend_id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1,friend.getUserId());
            statement.setString(2,friend.getFriendId());
            noOfRowsDeleted = statement.executeUpdate();          
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException sqlException) {}
        }
        return noOfRowsDeleted;
    }
    
    public List<String> getFriends(String userId) {
        List<String> friends = null;
        ResultSet resultSet;
        
        try {
            connection = DatabaseConnection.getConnection(); 
            query = "SELECT profile.username FROM profile JOIN friend ON friend.friend_id = profile.user_id WHERE friend.user_id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1,userId);
            resultSet = statement.executeQuery();
            friends = new ArrayList<>();
            
            while(resultSet.next()) {
                friends.add(resultSet.getString("username"));
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try { 
                statement.close();
                connection.close();
            } catch (SQLException sqlException) {}
        }
        return friends;
    }
    
}
