package com.ideas2it.controller;

import java.util.List;

import com.ideas2it.model.Like;
import com.ideas2it.service.LikeService;

public class LikeController {
    
    LikeService likeService;

    public LikeController() {
        likeService = new LikeService();    
    }
    
    public boolean addLike(Like like) {
        return likeService.addLike(like);
    }
    
    public List<String> getLikedUserNames(String postId) {
        return likeService.getLikedUserNames(postId);
    } 
    
}