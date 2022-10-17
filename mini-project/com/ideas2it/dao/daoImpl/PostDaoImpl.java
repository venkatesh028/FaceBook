package com.ideas2it.dao.daoImpl;

import java.util.List;
import java.util.ArrayList;

import com.ideas2it.model.Post;
import com.ideas2it.dao.PostDao;

/**
 * Perform add, update and get function for the post
 * 
 * @version 1.0 22-SEP-2022
 * @author Venkatesh TM
 */
public class PostDaoImpl implements PostDao {
    private List<Post> userPost;
    private static PostDaoImpl postDaoImpl;

    private PostDaoImpl() {
        this.userPost = new ArrayList<>();
    }
    
    /**
     * Creating the obj for the PostDao only for one time 
     *
     * @return postDao postDao is the object of the PostDao
     */
    public static synchronized PostDaoImpl getInsatance() {
        if (postDaoImpl == null) {
            postDaoImpl = new PostDaoImpl();
        }
        return postDaoImpl;
    }

    /**
     * {@inheritDoc}
     */
    @Override  
    public boolean addPost(Post post) {        
        return userPost.add(post);   
    }

    /**
     * {@inheritDoc}
     */    
    @Override
    public List<Post> getUserPost() {
        return userPost;
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    public boolean updatePost(List<Post> userPost) {
        this.userPost = userPost;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deletePost(int index) {
        userPost.remove(index);
        return true;
    } 
    
}