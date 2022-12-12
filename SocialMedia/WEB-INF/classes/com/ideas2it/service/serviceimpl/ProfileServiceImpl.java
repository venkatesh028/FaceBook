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
     * {@inheritDoc}
     */
    @Override
    public boolean create(Profile profile) throws CustomException { 
        boolean isCreated;    
        String id = UUID.randomUUID().toString();
        profile.setId(id);
        isCreated = (profileDao.create(profile) > 0) ? true : false;
        return isCreated;    
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Profile getProfile(String userId) throws CustomException {
        return profileDao.getProfile(userId);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(Profile profile) throws CustomException {
        boolean isUpdated = (profileDao.update(profile) > 0 );
        return isUpdated; 
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
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
     * {@inheritDoc}
     */
    @Override
    public boolean delete(String userId) throws CustomException {
        boolean isDeleted;
        isDeleted = (0 < profileDao.delete(userId));
        return isDeleted;
    }  
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Profile getUserProfileByUserName(String userName) 
                                            throws CustomException {
        Profile profile = profileDao.getUserProfileByUserName(userName);

        if (null == profile) {
            throw new CustomException(Constants.ERROR_02);    
        }
        return profile;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUserNameExist(String userName) throws CustomException {
        List<String> existingUserNames = profileDao.getExistingUserNames();
        return existingUserNames.contains(userName);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateVisibility(String userId,
                                    String visibility_status) throws CustomException {
        Profile profile = getProfile(userId);
        profile.setVisibility(visibility_status);
        return update(profile);
    }
}