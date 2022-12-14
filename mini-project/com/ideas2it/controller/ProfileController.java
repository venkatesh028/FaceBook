package com.ideas2it.controller;

import com.ideas2it.service.ProfileService;
import com.ideas2it.model.Profile;
import com.ideas2it.exception.CustomException;
import com.ideas2it.logger.CustomLogger;

/**
 * Implemtens create, get, update and delete operation for the profile
 *
 * @version 1.0 23-SEP-2022
 * @author  Venkatesh TM
 */
public class ProfileController {
    ProfileService profileService;
    private CustomLogger logger;

    public ProfileController() {
        this.profileService = new ProfileService();
        this.logger = new CustomLogger(ProfileController.class);
    }
    
    /**
     * Creates the profile
     * 
     * @param  profile - profile contain the details of the profile 
     * @return boolean - true or false based on the response
     */
    public boolean create(Profile profile) {
        return profileService.create(profile);
    }
    
    /**
     * Shows the profile of the user
     * 
     * @param  userId -  userId of the user
     * @return Profile - profile of the user
     */
    public Profile getProfile(String userId) {
        return profileService.getProfile(userId);
    } 
    
    /**
     * Updates the profile
     * 
     * @param profile - details of the profile
     * @return boolean - true or false based on the response
     */
    public boolean update(Profile profile) {
        return profileService.update(profile);
    }

    /**
     * Deletes the profile based on the userId
     *
     * @param  userId - id of the user which need to be deleted 
     * @return boolean - true or false based on the response 
     */
    public boolean delete(String userId) {
        return profileService.delete(userId);
    }    
    
    /**
     * Gets the profileId based on the username 
     * 
     * @param  userName  - userName of the profile 
     * @return profile - profile of the profile
     */
    public Profile getProfileByUserName(String userName) { 
        Profile profile = null;
         
        try {
            profile = profileService.getUserProfileByUserName(userName); 
        } catch (CustomException customException) { 
            logger.error(customException.getMessage());
        }
        return profile;
    }
    
    /**
     * Check is the username is exist or not
     * 
     * @param  userName username given by the user
     * @return boolean  true if the name is not exist else false
     */
    public boolean isUserNameExist(String username) {
        return profileService.isUserNameExist(username);
    }
      
    /**
     * Sets the profile as public 
     * 
     * @param  userId - id of the user
     * @return boolean - true or false based on the response
     */
    public boolean setPublic(String userId) {
        return profileService.setPublic(userId);
    }

    /**
     * Sets the profile as private
     * 
     * @param  userId - id of the user
     * @return boolean - true or false based on the response
     */
    public boolean setPrivate(String userId) {
        return profileService.setPrivate(userId);
    }
} 
