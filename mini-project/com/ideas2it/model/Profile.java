package com.ideas2it.model;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

import com.ideas2it.model.Post;

/**
 * Contain the attributes of the Profile
 * Constructor is used to initialize the attributes 
 * and getter and setter method  is used to update and retrive the attributes
 * tostring method is used to return the values
 *
 * @version 1.0 22-SEP-2022
 * @author Venkatesh TM
 */
public class Profile {
    private String id;
    private String userId;
    private String userName;
    private String bio;
    private int friendsCount;
    private List<Post> post; 
    private Set<String> friends = new HashSet<>();
    private boolean isPrivate= false;

    public Profile() {}

    public Profile(String userName) {
        this.userName = userName;
        this.friendsCount = 0;
    }
    
    public void setProfileId(String id) {
        this.id = id;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public void setBio(String bio) {
        this.bio = bio;
    }
    
    public void setFriendsCount() {
        this.friendsCount++;
    }
    
    public void setPost(List<Post> post) {
        this.post = post;
    }
    
    public void setFriend(String friendName) {
        this.friends.add(friendName);
    }
    
    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }
    
    public String getProfileId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
    
    public String getBio() {
        return bio;
    }
    
    public int getFriendsCount() {
        return friendsCount;
    }
    
    public Set<String> getFriends() {
        return friends;
    }
    
    public boolean getIsPrivate() {
        return isPrivate;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String toString() {
        StringBuilder profileMessage = new StringBuilder();
        profileMessage.append("\nUserName : ").append(userName)
                      .append("\nBio      : ").append(bio)
                      .append("\nFriends  : ").append(friendsCount)
                      .append("\nFriendsList : ").append(friends);
        return profileMessage.toString();
    }
}  