package com.ideas2it.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.connection.DatabaseConnection;
import com.ideas2it.model.Post;
import com.ideas2it.dao.PostDao;
import com.ideas2it.logger.CustomLogger;

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
    
    public PostDaoImpl() {
        logger = new CustomLogger(PostDaoImpl.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override  
    public int create(Post post) {   
        int noOfRowsAffected = 0;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO")
             .append(" post(id, user_id, content, created_date_time)")
             .append(" VALUES(?, ?, ?, now());");

        try {     
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString()); 
            statement.setString(1, post.getId());
            statement.setString(2, post.getPostedUserId());
            statement.setString(3, post.getContent());
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
    public int update(String id, String content) {
        int noOfRowsUpdated = 0;
        StringBuilder query = new StringBuilder();
        query.append("UPDATE post SET")
             .append(" content = ?, updated_date_time = now()")
             .append(" WHERE id = ?;");

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString()); 
            statement.setString(1,content);
            statement.setString(2,id);
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
    public int updateLikeCount(String id, int likeCount) {
        int noOfRowsUpdated = 0;
        StringBuilder query = new StringBuilder();
        query.append("UPDATE post")
             .append(" SET like_count = ?, updated_date_time = now()")
             .append(" WHERE id = ?;");

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setInt(1, likeCount);
            statement.setString(2, id);
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
    public int updateCommentCount(String id, int commentCount) {
        int noOfRowsUpdated = 0;
        StringBuilder query = new StringBuilder();
        query.append("UPDATE post SET")
             .append(" comment_count = ?, updated_date_time = now()")
             .append(" WHERE id = ?;");

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setInt(1, commentCount);
            statement.setString(2, id);
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
    public List<Post> getUserPosts() {
        List<Post> posts = null;
        ResultSet resultSet;
        StringBuilder query = new StringBuilder();
        query.append("SELECT post.id, profile.username as posted_user_name,")
             .append(" post.content, post.like_count, post.comment_count")
             .append(" FROM post JOIN profile ON")
             .append(" profile.user_id = post.user_id")
             .append(" WHERE profile.visibility = 'public';");

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            resultSet = statement.executeQuery();
            posts = new ArrayList<>();

            while (resultSet.next()){
                Post post = new Post(resultSet.getString("id"), 
                                        resultSet.getString("posted_user_name"),
                                        resultSet.getString("content"),
                                        resultSet.getInt("like_count"),
                                        resultSet.getInt("comment_count"));
                posts.add(post);     
            }
            statement.close();  
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally { 
            DatabaseConnection.closeConnection();
        } 
        return posts;
    }

    /**
     * {@inheritDoc}
     */    
    @Override
    public List<Post> getPostOfParticularUser(String userId) {
        List<Post> postOfParticularUser = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT post.id, profile.username as posted_user_name,")
             .append(" post.content, post.like_count, post.comment_count")
             .append(" FROM post JOIN profile ON")
             .append(" profile.user_id = post.user_id")
             .append(" WHERE post.user_id = ?;");
        ResultSet resultSet;
        
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, userId);
            resultSet = statement.executeQuery();            
            postOfParticularUser = new ArrayList<>();

            while (resultSet.next()){
                Post post = new Post(resultSet.getString("id"),
                                     resultSet.getString("posted_user_name"),
                                     resultSet.getString("content"),
                                     resultSet.getInt("like_count"),
                                     resultSet.getInt("comment_count"));
                postOfParticularUser.add(post);               
            } 
            statement.close(); 
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        } 
        return postOfParticularUser;       
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(String id) { 
        int noOfRowsDeleted = 0;
        String query;
        query = "DELETE FROM post WHERE id = ?;";

        try {
            connection = DatabaseConnection.getConnection();            
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
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
    public Post getPost(String id) {
        String query;
        ResultSet resultSet;
        Post post = null;
        query = "SELECT id, user_id, content FROM post WHERE id = ?;";
         
        try {
            connection = DatabaseConnection.getConnection();            
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                post = new Post();  
                post.setPostId(resultSet.getString("id"));
                post.setPostedUserId(resultSet.getString("user_id"));
                post.setContent(resultSet.getString("content")); 
            } 
            statement.close();          
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }  
        return post;    
    }
  
}