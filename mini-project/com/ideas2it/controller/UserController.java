package com.ideas2it.controller;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;

import com.ideas2it.service.UserService;
import com.ideas2it.util.ValidationUtil;
import com.ideas2it.model.User;
import com.ideas2it.model.Profile;

/**
 * It contains validation method for validating the user details
 *
 * @version 1.0 08-SEP-2022
 * @author Venkatesh TM
 */
public class UserController {
    private UserService userService;
    private ValidationUtil validationUtil;

    public UserController() {
        this.userService = new UserService();
        this.validationUtil = new ValidationUtil();
    }
    


    /**
     * Check is that email is exist 
     *
     * @param  email   email of the user
     * @return boolean true if the account is exist else false 
     */
    public boolean isEmailExist(String email) {
        return userService.isEmailExist(email);       
    }

    /**
     * Create new account for the user
     *
     * @param  key      email of the user as a key
     * @param  user     details of the user
     * @return boolean  true if account is created successfully else false
     */
    public User create(User user, Profile profile){
        return userService.create(user, profile);
    }
   
    
    /**
     * Check is the credentials are valid
     *
     * @param  email    email of the user
     * @param  password password of the user
     * @return boolean  true if the credentials are valid else false
     */
    public boolean isValidCredentials(String email, String password) {
        return userService.isValidCredentials(email, password);
    }


    /**
     * Delete the user account 
     *
     * @param  email   email of the user to find the account
     * @return booelan true if the account is deleted Successfully else false
     */      
    public boolean delete(String email) {
        return userService.delete(email);
    }
    
    /** 
     * Gets the userId of the user
     *
     * @param email email of the user
     * @return userId userId of the user
     */
    public String getUserId(String email) {
        return userService.getUserId(email);
    }

    /** 
     * Get the user Based on th id
     *
     * @param  userId userid of the user
     * @return user   user 
     */     
    public User getById(String userId) {
        return userService.getById(userId);  
    }

    /**
     * Shows the personal info of the user
     *
     * @param  userId userId of the user
     * @return user   user details 
     */   
    public User showPersonalInfo(String userId) {
        return userService.showPersonalInfo(userId);
    }
    
    /**
     * Update the personal information of the user
     * 
     * @param  userId   user id of the user 
     * @param  user     user 
     * @return userName username of the user 
     */
    public User update(String userId, User user) {
        return userService.update(userId, user);
    }

    /** 
     * Update the loginCredentials of the user 
     * 
     * @param  oldEmail old email of the user 
     * @param  newEmail new email of the user
     * @return boolean  true after the update
     */
    public String updateLoginCredentials(String oldEmail, String newEmail) {
        return userService.updateLoginCredentials(oldEmail, newEmail);
    }
    
    /** 
     * Checks the given dateOfBith matches to the given format 
     * 
     * @param  dateOfBirth dateOfBirth need to be validated 
     * @return boolean     true if the given data matches the format else false
     */
    public boolean isValidDateOfBirth(String dateOfBirth) {
        try {
            validationUtil.isValidDateOfBirth(dateOfBirth);
        } catch(DateTimeParseException DE) {
            return false;
        }
        return true;
    }
    
    /**
     * Check the given phoneNumber matches the given format
     *
     * @param phoneNumber phone number given by the user 
     * @return boolean    true if the given data matches the format else false
     */
    public boolean isValidPhoneNumber(String phoneNumber) {
        return validationUtil.isValidPhoneNumber(phoneNumber);
    }
    
    /**
     * Check the entered password is correct
     * 
     * @param  userId       userId of the user
     * @param  oldPassword  password of the user 
     * @return boolean      true or false based on the result
     */
    public boolean isPasswordMatches(String userId, String oldPassword) {
        return userService.isPasswordMatches(userId, oldPassword);
    }

    /**
     * Calculate the age based on the dateOfBirth given by the user
     *
     * @param  dateOfBirth dateOfBirth given by the user
     * @return age         age based on the dateOfBirth
     */
    public int calculateAge(LocalDate dateOfBirth) {
        return userService.calculateAge(dateOfBirth);
    }   
    
    /**
     * Get the userId based on the username
     * 
     * @param  userName username of the user
     * @return userId   id of the user based on the userName
     */
    public String getUserIdByUserName(String userName) {
        return userService.getUserIdByUserName(userName);
    }

    /**
     * Check the given name matches the given format
     *
     * @param  name    name given by the user 
     * @return boolean true if the given data matches the format else false
     */
    public boolean isValidName(String name) {
        return validationUtil.isValidName(name);
    }

    /**
     * Check is this a valid email
     * 
     * @param  email   email entered by the user
     * @return boolean true if the eamil is valid else false
     */
    public boolean isValidEmail(String email) {
        return validationUtil.isValidEmail(email);
    }


    /**
     * Check is this a valid password
     * 
     * @param  password password entered by the user
     * @return boolean  true if the password is valid
     */   
    public boolean isValidPassword(String password) {
        return validationUtil.isValidPassword(password);
    }

    /**
     * Check the given userName matches the given format
     *
     * @param  userName  username given by the user
     * @return boolean   true if the given data matches the format else false
     */
    public boolean isValidUserName(String userName) {
        return validationUtil.isValidUserName(userName);
    }
}