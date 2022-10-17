package com.ideas2it.view;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;

import com.ideas2it.controller.PostController;
import com.ideas2it.controller.ProfileController; 
import com.ideas2it.constant.Constants;
import com.ideas2it.logger.CustomLogger;

/**
 * Shows the news feed page to user and based on is action shows further pages
 *
 * @version 1.0 22-SEP-2022
 * @author  Venkatesh TM
 */ 
public class PostView {
    private PostController postController;
    private Scanner scanner;
    private ProfileController profileController;
    private CustomLogger logger;

    public PostView() {
        this.postController = new PostController();
        this.profileController = new ProfileController();
        this.scanner = new Scanner(System.in);
        this.logger = new CustomLogger(PostView.class);
    }
    
    /** 
     * Add the post by getting the quotes form the user
     * 
     * @param userName - userName of the person who is uploading the post
     */ 
    private void addPost(String profileId) {
        String quotes;
        String userName;
        System.out.print("Enter your quotes : ");
        quotes = scanner.nextLine();
        userName = profileController.getUserName(profileId);

        if (postController.addPost(userName, quotes)) {
            logger.info("Post added Successfully");
        }        
    }
    
    /** 
     * Add like to the post by getting the details about that post
     * 
     * @param likedUserName userName of the person who liked the post
     */
    private void addLike(String profileId) {
        String likedUserName = profileController.getUserName(profileId);
        System.out.println("Enter the post Id : ");
        String postId = scanner.nextLine();        
        postController.addLike(likedUserName, postId);                   
    }

    /**
     * gets the userName who liked the post
     */
    private void showLikedUsers() {
        String postId;
        System.out.println("Enter the PostId : ");
        postId = scanner.nextLine();
        System.out.println(postController.getLikedUsers(postId));
    }
    
    /**
     * Add comment to the post be getting the details about that post
     * 
     * @param userID - id of the user who comment
     */
    private void addComment(String profileId) {
        String postId;
        String comment;
        String commentedBy;

        System.out.print("Enter the post Id : ");
        postId = scanner.nextLine();   
        System.out.print("Enter your comment : ");
        comment = scanner.nextLine();
        commentedBy = profileController.getUserName(profileId);

        if (postController.addComment(postId, commentedBy, comment)) {
            logger.info("Comment added successfully ");
        }      
    }
    
    /**
     * Shows the comments for the particular post
     * 
     * @param userId - userId for the post
     */
    private void showComments(String profileId) {
        String postId;
        List<String> comments;
        boolean isGoBack = false;
        
        System.out.print("Enter the Post Id : ");
        postId = scanner.nextLine();
        comments = postController.getComments(postId);
        System.out.println(comments);

    } 
    
    /**
     * Shows the post to the user and also provide the option to
     * Add post, like and comment 
     *
     * @param userId userId of the user who is in this page
     */
    public void displayPost(String profileId) {      
        String userName = profileController.getUserName(profileId);
        StringBuilder statement = new StringBuilder();
        int action;
        boolean postFeedRunning = true;
        statement.append("\nEnter ").append(Constants.ADDPOST)
                 .append(" --> To add post ").append("\nEnter ")
                 .append(Constants.LIKE).append(" --> To add like ")  
                 .append("\nEnter ").append(Constants.LIKEDUSERS)
                 .append(" --> To view likedusersName ")            
                 .append("\nEnter ").append(Constants.COMMENT)
                 .append(" --> To add comment ")
                 .append("\nEnter ").append(Constants.VIEW_COMMENTS)
                 .append(" --> To view Comments ")
                 .append("\nEnter ").append(Constants.EXIT_POSTPAGE)
                 .append(" --> To exit post feed : ");
    
        while (postFeedRunning) {        
            if (postController.isPostEmpty()) {
                System.out.println("Post is not available");    
            } else {
                System.out.println(postController.getUserPost());
            } 
            
            System.out.println(statement);
            action = getInput();

            switch (action) {
            case Constants.ADDPOST:
                addPost(profileId);
                break;

            case Constants.LIKE:
                addLike(profileId);
                break;
 
            case Constants.LIKEDUSERS:
                showLikedUsers();
                break;
      
            case Constants.COMMENT:
                addComment(profileId);
                break;

            case Constants.VIEW_COMMENTS:
                showComments(profileId);
                break;

            case Constants.EXIT_POSTPAGE:
                postFeedRunning = false;
                break;

            default:
                logger.warn("You entered wrong option");
            }    
        }
    }

    /**
     * Get the input from the user
     *
     * @return input input given by the user
     */
    private int getInput() {
        Scanner scanner = new Scanner(System.in);
        int input = 0;

        try {
            input = scanner.nextInt();    
        } catch(InputMismatchException e) {
            logger.error("Enter Only Number not String\n");
            return input;
        }
        return input;
    }
}