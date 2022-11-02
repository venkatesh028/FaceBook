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
     * Create the post 
     * 
     * @param  post    - entire details of the post
     * @return noOfRowsAffected - number of the rows based on the creation
     */
    public int create(Post post);  

    /** 
     * Update the post this update include the like and comment
     *
     * @param  id - id of the post need to be updated
     * @param  content - content of the post need to be updated
     * @return noOfRowsUpdated - number of the rows based on the updation
     */
    public int update(String id, String content);  
    
    public int updateLikeCount(String id, int likeCount);
    
    public int updateCommentCount(String id, int commentCount);

    /** 
     * Delete the post based on the id 
     *
     * @param  id - id os the post need to be deleted
     * @return noOfRowsDeleted -  number of the rows based on the deletion
     */
    public int delete(String id); 

    /**
     * Get all the post in the list
     * 
     * @return userPost list of post uploaded
     */
    public List<Post> getUserPosts();      
    
    /**
     * Gets the post of the particular user based on the id 
     *
     * @param  userId    - id of the user
     * @return postOfParticualrUser - posts of the particular user
     */
    public List<Post> getPostOfParticularUser(String userId);  
}