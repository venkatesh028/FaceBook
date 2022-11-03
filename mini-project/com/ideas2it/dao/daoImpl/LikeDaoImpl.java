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
    String query;
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
         
        try {
            connection = DatabaseConnection.getConnection();
            query = "INSERT INTO post_like (id, post_id, liked_user_id, created_date_time) VALUES(?,?,?,now());";
            statement = connection.prepareStatement(query);
            statement.setString(1,like.getId());
            statement.setString(2,like.getPostId());
            statement.setString(3,like.getLikedUserId());
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
    public int delete(String postId, String userId) {
        int noOfRowsDeleted = 0;
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "DELETE FROM post_like WHERE post_id = ? AND liked_user_id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1,postId);
            statement.setString(2,userId);
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
    public int getLikeCountOfPost(String postId) {
        int likeCount = 0;
        ResultSet resultSet;

        try {
            connection = DatabaseConnection.getConnection();
            query = "SELECT count(id) AS like_count FROM post_like WHERE post_id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1,postId);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                likeCount = resultSet.getInt("like_count");
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());   
        } finally {
            try {
                statement.close();
                connection.close();
            } catch(SQLException sqlException) {}
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
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "SELECT liked_user_id FROM post_like WHERE post_id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1,postId);
            resultSet = statement.executeQuery();
            likedUsersId = new ArrayList<>(); 
            
            while (resultSet.next()) {
                likedUsersId.add(resultSet.getString("liked_user_id"));
            }         
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException sqlException) {}
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
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "SELECT username FROM profile JOIN post_like ON post_like.liked_user_id = profile.user_id  WHERE post_like.post_id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1,postId);
            resultSet = statement.executeQuery();
            likedUserNames = new ArrayList<>(); 
            
            while (resultSet.next()) {
                likedUserNames.add(resultSet.getString("username"));
            }         
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException sqlException) {}
        } 
        return likedUserNames;         
    }  
}