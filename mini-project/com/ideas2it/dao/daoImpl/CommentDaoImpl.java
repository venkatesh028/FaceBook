package com.ideas2it.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ideas2it.logger.CustomLogger;
import com.ideas2it.model.Comment;
import com.ideas2it.connection.DatabaseConnection;

public class CommentDaoImpl implements CommentDao {
    Connection connection;
    PreparedStatement statement;
    String query;
    CustomLogger logger;
    
    public CommentDaoImpl() {
        logger = new CustomLogger(CommentDaoImpl.class);
    }
 
    public int create(Comment comment) {
        int noOfRowsAffected = 0;
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "INSERT INTO post_comment(id, post_id, commented_user_id, content, created_date_time) VALUES(?,?,?,?,now());";
            statement = connection.prepareStatement(query);
            statement.setString(1,comment.getId());
            statement.setString(2,comment.getPostId());
            statement.setString(3,comment.getCommentedUserId());
            statement.setString(4,comment.getContent());
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
    
    public int update(String id, String content) {
        int noOfRowsUpdated = 0;
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "UPDATE post_comment SET content = ?, updated_date_time = now() WHERE id = ?;";
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
    
    public int delete(String id) {
        int noOfRowsDeleted = 0;
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "DELETE FROM post_comment WHERE id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1,id);
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
    
    public List<Comment> getComments(String postId) {
        ResultSet resultSet;
        List<Comment> comments = null;
        try {
            connection = DatabaseConnection.getConnection();
            query = "";
            statement = connection.prepareStatement(query);
            statement.setString(1,postId);
            resultSet = statement.executeQuery();
            comments = new ArrayList<>();

            while (resultSet) {
                Comment comment = new Comment();
                
            }

        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException sqlException) {}
        }
        return comments;
    }
}