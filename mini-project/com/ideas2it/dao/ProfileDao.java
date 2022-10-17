package com.ideas2it.dao;

import java.util.List;
import com.ideas2it.model.Profile;

/**
 * Performs create, get, update, delete funtionality of the profile
 *
 * @version 1.0 15-OCT-2022
 * @author Venkatesh TM
 */
public interface ProfileDao {  
    
    /** 
     * Add profile to the database
     * 
     * @param  profile - profile of the user contain the profile details
     * @return profile - return null if the profile is created
     */
    public Profile add(Profile profile);
    
    /**
     * Gets the profile of based on the profile id
     *
     * @param profileId - profile id of that user
     * @return profile - profile details
     */
    public Profile getProfile(String profileId); 
    
    /**
     * Updates the profile details of the user
     * 
     * @param profile - profile with updated details 
     * @param profile - if id is there old details of the user else null
     */
    public Profile update(Profile profile);
    
    /**
     * Gets the all of profiles 
     * 
     * @return listOfProfiles - all the profiles
     */
    public List<Profile> getProfiles();
    
    /**
     * Deletes the profile of the user based on the profile id
     * 
     * @param profileId - profile id whose profile need to be deleted 
     * @param profile   - deleted profile 
     */
    public Profile delete(String profileId);
   
}
