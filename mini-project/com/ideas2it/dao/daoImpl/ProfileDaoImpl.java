package com.ideas2it.dao.daoImpl;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import com.ideas2it.model.Profile;
import com.ideas2it.dao.ProfileDao;

/**
 * Perform the creation, Read , update and Delete for the profile 
 * 
 * @version 1.0 15-OCT-2022
 * @author  Venkatesh TM
 */
public class ProfileDaoImpl implements ProfileDao {
    private Map<String, Profile> profiles;
    private static ProfileDaoImpl profileDaoImpl;
    
    private ProfileDaoImpl() {
        this.profiles = new HashMap<>();
    }
    
    public static synchronized ProfileDaoImpl getInstance() {
        if (profileDaoImpl == null) {
            profileDaoImpl = new ProfileDaoImpl();        
        }
        return profileDaoImpl; 
    } 
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Profile add(Profile profile) {
        return profiles.put(profile.getProfileId(), profile);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Profile getProfile(String profileId) {
        return profiles.get(profileId);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Profile> getProfiles() {
        return new ArrayList<>(profiles.values());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Profile update(Profile profile) {
        return profiles.replace(profile.getProfileId(), profile);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Profile delete(String profileId) {
        return profiles.remove(profileId);   
    }      
}
