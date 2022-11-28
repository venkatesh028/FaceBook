package com.ideas2it.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

import com.ideas2it.logger.CustomLogger;
import com.ideas2it.connection.DatabaseConnection;
import com.ideas2it.model.User;
import com.ideas2it.model.Profile;
import com.ideas2it.dao.UserDao;

/**
 * Perform the creation and delete operation for the user account 
 * 
 * @version 1.2 30-OCT-2022
 * @author  Venkatesh TM
 */
public class UserDaoImpl implements UserDao {
    private CustomLogger logger;
    Connection connection;      
    PreparedStatement statement;
     
    
    public UserDaoImpl() {
        this.logger = new CustomLogger(UserDaoImpl.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int create(User user) {
        int userCreated = 0;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO user(id, email, password, date_of_birth,")
             .append(" age, created_date_time) ")
             .append("VALUES(?,?,?,?,?,now());");
        
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1,user.getId());
            statement.setString(2,user.getEmail());
            statement.setString(3,user.getPassword());
            statement.setDate(4,Date.valueOf(user.getDateOfBirth()));
            statement.setInt(5,user.getAge());
            userCreated = statement.executeUpdate(); 
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {            
            DatabaseConnection.closeConnection();
        }
        return userCreated;
    } 

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(User user) {
        int userUpdated = 0;
        StringBuilder query = new StringBuilder();
        query.append("UPDATE user SET email = ?, password = ?, ")
             .append("date_of_birth = ?, age = ?, phone_number = ?, ")
             .append("updated_date_time = now() WHERE id = ?;");
        
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1,user.getEmail());
            statement.setString(2,user.getPassword());
            statement.setDate(3,Date.valueOf(user.getDateOfBirth()));
            statement.setInt(4,user.getAge());
            statement.setLong(5,user.getPhoneNumber());
            statement.setString(6,user.getId());
            userUpdated = statement.executeUpdate();
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return userUpdated;    
    }  
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(String id) {
        int noOfRowDeleted = 0;
        String query;
        query = "DELETE FROM user WHERE id = ?;";

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1,id);
            noOfRowDeleted = statement.executeUpdate();
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return noOfRowDeleted;
    } 
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int updatePassword(String id, String password) {
        int noOfRowsUpdated = 0;
        String query;

        try {
            connection = DatabaseConnection.getConnection();
            query = "UPDATE user SET password = ?, update_date_time = now() WHERE id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1,password);
            statement.setString(2,id);
            noOfRowsUpdated = statement.executeUpdate();   
            statement.close();         
        } catch (SQLException sqlException) { 
            logger.error(sqlException.getMessage());
        }finally {
            DatabaseConnection.closeConnection();
        }
        return noOfRowsUpdated;
    }   
    
    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(String id) {
        ResultSet resultSet;
        User user = null;
        String query;
        query = "SELECT * FROM user where id = ? ;";

        try {
            connection = DatabaseConnection.getConnection();            
            statement = connection.prepareStatement(query);
            statement.setString(1,id);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {     
                user = new User();        
                user.setId(resultSet.getString("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setDateOfBirth((resultSet.getDate("date_of_birth").toLocalDate()));
                user.setAge(resultSet.getInt("age"));
                user.setPhoneNumber(resultSet.getLong("phone_number"));
            }     
            statement.close();         
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return user;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getExistingEmails() {
        ResultSet resultSet;
        String query;
        List<String> existingEmail = new ArrayList<>();
        query = "SELECT email FROM user;";

        try {
            connection = DatabaseConnection.getConnection();            
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                existingEmail.add(resultSet.getString("email"));   
            }
            statement.close();
        } catch (SQLException sqlException) {
            logger.error("Error in connection");
        } finally {
            DatabaseConnection.closeConnection();
        }
        return existingEmail;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override    
    public String getPassword(String email) {
        ResultSet resultSet;
        String password = null;
        String query;
        query = "SELECT password FROM user WHERE email = ?;";

        try {
            connection = DatabaseConnection.getConnection();            
            statement = connection.prepareStatement(query);
            statement.setString(1,email);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                password = resultSet.getString("password");
            } 
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return password;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getId(String email) {
        ResultSet resultSet;
        String id = null;
        String query;
        query = "SELECT id FROM user WHERE email = ?;";

        try {
            connection = DatabaseConnection.getConnection();            
            statement = connection.prepareStatement(query);
            statement.setString(1,email);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                id = resultSet.getString("id");
            }
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            DatabaseConnection.closeConnection();
        }
        return id;
    }
}