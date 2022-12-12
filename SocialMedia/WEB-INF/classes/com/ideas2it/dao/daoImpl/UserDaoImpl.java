package com.ideas2it.dao.daoImpl;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.model.User;
import com.ideas2it.model.Profile;
import com.ideas2it.dao.UserDao;

import com.ideas2it.connection.DatabaseConnection;

import com.ideas2it.constant.Messages;
import com.ideas2it.exception.CustomException;
import com.ideas2it.logger.CustomLogger;

/**
 * Perform the creation and delete operation for the user account 
 * 
 * @version 1.2 30-OCT-2022
 * @author  Venkatesh TM
 */
public class UserDaoImpl implements UserDao {
    private CustomLogger logger;
    private Connection connection;      
    private PreparedStatement statement;
     
    
    public UserDaoImpl() {
        this.logger = new CustomLogger(UserDaoImpl.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int create(User user) throws CustomException {
        int userCreated = 0;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO user(id, email, password, date_of_birth,")
             .append(" age, created_date_time)")
             .append(" VALUES(?, ?, ?, ?, ?, now());");
        
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, user.getId());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setDate(4, Date.valueOf(user.getDateOfBirth()));
            statement.setInt(5, user.getAge());
            userCreated = statement.executeUpdate(); 
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            throw new CustomException(Messages.USER_NOT_CREATED);
        } finally {            
            DatabaseConnection.closeConnection();
        }
        return userCreated;
    } 

    /**
     * {@inheritDoc}
     */
    @Override
    public int update(User user) throws CustomException {
        int userUpdated = 0;
        StringBuilder query = new StringBuilder();
        query.append("UPDATE user SET email = ?, date_of_birth = ?,")
             .append(" age = ?, phone_number = ?")
             .append(" WHERE id = ?;");
        
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, user.getEmail());
            statement.setDate(2, Date.valueOf(user.getDateOfBirth()));
            statement.setInt(3, user.getAge());
            statement.setLong(4, user.getPhoneNumber());
            statement.setString(5, user.getId());
            userUpdated = statement.executeUpdate();
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            throw new CustomException(Messages.USER_NOT_UPDATED);
        } finally {
            DatabaseConnection.closeConnection();
        }
        return userUpdated;    
    }  
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int delete(String id) throws CustomException {
        int noOfRowDeleted = 0;
        String query;
        query = "DELETE FROM user WHERE id = ?;";

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
            noOfRowDeleted = statement.executeUpdate();
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            throw new CustomException(Messages.USER_NOT_DELETED);
        } finally {
            DatabaseConnection.closeConnection();
        }
        return noOfRowDeleted;
    } 
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int updatePassword(User user) throws CustomException {
        int noOfRowsUpdated = 0;
        String query;

        try {
            connection = DatabaseConnection.getConnection();
            query = "UPDATE user SET password = ? WHERE id = ?;";
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getId());
            noOfRowsUpdated = statement.executeUpdate();   
            statement.close();         
        } catch (SQLException sqlException) { 
            logger.error(sqlException.getMessage());
            throw new CustomException(Messages.PASSWORD_NOT_UPDATED);
        }finally {
            DatabaseConnection.closeConnection();
        }
        return noOfRowsUpdated;
    }   
    
    /**
     * {@inheritDoc}
     */
    @Override
    public User getUser(String id)  throws CustomException {
        ResultSet resultSet;
        User user = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT user.id, user.email, user.date_of_birth,")
             .append(" user.age, user.phone_number, profile.visibility")
             .append(" FROM user JOIN profile ON user.id = profile.user_id")
             .append(" WHERE user.id= ?;");

        try {
            connection = DatabaseConnection.getConnection();            
            statement = connection.prepareStatement(query.toString());
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {     
                user = new User();  
                Profile profile = new Profile();      
                profile.setUserId(resultSet.getString("id"));

                user.setId(resultSet.getString("id"));
                user.setEmail(resultSet.getString("email"));
                user.setDateOfBirth((resultSet.getDate("date_of_birth").toLocalDate()));
                user.setAge(resultSet.getInt("age"));
                user.setPhoneNumber(resultSet.getLong("phone_number"));
                profile.setVisibility(resultSet.getString("visibility"));
                user.setProfile(profile);
                logger.info(user.toString());
            }     
            statement.close();         
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            throw new CustomException(Messages.USER_NOT_OBTAINED);
        } finally {
            DatabaseConnection.closeConnection();
        }
        return user;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getExistingEmails() throws CustomException {
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
            logger.error(sqlException.getMessage());
            throw new CustomException(Messages.EMAILS_NOT_OBTAINED);
        } finally {
            DatabaseConnection.closeConnection();
        }
        return existingEmail;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override    
    public String getPassword(String email) throws CustomException  {
        ResultSet resultSet;
        String password = null;
        String query;
        query = "SELECT password FROM user WHERE email = ?;";

        try {
            connection = DatabaseConnection.getConnection();            
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                password = resultSet.getString("password");
            } 
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            throw new CustomException(Messages.PASSWORD_NOT_OBTAINED);            
        } finally {
            DatabaseConnection.closeConnection();
        }
        return password;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getId(String email) throws CustomException {
        ResultSet resultSet;
        String id = null;
        String query;
        query = "SELECT id FROM user WHERE email = ?;";

        try {
            connection = DatabaseConnection.getConnection();            
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                id = resultSet.getString("id");
            }
            statement.close();
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
            throw new CustomException(Messages.ID_NOT_OBTAINED);
        } finally {
            DatabaseConnection.closeConnection();
        }
        return id;
    }
}