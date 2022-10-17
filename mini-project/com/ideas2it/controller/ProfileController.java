package com.ideas2it.controller;

import com.ideas2it.service.ProfileService;
import com.ideas2it.model.Profile;

/**
 * Perform the set, get and update operation for the profile
 *
 * @version 1.0 23-SEP-2022
 * @author  Venkatesh TM
 */
public class ProfileController {
    ProfileService profileService;

    public ProfileController() {
        this.profileService = new ProfileService();
    }
    

    public Profile create(Profile profile) {
        return profileService.create(profile);
    }
    
    public String getProfileId(String userId) {
        return profileService.getProfileId(userId);
    }
    
    public String getProfileIdByUserName(String userName) {
        return profileService.getProfileIdByUserName(userName);
    }
     
    public String getUserId(String profileId) {
        return profileService.getUserId(profileId);
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
     * Shows the profile of the user
     * 
     * @param  userId  userId of the user
     * @return Profile profile of the user
     */
    public Profile getProfile(String userId) {
        return profileService.getProfile(userId);
    } 

    /**
     * Update the username of the user
     *
     * @param  userId      userId of the user
     * @param  newUserName new username of the user
     * @return boolean     true after the userName update
     */
    public boolean updateUserName(String userId, String newUserName) {
        return profileService.updateUserName(userId, newUserName);
    }

    /**
     * Update bio of the user  
     * 
     * @param  userId  userId of the user
     * @param  bio     bio of the user 
     * @return boolean true after update
     */
    public boolean updateBio(String userId, String bio) {
        return profileService.updateBio(userId, bio);
    }

    /**
     * Gets the userName of the user
     *
     * @param  userId   userId of the user
     * @return userName userName of the user
     */
    public String getUserName(String userId) {
        return profileService.getUserName(userId);
    }
    
    /**
     * Gets the userprofile based on the username
     *
     * @param  userName     username searched by the user
     * @return usersProfile userprofile based on the userName
     */
    public Profile getUserProfile(String userName) {
        return profileService.getUserProfile(userName);
    }
   
    /**
     * Changes the visibility of the profile 
     * 
     * @param userId userid of the user 
     * @param boolen true or based on the user request
     */
    public boolean changeVisibility(String userId, boolean isPrivate) {
        return profileService.changeVisibility(userId, isPrivate);
    }
    
    /**
     * Add friend name to the user based on the userId
     * 
     * @param userId     userId of the user
     * @param friendName username need to be added
     */ 
    public boolean addFriend(String userId, String friendName) {
        return profileService.addFriend(userId, friendName);     
    }

    public Profile delete(String profileId) {
        return profileService.delete(profileId);
    }
} 
