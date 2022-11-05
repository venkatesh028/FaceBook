package com.ideas2it.dao;

import java.util.List;
import com.ideas2it.model.Friend;

public interface FriendDao {
    public int create(Friend friend);
    public int delete(Friend friend);
    public List<String> getFriends(String userId);
}