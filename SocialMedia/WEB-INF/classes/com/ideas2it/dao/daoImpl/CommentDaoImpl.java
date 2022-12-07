package com.ideas2it.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.ideas2it.model.Comment;
import com.ideas2it.dao.CommentDao;
import com.ideas2it.connection.DatabaseConnection;
import com.ideas2it.logger.CustomLogger;

/**
 * Performs a Create, Delete and read Operations for comment
 *
 * @version 1.0 01-NOV-2022
 * @author Venkatesh TM
 */
public class CommentDaoImpl implements CommentDao {
    Connection connection;
    PreparedStatement statement;
    CustomLogger logger;
    
    public CommentDaoImpl() {
        logger = new CustomLogger(CommentDaoImpl.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    public int create(Comment comment) {
        int noOfRowsAffected = 0;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO comment")
             .append(" (id, post_id, commented_user_id, content,")
             .append(" created_date_time) VALUES(?, ?, ?, ?, now());");

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, comment.getId());
            statement.setString(2, comment.getPostId());
            statement.setString(3, comment.getCommentedUserId());
            statement.setString(4, comment.getContent());
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
        query.append("UPDATE comment SET")
             .append(" content = ?, updated_date_time = now()")
             .append(" WHERE id = ?;");

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, content);
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
    public int delete(String id) {
        int noOfRowsDeleted = 0;
        String query;
        query = "DELETE FROM comment WHERE id = ?;";

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
    public int getCommentsCount(String postId) {
        int commentsCount = 0;
        ResultSet resultSet;
        StringBuilder query = new StringBuilder();
        query.append("SELECT COUNT(id) AS comments_count")
             .append(" FROM comment WHERE post_id = ?;");

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1,postId);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                commentsCount = resultSet.getInt("comments_count");
            } 
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return commentsCount;     
    }
    
    /**
     * {@inheritDoc}
     */
    @Override  
    public List<Comment> getComments(String postId) {
        ResultSet resultSet;
        List<Comment> comments = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT comment.id, comment.commented_user_id,")
             .append(" profile.username, comment.content FROM comment JOIN") 
             .append(" profile ON profile.user_id = comment.commented_user_id")
             .append(" WHERE comment.post_id = ?;");

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, postId);
            resultSet = statement.executeQuery();
            comments = new ArrayList<>();

            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getString("id")); 
                comment.setCommentedUserId(resultSet
                                  .getString("commented_user_id"));
                comment.setCommentedUserName(resultSet
                                  .getString("username"));
                comment.setContent(resultSet.getString("content"));
                comments.add(comment);
            }
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return comments;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override 
    public Comment getComment(String id) {
        ResultSet resultSet;
        Comment comment = null;  
        String query;
        query = "SELECT id, content FROM comment WHERE id = ?;";

        try {
            connection = DatabaseConnection.getConnection();            
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            comment = new Comment();

            if (resultSet.next()) {
                comment.setId(resultSet.getString("id"));
                comment.setContent(resultSet.getString("content"));
            }
            statement.close();
        }  catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return comment;        
    }
}