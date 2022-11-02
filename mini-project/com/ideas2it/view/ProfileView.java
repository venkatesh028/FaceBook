package com.ideas2it.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ideas2it.constant.Constants;
import com.ideas2it.controller.PostController;
import com.ideas2it.controller.ProfileController;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.model.Profile;

/**
 * Shows the profile page to the user based on the user action
 * it takes toe further pages 
 *
 * @version 1.0 22-SEP-2022
 * @author Venkatesh TM
 */
public class ProfileView {
    private ProfileController profileController;
    private PostController postController;
    private Scanner scanner;  
    private CustomLogger logger;                                  
     
    public ProfileView() {
        this.profileController = new ProfileController();
        this.postController = new PostController();
        this.scanner = new Scanner(System.in);
        this.logger = new CustomLogger(ProfileView.class);
    }
    
    /**
     * Shows the update page 
     *
     * @param userId
     */
    private void updateProfile(String userId) {
        Profile profile = profileController.getProfile(userId);
        int selectedUpdate;
        boolean updatePage = true;
        String updateMenu = generateProfileUpdateMenu();

        while (updatePage) {
            System.out.print(updateMenu);
            selectedUpdate = getOption();         
       
            switch (selectedUpdate) {            
            case Constants.UPDATE_USERNAME:
                String newUserName;
                boolean userNameValid = false;

                while (!userNameValid) {                                     
                    System.out.print("Enter the UserName : ");
                    newUserName = scanner.nextLine();
                    
                    if (!profileController.isUserNameExist(newUserName)) {
                        profile.setUserName(newUserName);
                        profileController.update(profile);
                        userNameValid = true;
                    } else {  
                        logger.info("UserName is already exist Enter a new one");
                    }
                }                
                break;
            
            case Constants.UPDATE_BIO:
                System.out.print("Enter your Bio :");
                String bio = scanner.nextLine();
                profile.setBio(bio);
                profileController.update(profile);            
                break;

            case Constants.EXIT_UPDATEPAGE:
                updatePage = false;
                break;

            default:
                updatePage = false;
                break;
            }
        }         
    }

    /**
     * Shows the profile of the user
     * 
     * @param userId userId of the user
     */
    private void showProfile(String userId) {
       System.out.println(profileController.getProfile(userId));
    }

    /**
     * Shows the post by the username
     * 
     * @param userId userId of the user
     */    
    private void showMyPost(String userId) {         
        System.out.println(postController.getPostOfParticularUser(userId)); 
    }
    
    /**
     * Delete the post based on the id
     *
     */
    private void deletePost() {
        String postId;
        System.out.print("Enter the PostId : ");
        postId = scanner.nextLine();

        if (postController.delete(postId)) {
            System.out.println("Post Deleted ..");
        } else {
            logger.info("Something went wrong..");
        }
    }

    /**
     * Shows the profilepage of the user
     * 
     * @param userId userId of the user
     */
    public void displayProfilePage(String userId) {
        int selectedOption; 
        boolean profilePage = true;
        String profileMenu = generateProfileMenu();               

        while (profilePage) {   
            showProfile(userId); 
            showMyPost(userId);
            System.out.println(profileMenu);
            selectedOption = getOption();

            switch (selectedOption) { 
            case Constants.UPDATE_PROFILE:
                updateProfile(userId);
                break;

            case Constants.UPDATE_POST:
                updatePost();
                break;
                
            case Constants.DELETE_POST:
                deletePost();
                break;
            
            case Constants.EXIT_PROFILEPAGE:
                profilePage = false;
                break;

            default:
                logger.info("You entered wrong option");
            }
        }  
    }
    
    private void updatePost() {
        String postId;
        String content;
        System.out.println("Enter the Post ID : ");
        postId = scanner.nextLine();
        System.out.println("Enter the updates in content");
        content = scanner.nextLine();        
        if (postController.update(postId, content)) {
            System.out.println("Post Updated");
        } else {
            System.out.println("Not updated");
        }    
    }

    /**
     * Get the input from the user
     *
     * @return input input given by the user
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
    
    /**
     * Generates the profile menu
     * 
     * @return profileMenu - profile menu it shows the description about the options
     */
    private String generateProfileMenu() {
        StringBuilder profileMenu = new StringBuilder();

        profileMenu.append("\nEnter ").append(Constants.UPDATE_PROFILE)
                   .append(" --> To update Profile").append("\nEnter ")
                   .append(Constants.DELETE_POST).append("--> To delete the post ") 
                   .append("\nEnter ").append(Constants.UPDATE_POST)
                   .append(" --> To Update the post")
                   .append("\nEnter ").append(Constants.EXIT_PROFILEPAGE)
                   .append(" --> To Exit");

        return profileMenu.toString();
    }
    
    /**
     * Generates the profile update menu
     *
     * @return updateMenu - update menu it shows the description about the update options
     */
    private String generateProfileUpdateMenu() {
        StringBuilder updateMenu = new StringBuilder();

        updateMenu.append("\nEnter ").append(Constants.UPDATE_USERNAME)
                  .append(" --> To update username ").append("\nEnter ")
                  .append(Constants.UPDATE_BIO)
                  .append(" --> To update Bio ").append("\nEnter ")              
                  .append(Constants.EXIT_UPDATEPAGE)
                  .append(" --> To exit ");

        return updateMenu.toString();    
    }
} 