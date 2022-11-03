package com.ideas2it.view;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;

import com.ideas2it.controller.PostController;
import com.ideas2it.controller.ProfileController; 
import com.ideas2it.constant.Constants;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.model.Like;
import com.ideas2it.model.Comment;
import com.ideas2it.controller.LikeController;
import com.ideas2it.controller.CommentController;

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
    private LikeController likeController;
    private CommentController commentController;
    
    public PostView() {
        this.postController = new PostController();
        this.profileController = new ProfileController();
        this.scanner = new Scanner(System.in);
        this.likeController = new LikeController();
        this.commentController = new CommentController();
        this.logger = new CustomLogger(PostView.class);
    }
    
    /** 
     * Add the post by getting the quotes form the user
     * 
     * @param userName - userName of the person who is uploading the post
     */ 
    private void addPost(String userId) {
        String quotes;
        System.out.print("Enter your quotes : ");
        quotes = scanner.nextLine();

        if (postController.addPost(userId, quotes)) {
            logger.info("Post added Successfully");
        }    
    }
    
    /** 
     * Add like to the post by getting the details about that post
     * 
     * @param userId - id of the person who liked the post
     */
    private void addLike(String userId) {
        Like like;
        System.out.println("Enter the post Id : ");
        String postId = scanner.nextLine(); 
        like = new Like(userId, postId);       
        likeController.addLike(like);             
    }

    /**
     * gets the userName who liked the post
     */
    private void showLikedUsers() {
        String postId;
        boolean isExit = false;
        StringBuilder exitInstruction = new StringBuilder();
        exitInstruction.append("Enter ").append(Constants.EXIT_LIKED_USERS)
                       .append(" To Exit from Liked users page ");
        System.out.print("Enter the PostId : ");
        postId = scanner.nextLine();
        
        while (!isExit) {
            System.out.println(likeController.getLikedUserNames(postId));
            System.out.print(exitInstruction);
            isExit = (getOption() == 1);
        }
    }
    
    /**
     * Add comment to the post be getting the details about that post
     * 
     * @param userId - id of the user who comment
     */
    private void addComment(String userId) {
        String postId;
	String comment;  

        System.out.print("Enter the post Id : ");
        postId = scanner.nextLine();   
        System.out.print("Enter your comment : ");
        comment = scanner.nextLine();

        if (commentController.addComment(new Comment(postId, userId, comment))) {
            logger.info("Comment added successfully ");
        }    
    }
    
    /**
     * Shows the comments for the particular post
     * 
     * @param userId - userId for the post
     */
    private void showComments(String userId) {
        String postId;
        List<Comment> comments;
        boolean isExit = false;
        StringBuilder exitInstruction = new StringBuilder(); 
        exitInstruction.append("Enter ").append(Constants.EXIT_LIKED_USERS)
                       .append(" To Exit from comment users page ");

        System.out.print("Enter the Post Id : ");
        postId = scanner.nextLine();
        comments = commentController.getComments(postId);

        while (!isExit) {
            System.out.println(exitInstruction);
            System.out.println(comments); 
            isExit = (getOption() == 1);
        } 
    } 
    
    private void deleteComment(String userId) {
        String postId;
        String commentId;
        Comment comment;
        System.out.println("Enter the post Id : ");
        postId = scanner.nextLine();
        System.out.println("Enter the commen Id : ");
        commentId = scanner.nextLine();
        comment = commentController.getComment(commentId);
        
        if (comment.getCommentedUserId().equals(userId)) {
            comment.setId(commentId);
            comment.setPostId(postId);
            commentController.deleteComment(comment);
        } else { 
            System.out.println("You Can't delete Some one else Comment");
        }
    }
    
    /**
     * Shows the post to the user and also provide the option to
     * Add post, like and comment 
     *
     * @param userId userId of the user who is in this page
     */
    public void displayPost(String userId) {      
        
        StringBuilder statement = new StringBuilder();
        int action;
        List listOfPosts;
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
                 .append("\nEnter ").append(Constants.DELETE_COMMENT)
                 .append(" --> To Delete Comment ")
                 .append("\nEnter ").append(Constants.EXIT_POSTPAGE)
                 .append(" --> To exit post feed : ");
    
        while (postFeedRunning) {       
            listOfPosts = postController.getUserPosts();
            if (listOfPosts.isEmpty()) {
                System.out.println("Post is not available");    
            } else {
                System.out.println(listOfPosts);
            } 
            
            System.out.println(statement);
            action = getOption();

            switch (action) {
            case Constants.ADDPOST:
                addPost(userId);
                break;

            case Constants.LIKE:
                addLike(userId);
                break;
 
            case Constants.LIKEDUSERS:
                showLikedUsers();
                break;
      
            case Constants.COMMENT:
                addComment(userId);
                break;

            case Constants.VIEW_COMMENTS:
                showComments(userId);
                break;

            case Constants.DELETE_COMMENT:
                deleteComment(userId);
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
     * Get the option from the user
     *
     * @return option option given by the user
     */
    private int getOption() {
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        try {
            option = scanner.nextInt();    
        } catch(InputMismatchException e) {
            logger.error("Enter Only Number not String\n");
            return option;
        }
        return option;
    } 
}