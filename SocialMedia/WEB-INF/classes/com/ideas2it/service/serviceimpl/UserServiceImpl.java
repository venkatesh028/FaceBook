package com.ideas2it.service.serviceimpl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.ideas2it.model.User;
import com.ideas2it.model.Profile;
import com.ideas2it.service.ProfileService;
import com.ideas2it.service.serviceimpl.ProfileServiceImpl;
import com.ideas2it.service.UserService;
import com.ideas2it.dao.UserDao;
import com.ideas2it.dao.daoImpl.UserDaoImpl;
import com.ideas2it.constant.Constants;
import com.ideas2it.exception.CustomException;

/**
 * It implements the logic of Update, delete, create, 
 * read and validation operation for the user
 *
 * @version 1.0 22-SEP-2022
 * @author Venkatesh TM
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private ProfileService profileService;
    public UserServiceImpl() {
        userDao = new UserDaoImpl();
        profileService = new ProfileServiceImpl();
    }    

    public boolean create(User user, Profile profile) throws CustomException {
        String id;
        boolean isCreated;
        id = UUID.randomUUID().toString();
        user.setId(id);
        profile.setUserId(id);  
        isCreated = (userDao.create(user) > 0); 
        profileService.create(profile);
        return isCreated;
    }
   
    public boolean update(User user) throws CustomException {
        return ( 0 < userDao.update(user));
    }

    public boolean delete(String userId) throws CustomException {        
        return ( 0 < userDao.delete(userId)); 
    }

    public User getById(String userId) throws CustomException {
        return userDao.getUser(userId);
    }    


    public String getUserId(String email) throws CustomException {
        return userDao.getId(email);
    }
 
    public boolean isEmailExist(String email) throws CustomException {
        List<String> existingEmail = userDao.getExistingEmails();
        return existingEmail.contains(email);
    }
    

    public boolean isValidCredentials(String email, String password) throws CustomException { 
        String validPassword = userDao.getPassword(email);
        return  validPassword.equals(password);       
    }
            

    public boolean isPasswordMatches(String email, String oldPassword) throws CustomException {   
        String validPassword = userDao.getPassword(email);   
        return validPassword.equals(oldPassword);
    }

    public int calculateAge(LocalDate dateOfBirth) {        
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(dateOfBirth, currentDate);
        return age.getYears();    
    }
   
}