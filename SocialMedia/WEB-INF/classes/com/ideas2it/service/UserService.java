package com.ideas2it.service;

import java.time.LocalDate;

import com.ideas2it.model.User;
import com.ideas2it.model.Profile;
import com.ideas2it.exception.CustomException;



public interface UserService {

    /**
     * Create a account for the user and set the userId for the user
     *
     * @param  user  details of the user
     * @return boolean true after adding the account 
     */
    public boolean create(User user, Profile profile) throws CustomException;

    /**
     * Updates the user 
     *
     * @param user - details of the user
     * @return booelan - true or false based on the result
     */
    public boolean update(User user) throws CustomException;
    
    /**
     * Updates the password of the user 
     *
     * @param user - detail of the user with new password 
     * @return boolean - true or false based on the result
     */
    public boolean updatePassword(User user) throws CustomException;

    /**
     * Delete the account of the user based on the user request
     * 
     * @param  user    details of the user
     * @return boolean true after deleting the account
     */
    public boolean delete(User user) throws CustomException;

    /** 
     * Get the user Based on th id
     *
     * @param  userId userid of the user
     * @return user   user 
     */ 
    public User getById(String userId) throws CustomException;

    /**
     * Get the userid of the user based on the email 
     *
     * @param  email  email entered by the user
     * @return userId Id of the user
     */
    public String getUserId(String email) throws CustomException;

    /**
     * Check the email is exist already
     *
     * @param  email   email entered by the user
     * @return boolean true or false based on the result
     */ 
    public boolean isEmailExist(String email) throws CustomException;

    /**
     * Check the given login credentials is valid or not 
     * 
     * @param  email    email of the user
     * @param  password password entered by the user
     * @return boolean  True or false based on the result;
     */
    public boolean isValidCredentials(String email, String password) throws CustomException;

    /**
     * Check the entered password is correct
     * 
     * @param  email        email of the user
     * @param  oldPassword  password of the user 
     * @return boolean      true or false based on the result
     */
    public boolean isPasswordMatches(String email, String oldPassword) throws CustomException;

    /**
     * Calculate the age based on the dateOfBirth given by the user
     *
     * @param  dateOfBirth dateOfBirth given by the user
     * @return age         age based on the dateOfBirth
     */
    public int calculateAge(LocalDate dateOfBirth);  
}