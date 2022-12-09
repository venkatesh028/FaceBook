package com.ideas2it.service;

import com.ideas2it.model.Profile;
import com.ideas2it.exception.CustomException;

public interface ProfileService {

    /**
     * Creates the profileId for the profile and set that
     * and add profile to the database 
     * 
     * @param  profile - profile contain the details of the profile 
     * @return isCreated -  true or false based on the result
     */
    public boolean create(Profile profile);

    /**
     * Gets the profile of the user 
     *
     * @param  userId - id of the user
     * @return profile - profile details of the user
     */
    public Profile getProfile(String userId);
    
    /**
     * Updates the userName and bio of the profile
     * 
     * @param profile - details of the profile
     * @return boolean - true or false based on the result
     */
    public boolean update(Profile profile);

    /** 
     * Increase the friend's count based on the user profile
     * 
     * @param userId - id of the user
     * @return isUpdated - true or false based on the result
     */
    public boolean updateFriendCount(String userId, int friendsCount);

    /**
     * Deletes the profile based on the profileId
     *
     * @param  userId - id of the user which need to be deleted 
     * @return profile   - profile which got deleted 
     */
    public boolean delete(String userId);

    /**
     * Gets the profile based on the username
     *  
     * @param  userName - username of the user
     * @return profile - details of the user
     */
    public Profile getUserProfileByUserName(String userName) throws CustomException;

    /**
     * Check the userName is exist already
     * 
     * @param  userName userName entered by the user
     * @return boolean  true or false based on the result
     */
    public boolean isUserNameExist(String userName);

    /**
     * Updates the visibility of the profile from public to private and vice versa
     * 
     * @param userId - id of the user 
     * @param visibility_status - status choosed buy the user
     * @return boolean true or false based on the response
     */
    public boolean updateVisibility(String userId, String visibility_status);    
  
}