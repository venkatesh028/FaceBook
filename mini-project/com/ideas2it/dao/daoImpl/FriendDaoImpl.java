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
    private PreparedStatement statement;

    
    public FriendDaoImpl() {
        this.logger = new CustomLogger(FriendDaoImpl.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int create(Friend friend) {
        int noOfRowsAffected = 0;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO friend")
             .append("(id, user_id, friend_id, created_date_time) ")
             .append("VALUES(?,?,?,now());");

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(Friend friend) {
        int noOfRowsDeleted = 0;
        String query;
        query = "DELETE FROM friend WHERE user_id = ? AND friend_id = ?;";

        try {
            connection = DatabaseConnection.getConnection();            
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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getFriends(String userId) {
        List<String> friends = null;
        ResultSet resultSet;
        StringBuilder query = new StringBuilder();
        query.append("SELECT profile.username ")
             .append("FROM profile JOIN friend ")
             .append("ON friend.friend_id = profile.user_id ")
             .append("WHERE friend.user_id = ?;");

        try {
            connection = DatabaseConnection.getConnection(); 
            statement = connection.prepareStatement(query.toString());
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
