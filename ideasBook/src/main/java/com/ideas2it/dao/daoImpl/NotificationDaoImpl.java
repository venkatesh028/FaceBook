package com.ideas2it.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.model.Notification;
import com.ideas2it.dao.NotificationDao;
import com.ideas2it.connection.DatabaseConnection;
import com.ideas2it.logger.CustomLogger;

/**
 * Performs the Create, get, Update, delete operations for the Notification
 * 
 * @version 1.0 04-NOV-2022
 * @author Venkatesh TM
 */
public class NotificationDaoImpl implements NotificationDao  { 
    private CustomLogger logger;
    private Connection connection;  
    private PreparedStatement statement;      
    
    public NotificationDaoImpl() {
        this.logger = new CustomLogger(NotificationDaoImpl.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int create(Notification notification) {
        int noOfRowsAffected  = 0;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO notification")
             .append(" (id, friend_request_id, created_date_time)")
             .append(" VALUES(?, ?, now());");

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, notification.getId());
            statement.setString(2, notification.getRequestId());
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
    public List<Notification> getNotifications(String userId) {
        ResultSet resultSet;
        List<Notification> notifications = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT notification.id, notification.friend_request_id,") 
             .append(" profile.username, friend_request.created_date_time")
             .append(" FROM notification JOIN friend_request")
             .append(" ON friend_request.id = notification.friend_request_id")
             .append(" JOIN profile ON profile.user_id = friend_request.requested_user_id")
             .append(" WHERE friend_request.user_id = ?;");

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, userId);
            resultSet = statement.executeQuery();
            notifications = new ArrayList<>();
            
            while (resultSet.next()) {
               Notification notification = new Notification();
               
               notification.setId(resultSet
                                  .getString("id"));
               notification.setRequestId(resultSet
                                  .getString("friend_request_id"));
               notification.setRequestedUserName(resultSet
                                  .getString("username"));
               notification.setRequestGivenAt(resultSet
                                  .getTimestamp("created_date_time"));
               notifications.add(notification);
            }
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }   
        return notifications;        
    }    

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(String id) {
        int noOfRowsUpdated = 0;
        String status = "seen";
        StringBuilder query = new StringBuilder();
        query.append("UPDATE notification SET")
             .append(" notification_status = ?, updated_date_time = now()")
             .append(" WHERE id = ?;");

        try { 
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, status);
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
        query = "DELETE FROM notification WHERE id = ?;";

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
}