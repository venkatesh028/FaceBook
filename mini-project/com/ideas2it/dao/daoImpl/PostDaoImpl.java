package com.ideas2it.dao.daoImpl;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ideas2it.logger.CustomLogger;
import com.ideas2it.connection.DatabaseConnection;
import com.ideas2it.model.Post;
import com.ideas2it.dao.PostDao;

/**
 * Perform add, update and get function for the post
 * 
 * @version 1.0 22-SEP-2022
 * @author Venkatesh TM
 */
public class PostDaoImpl implements PostDao { 
    CustomLogger logger;
    Connection connection;
    PreparedStatement statement;
    String query;
    
    public PostDaoImpl() {
        logger = new CustomLogger(PostDaoImpl.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override  
    public int create(Post post) {   
        int noOfRowsAffected = 0;
        
        try {     
            connection = DatabaseConnection.getConnection();
            query = "INSERT INTO post(id, user_id, content, created_date_time) VALUES(?,?,?,now());";
            statement = connection.prepareStatement(query); 
            statement.setString(1,post.getId());
            statement.setString(2,post.getPostedUserId());
            statement.setString(3,post.getContent());
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
    public int update(String id, String content) {
        int noOfRowsUpdated = 0;
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "UPDATE post SET content = ? WHERE id = ?;";
            statement = connection.prepareStatement(query); 
            statement.setString(1,content);
            statement.setString(2,id);
            noOfRowsUpdated = statement.executeUpdate();          
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException sqlException) {}
        }
        return noOfRowsUpdated;
    }
    
    public int updateLikeCount(String id, int likeCount) {
        int noOfRowsUpdated = 0;
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "UPDATE post SET like_count = ? WHERE id = ?;";
            statement = connection.prepareStatement(query);
            statement.setInt(1, likeCount);
            statement.setString(2, id);
            noOfRowsUpdated = statement.executeUpdate();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException sqlException) {}
        }
        return noOfRowsUpdated;
    }
    
    public int updateCommentCount(String id, int commentCount) {
        int noOfRowsUpdated = 0;
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "UPDATE post SET comment_count = ? WHERE id = ?;";
            statement = connection.prepareStatement(query);
            statement.setInt(1, commentCount);
            statement.setString(2, id);
            noOfRowsUpdated = statement.executeUpdate();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException sqlException) {}
        }
        return noOfRowsUpdated;
    }

    /**
     * {@inheritDoc}
     */    
    @Override
    public List<Post> getUserPosts() {
        List<Post> posts = null;
        ResultSet resultSet;
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "SELECT * FROM post;";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            posts = new ArrayList<>();

            while (resultSet.next()){
            }  
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException sqlException) {}
        } 
        return posts;
    }

    /**
     * {@inheritDoc}
     */    
    @Override
    public List<Post> getPostOfParticularUser(String userId) {
        List<Post> postOfParticularUser = null;
        ResultSet resultSet;
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "SELECT * FROM post WHERE user_id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1,userId);
            resultSet = statement.executeQuery();            
            postOfParticularUser = new ArrayList<>();

            while (resultSet.next()){
               
            }  
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException sqlException) {}
        } 
        return postOfParticularUser;
       
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(String id) { 
        int noOfRowsDeleted = 0;
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "DELETE FROM post WHERE id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
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
    
}