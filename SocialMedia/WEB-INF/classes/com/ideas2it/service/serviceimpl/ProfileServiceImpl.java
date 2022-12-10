package com.ideas2it.service.serviceimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.ideas2it.model.Profile;
import com.ideas2it.service.ProfileService;
import com.ideas2it.dao.ProfileDao;
import com.ideas2it.dao.daoImpl.ProfileDaoImpl;
import com.ideas2it.constant.Constants;
import com.ideas2it.exception.CustomException;

/** 
 * Implements the logic of Create, update, delete operation
 * for the user profile
 * 
 * @version 1.0 22-SEP-2022
 * @author  Venkatesh TM
 */
public class ProfileServiceImpl implements ProfileService {
    private Profile profile;
    private ProfileDao profileDao;    

    public ProfileServiceImpl() {
        this.profileDao = new ProfileDaoImpl();
    }
    
    /**
     * Creates the profileId for the profile and set that
     * and add profile to the database 
     * 
     * @param  profile - profile contain the details of the profile 
     * @return isCreated -  true or false based on the result
     */
    public boolean create(Profile profile) throws CustomException { 
        boolean isCreated;    
        String id = UUID.randomUUID().toString();
        profile.setId(id);
        isCreated = (profileDao.create(profile) > 0) ? true : false;
        return isCreated;    
    }

    /**
     * Gets the profile of the user 
     *
     * @param  userId - id of the user
     * @return profile - profile details of the user
     */
    public Profile getProfile(String userId) throws CustomException {
        return profileDao.getProfile(userId);
    }
    
    /**
     * Updates the userName and bio of the profile
     * 
     * @param profile - details of the profile
     * @return boolean - true or false based on the result
     */
    public boolean update(Profile profile) throws CustomException {
        boolean isUpdated = (profileDao.update(profile) > 0 );
        return isUpdated; 
    }
    
    /** 
     * Increase the friend's count based on the user profile
     * 
     * @param userId - id of the user
     * @return isUpdated - true or false based on the result
     */
    public boolean updateFriendCount(String userId,
                                     int friendsCount) throws CustomException {
        boolean isUpdated;
        int friendCount;
        Profile profile = getProfile(userId);        
        profile.setFriendsCount(friendsCount);
        isUpdated = profileDao.update(profile) > 0;
        return isUpdated;
    }

    /**
     * Deletes the profile based on the profileId
     *
     * @param  userId - id of the user which need to be deleted 
     * @return profile   - profile which got deleted 
     */
    public boolean delete(String userId) throws CustomException {
        boolean isDeleted;
        isDeleted = (0 < profileDao.delete(userId));
        return isDeleted;
    }  
    
    /**
     * Gets the profile based on the username
     *  
     * @param  userName - username of the user
     * @return profile - details of the user
     */
    public Profile getUserProfileByUserName(String userName) 
                                            throws CustomException {
        Profile profile = profileDao.getUserProfileByUserName(userName);

        if (null == profile) {
            throw new CustomException(Constants.ERROR_02);    
        }
        return profile;
    }

    /**
     * Check the userName is exist already
     * 
     * @param  userName userName entered by the user
     * @return boolean  true or false based on the result
     */
    public boolean isUserNameExist(String userName) throws CustomException {
        List<String> existingUserNames = profileDao.getExistingUserNames();
        return existingUserNames.contains(userName);
    }
    
    
    public boolean updateVisibility(String userId,
                                    String visibility_status) throws CustomException {
        Profile profile = getProfile(userId);
        profile.setVisibility(visibility_status);
        return update(profile);
    }
}