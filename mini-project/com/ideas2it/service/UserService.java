package com.ideas2it.service;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.UUID;
import java.time.LocalDate;
import java.time.Period;

import com.ideas2it.dao.UserDao;
import com.ideas2it.dao.daoImpl.UserDaoImpl;
import com.ideas2it.model.User;
import com.ideas2it.model.Profile;

/**
 * Perform the Update, delete, create operation for the user
 *
 * @version 1.0 22-SEP-2022
 * @author Venkatesh TM
 */
public class UserService {
    private UserDao userDao;
    private ProfileService profileService;

    public UserService() {
        this.userDao = UserDaoImpl.getInsatance();
        this.profileService = new ProfileService();
    }

    /**
     * Check the email is exist already
     *
     * @param  email   email entered by the user
     * @return boolean true or false based on the result
     */ 
    public boolean isEmailExist(String email) {
        Map<String, String> loginCredentials = userDao.getLoginCredentials();
        return loginCredentials.containsKey(email);
    }
    
    /**
     * Create a account for the user and set the userId for the user
     *
     * @param  user  details of the user
     * @return boolean true after adding the account 
     */
    public User create(User user, Profile profile) {
        String userId;
   
        userId = UUID.randomUUID().toString();
        user.setUserId(userId);       
        profile.setUserId(userId);
        profileService.create(profile); 
        return userDao.create(user);
    }
    
    /**
     * Delete the account of the user based on the user request 
     * 
     * @param  userId  userId of the user
     * @return boolean true after deleting the account
     */
    public boolean delete(String userId) {
        return userDao.delete(userId);
    }
    
    /**
     * Check the given login credentials is valid or not 
     * 
     * @param  email    email of the user
     * @return password password entered by the user
     */
    public boolean isValidCredentials(String email, String password) {
        User user;
        boolean isValid = false;
        Map<String, User> users;
        Map<String, String> loginCredentials;
        users = userDao.getUsers();
        loginCredentials = userDao.getLoginCredentials();        
        user = users.get(loginCredentials.get(email));
      
        return user != null ? user.getPassword().equals(password) : isValid;
    }
    
    /**
     * Get the userid of the user based on the email 
     *
     * @param  email  email entered by the user
     * @return userId userId of the user
     */
    public String getUserId(String email) {
        Map<String, String> loginCredentials;
        loginCredentials = userDao.getLoginCredentials();
        return loginCredentials.get(email);
    }
    
    /** 
     * Get the user Based on th id
     *
     * @param  userId userid of the user
     * @return user   user 
     */ 
    public User getById(String userId) {
        return userDao.getById(userId); 
    }

    /**
     * Shows the personal information of the user
     * 
     * @param  userId userId of the user
     * @return user   personal information of the user
     */    
    public User showPersonalInfo(String userId) {
        return userDao.getById(userId);
    }
    
    /**
     * Update the personal information of the user
     *
     * @param  userId   Id of the user
     * @param  user     updated personal information of the user
     * @return booelan  true after the updating
     */ 
    public User update(String userId, User user) { 
        return userDao.update(userId, user);
    }
    
    /**
     * Update the loginCredentials of the user
     * 
     * @param oldEmail old email in loginCredentials
     * @parma newEmail updated email of the user
     * @retun boolean  true after the update
     */
    public String updateLoginCredentials(String oldEmail, String newEmail) {
        return userDao.updateLoginCredentials(oldEmail, newEmail);        
    }

    /**
     * get the profile of the user 
     *
     * @param  userId  userId of the user
     * @return profile profile details of the user
     */
    public Profile getProfile(String userId) {
        return userDao.getProfile(userId);
    }
    
    /**
     * Check the entered password is correct
     * 
     * @param  userId       userId of the user
     * @param  oldPassword  password of the user 
     * @return boolean      true or false based on the result
     */
    public boolean isPasswordMatches(String userId, String oldPassword) {
        Map<String, User> users; 
        User user;
        users = userDao.getUsers();
        user = users.get(userId);
        return user.getPassword().equals(oldPassword);
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

    /**
     * Get the userId based on the uesrName 
     * 
     * @param  userName userName of the user
     * @return userId   userid of the user based on the username 
     */    
    public String getUserIdByUserName(String userName) {
        Map<String, User> users = userDao.getUsers();
        String userId = "";

        for (User user : users.values()) {
            if (user.getProfile().getUserName().equals(userName)) {
                 userId = user.getUserId();
                break;
            }
        }
        return userId;        
    } 
}