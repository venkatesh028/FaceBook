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
    String query;    
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

        try {
            connection = DatabaseConnection.getConnection();
            query = "INSERT INTO user(id, email, password, date_of_birth, age, created_date_time) VALUES(?,?,?,?,?,now());";
            statement = connection.prepareStatement(query);
            statement.setString(1,user.getId());
            statement.setString(2,user.getEmail());
            statement.setString(3,user.getPassword());
            statement.setDate(4,Date.valueOf(user.getDateOfBirth()));
            statement.setInt(5,user.getAge());
            userCreated = statement.executeUpdate();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {};
        }
        return userCreated;
    } 
    
    public int update(User user) {
        int userUpdated = 0;

        try {
            connection = DatabaseConnection.getConnection();
            query = "UPDATE user SET email = ?, password = ?, date_of_birth = ?, age = ?, phone_number = ?, updated_date_time = now() WHERE id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1,user.getEmail());
            statement.setString(2,user.getPassword());
            statement.setDate(3,Date.valueOf(user.getDateOfBirth()));
            statement.setInt(4,user.getAge());
            statement.setLong(5,user.getPhoneNumber());
            statement.setString(6,user.getId());
            userUpdated = statement.executeUpdate();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {};
        }
        return userUpdated;    
    }
 
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(String id) {
        int noOfRowDeleted = 0;

        try {
            connection = DatabaseConnection.getConnection();
            query = "DELETE FROM user WHERE id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1,id);
            noOfRowDeleted = statement.executeUpdate();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {};
        }
        return noOfRowDeleted;
    }
        
    /**
     * {@inheritDoc}
     */
    @Override
    public int updateEmail(String id, String newEmail) {
        int noOfRowsUpdated = 0;

        try {
            connection = DatabaseConnection.getConnection();
            query = "UPDATE user SET email = ?, update_date_time = now() WHERE id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1,newEmail);
            statement.setString(2,id);
            noOfRowsUpdated = statement.executeUpdate();            
        } catch (SQLException sqlException) { 
            logger.error(sqlException.getMessage());
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {};
        }
        return noOfRowsUpdated;
    }  
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int updatePassword(String id, String password) {
        int noOfRowsUpdated = 0;

        try {
            connection = DatabaseConnection.getConnection();
            query = "UPDATE user SET password = ?, update_date_time = now() WHERE id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1,password);
            statement.setString(2,id);
            noOfRowsUpdated = statement.executeUpdate();            
        } catch (SQLException sqlException) { 
            logger.error(sqlException.getMessage());
        }finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {};
        }
        return noOfRowsUpdated;
    }
       
    /**
     * {@inheritDoc}
     */
    @Override 
    public int updateDateOfBirthAndAge(String id, LocalDate dateOfBirth, int age) {
        int noOfRowsUpdated = 0;
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "UPDATE user SET date_of_birth = ?, age = ?, updated_date_time = now() WHERE id = ?;";
            statement = connection.prepareStatement(query);
            statement.setDate(1,Date.valueOf(dateOfBirth));
            statement.setInt(2,age);
            statement.setString(3,id);
            noOfRowsUpdated = statement.executeUpdate(); 
        } catch (SQLException sqlException) { 
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {};
        }
        return noOfRowsUpdated;
    }    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int updatePhoneNumber(String id, long phoneNumber) {
        int noOfRowsUpdated = 0;
        
        try {
            connection = DatabaseConnection.getConnection();
            query = "UPDATE user SET phone_number = ?, updated_date_time = now() WHERE id = ?;";
            statement = connection.prepareStatement(query);
            statement.setLong(1,phoneNumber);
            statement.setString(2,id);
            noOfRowsUpdated = statement.executeUpdate();             
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {};
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

        try {
            connection = DatabaseConnection.getConnection();
            query = "SELECT * FROM user where id = ? ;";
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
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {};
        }
        return user;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getExistingEmails() {
        ResultSet resultSet;
        List<String> existingEmail = new ArrayList<>();

        try {
            connection = DatabaseConnection.getConnection();
            query = "SELECT email FROM user;";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                existingEmail.add(resultSet.getString("email"));   
            }
        } catch (SQLException sqlException) {
            logger.error("Error in connection");
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {};
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

        try {
            connection = DatabaseConnection.getConnection();
            query = "SELECT password FROM user WHERE email = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1,email);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                password = resultSet.getString("password");
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {};
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

        try {
            connection = DatabaseConnection.getConnection();
            query = "SELECT id FROM user WHERE email = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1,email);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                id = resultSet.getString("id");
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {};
        }
        return id;
    }
}