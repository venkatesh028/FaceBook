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
    
    /**
     * Create the profileId for the profile and set that
     * and add profile to the database 
     * 
     * @param  profile - profile contain the details of the profile 
     * @return profileId - profileId of the profile
     */
    public Profile create(Profile profile) {
        return profileService.create(profile);
    }
    
    /**
     * Gets the profile id based on the userId
     * 
     * @param  userId - userid of the user 
     * @return profileId - profile id of the user based on the userId
     */
    public String getProfileId(String userId) {
        return profileService.getProfileId(userId);
    }    

    /**
     * Gets the profileId based on the username 
     * 
     * @param  userName  - userName of the profile 
     * @return profileId - profileId of the profile
     */
    public String getProfileIdByUserName(String userName) {
        return profileService.getProfileIdByUserName(userName);
    }

    /**
     * Gets the userId of the profile based on the profile id
     * 
     * @param  profileId - profileId of the user
     * @return userId    - userId of the profile based on the profileId 
     */    
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

    /**
     * Deletes the profile based on the profileId
     *
     * @param  profileId - id of the profile which need to be deleted 
     * @return profile   - profile which got deleted 
     */
    public Profile delete(String profileId) {
        return profileService.delete(profileId);
    }
} 
