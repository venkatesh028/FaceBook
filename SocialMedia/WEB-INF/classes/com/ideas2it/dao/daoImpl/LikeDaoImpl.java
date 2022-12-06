package com.ideas2it.dao.daoImpl;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ideas2it.logger.CustomLogger;
import com.ideas2it.connection.DatabaseConnection;
import com.ideas2it.model.Like;
import com.ideas2it.dao.LikeDao;


/**
 * Performs a Create, Delete and read Operations for Like
 *
 * @version 1.0 01-NOV-2022
 * @author Venkatesh TM
 */
public class LikeDaoImpl implements LikeDao {
    Connection connection;
    PreparedStatement statement;
    CustomLogger logger;

    public LikeDaoImpl() {
        logger = new CustomLogger(LikeDaoImpl.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override 
    public int create(Like like) {
        int noOfRowsAffected = 0;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO reaction ")
             .append("(id, post_id, liked_user_id, created_date_time) ")
             .append("VALUES(?,?,?,now());");

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1,like.getId());
            statement.setString(2,like.getPostId());
            statement.setString(3,like.getLikedUserId());
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
    public int delete(String postId, String userId) {
        int noOfRowsDeleted = 0;
        String query;
        query = "DELETE FROM reaction WHERE post_id = ? AND liked_user_id = ?;";

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1,postId);
            statement.setString(2,userId);
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
    public int getLikeCountOfPost(String postId) {
        int likeCount = 0;
        ResultSet resultSet;
        StringBuilder query = new StringBuilder();
        query.append("SELECT count(id) AS like_count ")
             .append("FROM reaction WHERE post_id = ?;");

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1,postId);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                likeCount = resultSet.getInt("like_count");
            }
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());   
        } finally {
            DatabaseConnection.closeConnection();
        }
        return likeCount;
    }
   
    /**
     * {@inheritDoc}
     */
    @Override 
    public List<String> getLikedUsersIdOfPost(String postId) {
        List<String> likedUsersId = null;
        ResultSet resultSet;
        String query;
        query = "SELECT liked_user_id FROM reaction WHERE post_id = ?;";

        try {
            connection = DatabaseConnection.getConnection();            
            statement = connection.prepareStatement(query);
            statement.setString(1,postId);
            resultSet = statement.executeQuery();
            likedUsersId = new ArrayList<>(); 
            
            while (resultSet.next()) {
                likedUsersId.add(resultSet.getString("liked_user_id"));
            } 
            statement.close();         
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        } 
        return likedUsersId;         
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    public List<String> getLikedUserNamesOfPost(String postId) {
        List<String> likedUserNames = null;
        ResultSet resultSet;
        StringBuilder query = new StringBuilder();
        query.append("SELECT username FROM profile JOIN reaction ")
             .append("ON reaction.liked_user_id = profile.user_id  ")
             .append("WHERE reaction.post_id = ?;");

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1,postId);
            resultSet = statement.executeQuery();
            likedUserNames = new ArrayList<>(); 
            
            while (resultSet.next()) {
                likedUserNames.add(resultSet.getString("username"));
            }    
            statement.close();     
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        } 
        return likedUserNames;         
    }  
}