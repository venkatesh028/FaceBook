package com.ideas2it.dao;

import java.util.List;

import com.ideas2it.model.Profile;

/**
 * It is interface for the profile dao impl it contains the method for
 * create, update, delete, get operations for profile
 *
 * @version 1.1 31-OCT-2022
 * @author Venkatesh TM
 */
public interface ProfileDao {  
    
    /** 
     * create profile to the database
     * 
     * @param  profile - profile of the user contain the profile details
     * @return noOfRowsAffected - based on the profile creation
     */
    public int create(Profile profile);

    /**
     * Deletes the profile of the user based on the profile id
     * 
     * @param profileId - profile id whose profile need to be deleted 
     * @return noOfRowsDeleted - based on the deletion response
     */
    public int delete(String userId);

    /**
     * Updates the profile details of the user
     * 
     * @param profile - profile with updated details 
     * @return noOfRowsUpdated - based on the updation
     */
    public int update(Profile profile);    
    
    /**
     * Gets the profile of based on the profile id
     *
     * @param profileId - profile id of that user
     * @return profile - profile details
     */
    public Profile getProfile(String userId);     
    
    /**
     * Gets the profile based on the profile id
     * 
     * @param userName - userName of the user
     * @return profile - profile of the user
     */
    public Profile getUserProfileByUserName(String userName);  
    
    /** 
     * Gets the list of username of the users 
     *
     * @return userNames list of existing usernames
     */
    public List<String> getExistingUserNames();  
    
    /**
     * Sets the profile as public
     * 
     * @param  userId - id of the user
     * @return noOfRowsUpdated - based on the updation
     */
    public int setPublic(String userId); 

    /**
     * Sets the profile as private
     * 
     * @param  userId - id of the user
     * @return noOfRowsUpdated - based on the updation
     */
    public int setPrivate(String userId) ;
}
