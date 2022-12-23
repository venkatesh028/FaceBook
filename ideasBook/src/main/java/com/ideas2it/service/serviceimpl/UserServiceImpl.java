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
 * @version 1.0 09-DEC-2022
 * @author Venkatesh TM
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private ProfileService profileService;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
        profileService = new ProfileServiceImpl();
    } 
   
    /**
     * {@inheritDoc}
     */
    @Override
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
   
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(User user) throws CustomException {
        return ( 0 < userDao.update(user));
    }

    /**
     * {@inheritDoc}
     */
    @Override    
    public boolean updatePassword(User user) throws CustomException {
        return (0 < userDao.updatePassword(user));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(User user) throws CustomException {        
        return ( 0 < userDao.delete(user)); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User getById(String userId) throws CustomException {
        return userDao.getUser(userId);
    }    

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUserId(String email) throws CustomException {
        return userDao.getId(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    public boolean isEmailExist(String email) throws CustomException {
        List<String> existingEmail = userDao.getExistingEmails();
        return existingEmail.contains(email);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidCredentials(String email, String password) throws CustomException { 
        boolean isValid = false;
        String id = userDao.getIdByEmailAndPassword(email, password);  
        
        if (null != id) {
            isValid = true;
        }
        return isValid;        
    }
            
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPasswordMatches(String email, String oldPassword) throws CustomException {   
        return isValidCredentials(email, oldPassword); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int calculateAge(LocalDate dateOfBirth) {        
        LocalDate currentDate = LocalDate.now();
        Period age = Period.between(dateOfBirth, currentDate);
        return age.getYears();    
    }    
}