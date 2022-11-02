package com.ideas2it.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;

import com.ideas2it.logger.CustomLogger;
import com.ideas2it.connection.DatabaseConnection;
import com.ideas2it.model.Profile;
import com.ideas2it.dao.ProfileDao;

/**
 * Perform the creation, Read , update and Delete for the profile 
 * 
 * @version 1.0 15-OCT-2022
 * @author  Venkatesh TM
 */

public class ProfileDaoImpl implements ProfileDao {
    Connection connection;
    PreparedStatement statement;
    String query;
    CustomLogger logger;
    
    public ProfileDaoImpl() {
        logger = new CustomLogger(ProfileDaoImpl.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override    
    public int create(Profile profile) {
        int noOfRowsAffected = 0;
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "INSERT INTO profile(id, user_id, username, created_date_time) VALUES(?,?,?,now());";
            statement = connection.prepareStatement(query);
            statement.setString(1,profile.getId());
            statement.setString(2,profile.getUserId());
            statement.setString(3,profile.getUserName());
            noOfRowsAffected = statement.executeUpdate();        
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException sqlException) {}
        }
        return noOfRowsAffected;
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public Profile getProfile(String userId) {
        ResultSet resultSet;
        Profile profile = null;

        try {
            connection = DatabaseConnection.getConnection();
            query = "SELECT * FROM profile WHERE user_id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1,userId);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                profile =  new Profile();
                profile.setId(resultSet.getString("id"));
                profile.setUserId(resultSet.getString("user_id"));
                profile.setUserName(resultSet.getString("username"));
                profile.setBio(resultSet.getString("bio"));
                profile.setFriendsCount(resultSet.getInt("friends_count")); 
            }
        }  catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException sqlException) {};
        }
        return profile;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(Profile profile) {
        int noOfRowsUpdated = 0;
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "UPDATE profile SET username = ?, bio = ?, updated_date_time = now() WHERE user_id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1,profile.getUserName());
            statement.setString(2,profile.getBio());
            statement.setString(3,profile.getUserId());
            noOfRowsUpdated = statement.executeUpdate();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException sqlException) {};
        }
        return noOfRowsUpdated;
    } 
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(String userId) {
        int noOfRowsDeleted = 0;
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "DELETE FROM profile WHERE user_id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1,userId);
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
    public Profile getUserProfileByUserName(String userName) {
        ResultSet resultSet;
        Profile profile = null;
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "SELECT * FROM profile WHERE username = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1,userName);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) { 
                profile = new Profile();
                profile.setUserName(resultSet.getString("username"));
                profile.setBio(resultSet.getString("bio"));
                profile.setFriendsCount(resultSet.getInt("friends_count"));
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        }  finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException sqlException) {};
        }
        return profile;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getExistingUserNames() {
        List<String> userNames = new ArrayList();
        ResultSet resultSet;
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "SELECT username FROM profile;";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                userNames.add(resultSet.getString("username"));
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        }  finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException sqlException) {};
        }
        return userNames;         
    }
}
