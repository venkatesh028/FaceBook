package com.ideas2it.dao;

import java.util.List;

import com.ideas2it.model.Profile;
import com.ideas2it.exception.CustomException;

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
    public int create(Profile profile) throws CustomException;

    /**
     * Deletes the profile of the user based on the profile id
     * 
     * @param profileId - profile id whose profile need to be deleted 
     * @return noOfRowsDeleted - based on the deletion response
     */
    public int delete(String userId) throws CustomException;

    /**
     * Updates the profile details of the user
     * 
     * @param profile - profile with updated details 
     * @return noOfRowsUpdated - based on the updation
     */
    public int update(Profile profile) throws CustomException;    
    
    /**
     * Gets the profile of based on the profile id
     *
     * @param profileId - profile id of that user
     * @return profile - profile details
     */
    public Profile getProfile(String userId) throws CustomException;     
    
    /**
     * Gets the profile based on the profile id
     * 
     * @param userName - userName of the user
     * @return profile - profile of the user
     */
    public Profile getUserProfileByUserName(String userName) throws CustomException;  
    
    /** 
     * Gets the list of username of the users 
     *
     * @return userNames list of existing usernames
     */
    public List<String> getExistingUserNames() throws CustomException; 
}
