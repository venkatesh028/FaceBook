package com.ideas2it.dao;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.model.User;
import com.ideas2it.model.Profile;
import com.ideas2it.exception.CustomException;

/**
 * It is interface for the User dao impl it contains the method for
 * create, update, delete, get operations for user
 * 
 * @version 1.0 22-SEP-2022
 * @author  Venkatesh TM
 */
public interface UserDao {

    /**
     * Creates the user
     *  
     * @param  user     details of the user
     * @return boolean  true after adding the user in map
     */
    public int create(User user) throws CustomException;

    /** 
     * Deletes the user
     * 
     * @param  id   id of the user
     * @return noOfRowDeleted - number of rows deleted 
     */
    public int delete(String id)throws CustomException;
    
    /**
     * Updates the user 
     *
     * @param user - details of the user
     * @return noOfRowsUpdated - number of rows updated
     */
    public int update(User user) throws CustomException; 
    
    /**
     * Updates the password of the user
     * 
     * @param user - user details with new password
     * @return noOfRowsUpdated - number of rows updated
     */
    public int updatePassword(User user) throws CustomException; 
    
    /**
     * Gets the user based on the id
     * 
     * @param id - id of the user 
     * @return user - user details
     */
    public User getUser(String id) throws CustomException;
    
    /**
     * Gets the existing emails 
     *
     * @return existingEmail - list of existing emails
     */
    public List<String> getExistingEmails() throws CustomException;
    
    /**
     * Gets the password for the eamil
     *
     * @param email - email of the user
     * @return password - password for the email 
     */
    public String getPassword(String email) throws CustomException;
    
    /** 
     * Gets the id of the user based on the email 
     * 
     * @param email - email of the user
     * @return id - id of the user based on the email
     */
    public String getId(String email) throws CustomException; 
}