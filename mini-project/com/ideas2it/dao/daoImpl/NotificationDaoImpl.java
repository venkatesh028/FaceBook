package com.ideas2it.dao.daoImpl;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ideas2it.dao.NotificationDao;
import com.ideas2it.model.Notification;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.connection.DatabaseConnection;

public class NotificationDaoImpl implements NotificationDao  { 
    private CustomLogger logger;
    private Connection connection;  
    private String query;    
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
         
        try {
            connection = DatabaseConnection.getConnection();
            query = "INSERT INTO notification (id, friend_request_id, created_date_time) VALUES(?,?,now());";
            statement = connection.prepareStatement(query);
            statement.setString(1,notification.getId());
            statement.setString(2,notification.getRequestId());
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
    public List<Notification> getNotifications(String userId) {
        ResultSet resultSet;
        List<Notification> notifications = null;
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "SELECT notification.id, notification.friend_request_id, profile.username, friend_request.created_date_time FROM notification JOIN friend_request ON friend_request.id = notification.friend_request_id JOIN profile ON profile.user_id = friend_request.requested_user_id WHERE friend_request.user_id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1,userId);
            resultSet = statement.executeQuery();
            notifications = new ArrayList<>();
            
            while (resultSet.next()) {
               Notification notification = new Notification();
               
               notification.setId(resultSet.getString("id"));
               notification.setRequestId(resultSet.getString("friend_request_id"));
               notification.setRequestedUserName(resultSet.getString("username"));
               notification.setRequestGivenAt(resultSet.getTimestamp("created_date_time"));
               notifications.add(notification);
            }

        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try { 
                statement.close();
                connection.close();
            } catch (SQLException sqlException) {}
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
        try { 
            connection = DatabaseConnection.getConnection();
            query = "UPDATE notification SET notification_status = ?, updated_date_time = now() WHERE id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1,status);
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

    /**
     * {@inheritDoc}
     */
    @Override  
    public int delete(String id) {
        int noOfRowsDeleted = 0;
        
        try { 
            connection = DatabaseConnection.getConnection();
            query = "DELETE FROM notification WHERE id = ?;";
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
}