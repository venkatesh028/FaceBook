package com.ideas2it.dao;

import java.util.List;
import com.ideas2it.model.Profile;

public interface ProfileDao {  

    public Profile create(Profile profile);

    public Profile getProfile(String profileId); 
    
    public Profile update(Profile profile);

    public List<Profile> getProfiles();
    
    public Profile delete(String profileId);
   
}
