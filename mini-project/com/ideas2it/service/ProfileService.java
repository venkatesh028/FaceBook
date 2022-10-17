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
        this.profileDao = ProfileDaoImpl.getInstance();
    }
    
    /**
     * Create the profileId for the profile and set that
     * and add profile to the database 
     * 
     * @param  profile - profile contain the details of the profile 
     * @return profileId - profileId of the profile
     */
    public Profile create(Profile profile) {
        String profileId;
        
        profileId = UUID.randomUUID().toString();
        profile.setProfileId(profileId);
        return profileDao.add(profile);    
    }

    /**
     * get the profile of the user 
     *
     * @param  profileId - profileId of the user
     * @return profile profile details of the user
     */
    public Profile getProfile(String profileId) {
        return profileDao.getProfile(profileId);
    }
    
    /**
     * Update the username of the user
     *
     * @param  profileId   profileId of the user
     * @param  newUserName new username of the user
     * @return boolean     true after the userName update
     */
    public boolean updateUserName(String profileId, String newUserName) {
        profile = profileDao.getProfile(profileId);
        profile.setUserName(newUserName);
        profileDao.update(profile);
        return true;     
    }
    
    /**
     * Update bio of the user  
     * 
     * @param  profileId - profileId of the user
     * @param  bio     bio of the user 
     * @return boolean true after update
     */
    public boolean updateBio(String profileId, String bio) {
        profile = profileDao.getProfile(profileId);
        profile.setBio(bio);
        return true;    
    } 
    
    /**
     * Get the username of the user
     * 
     * @param  profileId    profileId  of the user
     * @return userName username of the user 
     */
    public String getUserName(String profileId) {
        Profile profile = profileDao.getProfile(profileId);
        return profile.getUserName();
    }

    /**
     * Gets the userprofile based on the username
     *
     * @param  userName     username searched by the user
     * @return usersProfile userprofile based on the userName
     */ 
    public Profile getUserProfile(String userName) { 
        List<Profile> profiles = new ArrayList<>(profileDao.getProfiles());
        
        for (Profile profile : profiles) {
            if (profile.getUserName().equals(userName)) {
                return profile;
            }
        }
        return null;  
    }
    
    /**
     * Chagne the visibility of the user from the public or private 
     * Based on the user request
     *
     * @param  profileId   profileId  of the particular user
     * @param  boolean true or false based on the user selection
     * @return boolean true after changing the visibility
     */
    public boolean changeVisibility(String profileId, boolean isPrivate) {
        profile = profileDao.getProfile(profileId );
        profile.setPrivate(isPrivate);
        profileDao.update(profile);
        return true;
    }
    
    /**
     * Add friend name to the particular user 
     * 
     * @param  profileId      profileId  of the user to who the name going to get add
     * @parma  friendName requested user name 
     * @return boolean    true after adding the name
     */
    public boolean addFriend(String profileId , String friendName) {
        profile = profileDao.getProfile(profileId );
        profile.setFriend(friendName);
        profileDao.update(profile);
        return true;
    }

    /**
     * Check the userName is exist already
     * 
     * @param  userName userName entered by the user
     * @return boolean  true or false based on the result
     */
    public boolean isUserNameExist(String userName) {
        Set<String> existingData =  new HashSet<>();
        List<Profile> profiles;
        profiles = profileDao.getProfiles();

        for (Profile profile : profiles) {
            existingData.add(profile.getUserName());        
        }
        return existingData.contains(userName);
    }
    
    /**
     * Gets the profile id based on the userId
     * 
     * @param  userId - userid of the user 
     * @return profileId - profile id of the user based on the userId
     */
    public String getProfileId(String userId) {
        List<Profile> profiles = new ArrayList<>(profileDao.getProfiles());
        String profileId = null;

        for (Profile profile : profiles) {
            if (profile.getUserId().equals(userId)) {
                profileId = profile.getProfileId();
            }
        } 
        return profileId; 
    } 
    
    /**
     * Gets the profileId based on the username 
     * 
     * @param  userName  - userName of the profile 
     * @return profileId - profileId of the profile
     */
    public String getProfileIdByUserName(String userName) {
        List<Profile> profiles = new ArrayList<>(profileDao.getProfiles());
        String profileId = null;

        for (Profile profile : profiles) {
            if (profile.getUserName().equals(userName)) {
                profileId = profile.getProfileId();
            }
        } 
        return profileId; 
    } 
      
    /**
     * Gets the userId of the profile based on the profile id
     * 
     * @param  profileId - profileId of the user
     * @return userId    - userId of the profile based on the profileId 
     */
    public String getUserId(String profileId) {
        List<Profile> profiles = new ArrayList<>(profileDao.getProfiles());
        String userId = null;

        for (Profile profile : profiles) {
            if (profile.getProfileId().equals(profileId)) {
                userId = profile.getUserId();
            }
        } 
        return userId;    
    }
    
    /**
     * Deletes the profile based on the profileId
     *
     * @param  profileId - id of the profile which need to be deleted 
     * @return profile   - profile which got deleted 
     */
    public Profile delete(String profileId) {
        return profileDao.delete(profileId) ;
    }   
}