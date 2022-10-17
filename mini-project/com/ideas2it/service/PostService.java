package com.ideas2it.service;

import java.util.UUID;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

import com.ideas2it.model.Comment;
import com.ideas2it.model.Like;
import com.ideas2it.model.Post;
import com.ideas2it.dao.PostDao;
import com.ideas2it.dao.daoImpl.PostDaoImpl;

/**
 * Perform the add post, like and comment for the post 
 * 
 * @version 1.0 23-SEP-2022
 * @author Venkatesh TM
 */
public class PostService {
    private PostDao postDao;
    
    public PostService() {
        this.postDao = PostDaoImpl.getInsatance();
    }
    
    /**
     * Check the post is empty
     * 
     * @return boolean true or false based on the result
     */
    public boolean isPostEmpty() {
        List<Post> userPost;
        userPost = postDao.getUserPost();
        return userPost.isEmpty();
    }
    
    /**
     * Add the post 
     * 
     * @param  postedBy name of the user who posted the post
     * @parma  quotes   post of the user 
     * @return boolean  true after adding the post
     */
    public boolean addPost(String postedBy, String quotes) {
        Post post; 
        String postId;
        postId = UUID.randomUUID().toString();
        post = new Post(postId, postedBy, quotes);
        return postDao.addPost(post);  
    }
    
    /**
     * Add like to the post based on id of the post
     * 
     * @param  likedUserName userName of the user who like 
     * @param  postId        Id of the post
     * @return boolean       true after adding the like 
     */ 
    public boolean addLike(String likedUserName, String postId) {
        List<Post> userPost = postDao.getUserPost();          
      
        for (Post post : userPost) {
            if (post.getPostId().equals(postId)) {   
                if (isUserLikedAlready(likedUserName, post)) {
                    /*for (Like like : post.getLikedUsers()) {
                        if (like.getLikedUserName().equals(likedUserName)){
                            post.getLikedUsers().remove(like);       
                         }  
                    } */   
                    
                    for (int i = 0; i <= post.getLikedUsers().size(); i++) {
                        if (post.getLikedUsers().get(i).getLikedUserName().equals(likedUserName)) {
                            post.getLikedUsers().remove(i);
                        }
                    }    

                } else {
                    Like newLike = new Like(likedUserName);
                    post.setLike(newLike);                
                }                    
            }
        }
        return postDao.updatePost(userPost);   
    }
    
    /**
     * gets the userName who liked the post
     *
     * @param  postId - id of the post
     * @return likedUserName userName of the people who liked for the post
     */
    public String getLikedUsers(String postId) {
        StringBuffer likedUserName = new StringBuffer();
        List<Post> listOfPost;
        Post userPost = null;
        
        listOfPost = postDao.getUserPost();
        
        for (Post post : listOfPost) {
            if (post.getPostId().equals(postId)) {
                userPost = post;
            }      
        } 
        
        if (null != userPost.getLikedUsers()) {
            for (Like like : userPost.getLikedUsers()){
                likedUserName.append("\n")
                             .append(like.getLikedUserName());
            }   
        } else {
            likedUserName.append("NO One liked yet");
        }  
        return likedUserName.toString();    
    }  
    
    /**
     * Add comment to the particular post based on the postId
     * 
     * @param  postId  id of the post
     * @param  comment comment entered by the user
     * @return boolean true or false based on the result
     */
    public boolean addComment(String postId, String commentBy, String userComment) {
        List<Post> userPost;
        String commentId;
        userPost = postDao.getUserPost();
        commentId = UUID.randomUUID().toString();

        for (Post post : userPost) {
            if (post.getPostId().equals(postId)) {
                Comment comment = new Comment(commentId, commentBy, userComment);
                post.setComment(comment);
                break;
            }
        }
        return postDao.updatePost(userPost);       
    }
    
    /**
     * Get Comments for the particular post 
     * 
     * @param  postId - id of the particualr post 
     * @return userComments - comments for the particular post
     */
    public List<String> getComments(String postId) {
        List<Post> userPost;
        List<String> userComments = new ArrayList<>();
        StringBuilder comments = new StringBuilder();
        userPost = postDao.getUserPost();

        for (Post post : userPost) {
            if (post.getPostId().equals(postId)) {
                for (Comment comment : post.getComments()) {
                    comments.append("\n").append("userName : ")
                           .append(comment.getCommentedBy()).append("\n")
                           .append(comment.getComment());
                    userComments.add(comments.toString());
                    comments.delete(0,comments.length());
                }
            }
        }
        return userComments;
    }
    

    /**
     * Get the post uploaded by the user
     *
     * @return post list of post
     */
    public String getUserPost() {
        StringBuilder postMessage = new StringBuilder();
        List<Post> userPost;
        userPost = postDao.getUserPost();

        for (Post post : userPost) {
            
            postMessage.append("\n").append(post);
        }
        return postMessage.toString();
    }
    
    /**
     * Gets the post based on there userName 
     * 
     * @param  userName       userName of the user
     * @return postByUserName post of the particular user based on username
     */
    public String getPostByUserName(String userName) {
        List<Post> userPost;
        userPost = postDao.getUserPost();
        StringBuilder postByUserName = new StringBuilder();
        Post post;

        for (int index = 0; index < userPost.size(); index++) {
            post = userPost.get(index);
            if (post.getPostedBy().equals(userName)) {
                postByUserName.append(post);        
            }
        }
        return postByUserName.toString();
    }
    
    /**
     * Delete the particular post of the user
     * Based on the postId
     * 
     * @param  postId  Id of the post
     * @return result  true or false 
     */
    public boolean deletePost(String postId) { 
        List<Post> userPost;
        Post post; 
        userPost = postDao.getUserPost();
        boolean result = false;
        for (int index = 0; index < userPost.size(); index++) {
            post = userPost.get(0);
            if (post.getPostId().equals(postId)) {
                result = postDao.deletePost(index);
                break;
            }
        }
        return result;
    }
    
    private boolean isUserLikedAlready(String likedUserName, Post post) {
        boolean isAlreadyLiked = false;
        if (null != post.getLikedUsers()) {
        for (Like like : post.getLikedUsers()) {
            if (like.getLikedUserName().equals(likedUserName)){                
                isAlreadyLiked = true;          
            }  
        }
        }
        return isAlreadyLiked; 
    } 
}