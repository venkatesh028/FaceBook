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
     * Update the Email of the user
     * 
     * @param userId   id of the user
     * @parma newEmail updated email of the user
     * @retun boolean  true or false based on the response
     */
    public boolean updateEmail(String userId, String newEmail) {
        return (userDao.updateEmail(userId, newEmail) > 0 )? true : false;        
    }

    /**
     * Updates the password of the user
     *
     * @param userId - id of the user
     * @param newPassword - new password 
     * @return boolean  true or false based on the response
     */
    public boolean updatePassword(String userId, String newPassword) {
        return (userDao.updatePassword(userId, newPassword) > 0) ? true : false;
    }

    /**
     * Updates the Dateofbirth and age of the user
     *
     * @param userId - id of the user
     * @param dateOfBirth - dateOfBirth entered by the user
     */
    public boolean updateDateOfBirthAndAge(String userId, LocalDate dateOfBirth, int age) {
        return (userDao.updateDateOfBirthAndAge(userId, dateOfBirth, age) > 0);        
    } 
    
    /**
     * Updates the phone number of the user
     * 
     * @param  userId - id of the user
     * @param  phoneNumber - phone number of the user
     * @return boolean true or false based on the response
     */
    public boolean updatePhoneNumber(String userId, long phoneNumber) {
        return (userDao.updatePhoneNumber(userId, phoneNumber) > 0) ? true : false;
    }

    /** 
     * Get the user Based on th id
     *
     * @param  userId userid of the user
     * @return user   user 
     */ 
    public User getById(String userId) {
        return userDao.getUser(userId); 
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
        return userDao.getPassword(email).equals(password);        
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
}