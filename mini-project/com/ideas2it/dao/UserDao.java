package com.ideas2it.dao;

import java.util.List;
import java.time.LocalDate;

import com.ideas2it.model.User;
import com.ideas2it.model.Profile;

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
    public int create(User user);

    /** 
     * Deletes the user
     * 
     * @param  id   id of the user
     * @return noOfRowDeleted - number of rows deleted 
     */
    public int delete(String id);
    
    /**
     * Updates the user 
     *
     * @param user - details of the user
     * @return noOfRowsUpdated - number of rows updated
     */
    public int update(User user);

    /**
     * Updates the eamil of the user
     * 
     * @param  id - id of the user
     * @param  newEmail - email of the user
     * @return noOfRowsUpdated - number of rows updated 
     */
    public int updateEmail(String id, String newEmail);
    
    /**
     * Updates the password of the user
     * 
     * @param id - id of the user
     * @param password - password of the user
     * @return noOfRowsUpdated - number of rows updated
     */
    public int updatePassword(String id, String password);
    
    /**
     * Updates the dateofbirth and age of the user
     * 
     * @param id - id of the user
     * @param dateOfBirth - dateofbirth of the user
     * @param age   - age of the user
     * @return noOfRowsUpdated - number of rows updated
     */
    public int updateDateOfBirthAndAge(String id, LocalDate dateOfBirth, int age);
    
    /**
     * Updates the phone number of the user
     * 
     * @param  id - id of the user
     * @param  phoneNumber - phone number of the user
     * @return noOfRowsUpdated - number of rows updated
     */
    public int updatePhoneNumber(String id, long phoneNumber);
    
    /**
     * Gets the user based on the id
     * 
     * @param id - id of the user 
     * @return user - user details
     */
    public User getUser(String id);
    
    /**
     * Gets the existing emails 
     *
     * @return existingEmail - list of existing emails
     */
    public List<String> getExistingEmails();
    
    /**
     * Gets the password for the eamil
     *
     * @param email - email of the user
     * @return password - password for the email 
     */
    public String getPassword(String email);
    
    /** 
     * Gets the id of the user based on the email 
     * 
     * @param email - email of the user
     * @return id - id of the user based on the email
     */
    public String getId(String email); 
}