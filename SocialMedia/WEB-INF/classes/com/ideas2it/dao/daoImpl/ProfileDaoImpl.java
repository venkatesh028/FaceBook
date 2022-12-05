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
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO profile")
             .append("(id, user_id, username, created_date_time) ")
             .append("VALUES(?,?,?,now());");

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1,profile.getId());
            statement.setString(2,profile.getUserId());
            statement.setString(3,profile.getUserName());
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
    public Profile getProfile(String userId) {
        ResultSet resultSet;
        Profile profile = null;
        String query;
        query = "SELECT * FROM profile WHERE user_id = ?;";

        try {
            connection = DatabaseConnection.getConnection();            
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
                profile.setVisibility(resultSet.getString("visibility"));
            }
            statement.close();
        }  catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return profile;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(Profile profile) {
        int noOfRowsUpdated = 0;
        StringBuilder query = new StringBuilder();
        query.append("UPDATE profile SET ")
             .append("username = ?, bio = ?, friends_count = ?, ")
             .append("visibility = ? ")
             .append("WHERE user_id = ?;");
        
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, profile.getUserName());
            statement.setString(2, profile.getBio());
            statement.setInt(3, profile.getFriendsCount());
            statement.setString(4, profile.getVisibility());
            statement.setString(5, profile.getUserId());
         
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
    public int delete(String userId) {
        int noOfRowsDeleted = 0;
        String query;
        query = "DELETE FROM profile WHERE user_id = ?;";

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1,userId);
            noOfRowsDeleted = statement.executeUpdate();
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
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
        String query;
        query = "SELECT * FROM profile WHERE username = ?;";

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1,userName);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) { 
                profile = new Profile();
                profile.setUserId(resultSet.getString("user_Id"));
                profile.setUserName(resultSet.getString("username"));
                profile.setBio(resultSet.getString("bio"));
                profile.setFriendsCount(resultSet.getInt("friends_count"));
                profile.setVisibility(resultSet.getString("visibility"));
            }
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        }  finally {
            DatabaseConnection.closeConnection();
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
        String query;
        query = "SELECT username FROM profile;";

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                userNames.add(resultSet.getString("username"));
            } 
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        }  finally {
            DatabaseConnection.closeConnection();
        }
        return userNames;         
    }
}
