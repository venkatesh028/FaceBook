package com.ideas2it.service;

import java.util.UUID;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

import com.ideas2it.dao.ProfileDao;
import com.ideas2it.dao.daoImpl.ProfileDaoImpl;
import com.ideas2it.model.Profile;

/** 
 * Perform the Create, update, delete taks for the user profile
 * 
 * @version 1.0 22-SEP-2022
 * @author  Venkatesh TM
 */
public class ProfileService {
    private Profile profile;
    private ProfileDao profileDao;    

    public ProfileService() {
        this.profileDao = new ProfileDaoImpl();
    }
    
    /**
     * Creates the profileId for the profile and set that
     * and add profile to the database 
     * 
     * @param  profile - profile contain the details of the profile 
     * @return isCreated -  true or false based on the result
     */
    public boolean create(Profile profile) {
        String id;   
        boolean isCreated;    
        id = UUID.randomUUID().toString();
        profile.setId(id);
        isCreated = (profileDao.create(profile) > 0)? true:false;
        return isCreated;    
    }

    /**
     * gets the profile of the user 
     *
     * @param  profileId - profileId of the user
     * @return profile - profile details of the user
     */
    public Profile getProfile(String userId) {
        return profileDao.getProfile(userId);
    }
    
    /**
     * Updates the userName and bio of the profile
     * 
     * @param profile - details of the profile
     * @return boolean - true or false based on the result
     */
    public boolean update(Profile profile) {
        boolean isUpdated;
        isUpdated = (profileDao.update(profile) > 0 )? true : false;
        return isUpdated; 
    }

    /**
     * Deletes the profile based on the profileId
     *
     * @param  profileId - id of the profile which need to be deleted 
     * @return profile   - profile which got deleted 
     */
    public boolean delete(String userId) {
        boolean isDeleted;
        isDeleted = (profileDao.delete(userId) > 0) ? true : false;
        return isDeleted;
    }  
    
    /**
     * Gets the profile based on the username
     *  
     * @param  userName - username of the user
     * @return profile - details of the user
     */
    public Profile getUserProfileByUserName(String userName) {
        Profile profile = profileDao.getUserProfileByUserName(userName);
        return profile;
    }

    /**
     * Check the userName is exist already
     * 
     * @param  userName userName entered by the user
     * @return boolean  true or false based on the result
     */
    public boolean isUserNameExist(String userName) {
        List<String> existingUserNames = profileDao.getExistingUserNames();
        return existingUserNames.contains(userName);
    }
}