package com.ideas2it.dao;

import java.util.List;
import com.ideas2it.model.Post;
import com.ideas2it.model.Like;

/**
 * Perform add, update and get function for the post
 * 
 * @version 1.0 22-SEP-2022
 * @author Venkatesh TM
 */
public interface PostDao {

    /**
     * Add the post 
     * 
     * @param  postedBy name of the user who posted the post
     * @param  quote  post of the user 
     * @return boolean  true after adding the post
     */
    public boolean addPost(Post post);    

    /**
     * Get all the post in the list
     * 
     * @return userPost list of post uploaded
     */
    public List<Post> getUserPost(); 
    
    /** 
     * Delete the post based on the index 
     *
     * @param  index  position of the post in the list
     * @return bolean true after deleting the post
     */
    public boolean deletePost(int index);    
    
    /** 
     * Update the post this update include the like and comment
     *
     * @param  userPost list of the post 
     * @return boolean  true after updating
     */
    public boolean updatePost(List<Post> userPost);
    
}