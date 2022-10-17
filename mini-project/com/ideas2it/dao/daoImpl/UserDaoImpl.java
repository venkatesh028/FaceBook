package com.ideas2it.dao.daoImpl;

import java.util.Map;
import java.util.HashMap;

import com.ideas2it.model.User;
import com.ideas2it.model.Profile;
import com.ideas2it.dao.UserDao;

/**
 * Perform the creation and delete operation for the user account 
 * 
 * @version 1.0 22-SEP-2022
 * @author  Venkatesh TM
 */
public class UserDaoImpl implements UserDao {
    private Map<String, User> users;
    private Map<String, String> loginCredentials;
    private User user;
    private static  UserDaoImpl userDaoImpl;

    private UserDaoImpl() {
        this.users = new HashMap<>();
        this.loginCredentials = new HashMap<>();
    }

   /**
    * Creating the obj for the UserDaoImpl only for one time 
    *
    * @return userDao userDaoImpl is the object of the userDao
    */
    public static synchronized UserDaoImpl getInsatance() {
        if (userDaoImpl == null) {
            userDaoImpl = new UserDaoImpl();
        }
        return userDaoImpl;
    }	
    
    /**
     * {@inheritDoc}
     */
    @Override
    public User create(User user) {        
        loginCredentials.put(user.getEmail(), user.getUserId());
        return users.put(user.getUserId(), user);        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(String userId) {
        users.remove(userId);
        return true;    
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, User> getUsers() {
        return users;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public User getById(String userId) {
        return users.get(userId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Profile getProfile(String userId) {
        return users.get(userId).getProfile();
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    public Map<String, String> getLoginCredentials() {
        return loginCredentials;    
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    public User update(String userId, User user) {        
        return users.put(userId, user);   
    }

    /**
     * {@inheritDoc}
     */
    @Override   
    public String getUserName(String userId) {
        user = users.get(userId);
        return user.getProfile().getUserName();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override   
    public String updateLoginCredentials(String oldEmail, String newEmail) {
        String userId;
        userId = loginCredentials.remove(oldEmail);        
        return loginCredentials.put(newEmail, userId);        
    }
   
}