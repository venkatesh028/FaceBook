package com.ideas2it.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.UUID;
import java.time.LocalDate;
import java.time.Period;

import com.ideas2it.dao.UserDao;
import com.ideas2it.dao.daoImpl.UserDaoImpl;
import com.ideas2it.model.User;
import com.ideas2it.model.Profile;
import com.ideas2it.exception.CustomException;
import com.ideas2it.constant.Constants;

/**
 * It implements the logic of Update, delete, create, read and validation operation for the user
 *
 * @version 1.0 22-SEP-2022
 * @author Venkatesh TM
 */
public class UserService {
    private UserDao userDao;
    private ProfileService profileService;
    
    public UserService() {
        userDao = new UserDaoImpl();  
        profileService = new ProfileService();     
    }
    
    /**
     * Create a account for the user and set the userId for the user
     *
     * @param  user  details of the user
     * @return boolean true after adding the account 
     */
    public boolean create(User user, Profile profile) {
        String id;
        boolean isCreated;
        id = UUID.randomUUID().toString();
        user.setId(id);
        profile.setUserId(id);  
        isCreated = (userDao.create(user) > 0); 
        profileService.create(profile);
        return isCreated;
    }
   
    /**
     * Updates the user 
     *
     * @param user - details of the user
     * @return isUpdated - true or false based on the result
     */
    public boolean update(User user) {
        boolean isUpdated;
        isUpdated = userDao.update(user) > 0;
        return isUpdated;
    }

    /**
     * Delete the account of the user based on the user request 
     * 
     * @param  userId  userId of the user
     * @return boolean true after deleting the account
     */
    public boolean delete(String userId) {
        int rowAffected = userDao.delete(userId);
        boolean isDeleted = rowAffected > 0? true:false;
        return isDeleted;
    }

    /** 
     * Get the user Based on th id
     *
     * @param  userId userid of the user
     * @return user   user 
     */ 
    public User getById(String userId) throws CustomException {
        User user = userDao.getUser(userId);

        if (null == user) {
            throw new CustomException(Constants.ERROR_01);
        }
        return user; 
    }    

    /**
     * Get the userid of the user based on the email 
     *
     * @param  email  email entered by the user
     * @return userId Id of the user
     */
    public String getUserId(String email) {
        return userDao.getId(email);
    }

    /**
     * Check the email is exist already
     *
     * @param  email   email entered by the user
     * @return boolean true or false based on the result
     */ 
    public boolean isEmailExist(String email) {
        List<String> existingEmail = userDao.getExistingEmails();
        return existingEmail.contains(email);
    }
    
    /**
     * Check the given login credentials is valid or not 
     * 
     * @param  email    email of the user
     * @param  password password entered by the user
     * @return boolean  True or false based on the result;
     */
    public boolean isValidCredentials(String email, String password) { 
        boolean isValid = false;
        String validPassword = userDao.getPassword(email);
        
        if (null != validPassword) {
            isValid = validPassword.equals(password); 
        }
        return isValid;       
    }
            
    /**
     * Check the entered password is correct
     * 
     * @param  userId       userId of the user
     * @param  oldPassword  password of the user 
     * @return boolean      true or false based on the result
     */
    public boolean isPasswordMatches(String email, String oldPassword) {        
        return userDao.getPassword(email).equals(oldPassword);
    }
    
    /**
     * Calculate the age based on the dateOfBirth given by the user
     *
     * @param  dateOfBirth dateOfBirth given by the user
     * @return age         age based on the dateOfBirth
     */
    public int calculateAge(LocalDate dateOfBirth) {        
        LocalDate currentDate = LocalDate.now();
        Period age;
        age = Period.between(dateOfBirth, currentDate);
        return age.getYears();    
    }
   
    public boolean isUserNameExist(String userName) {
        return profileService.isUserNameExist(userName);
    }
    
    public User getUser(String id) {
        return userDao.getUser(id);
    }
}