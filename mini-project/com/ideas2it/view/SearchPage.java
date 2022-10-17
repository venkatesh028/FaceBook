package com.ideas2it.view;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.LocalDate;

import com.ideas2it.constant.Constants;
import com.ideas2it.controller.ProfileController;
import com.ideas2it.controller.NotificationController;
import com.ideas2it.controller.PostController;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.model.Notification;
import com.ideas2it.model.Profile;

/**
 * Search the user and give friend request 
 *
 * @version 1.0 01-OCT-2022
 * @author Venkatesh TM
 */
public class SearchPage {
    private Scanner scanner;
    private ProfileController profileController;
    private ProfileView profileView;
    private NotificationController notificationController;
    private CustomLogger logger;

    public SearchPage() {
        this.scanner  = new Scanner(System.in);
        this.profileController = new ProfileController();
        this.profileView = new ProfileView();
        this.notificationController = new NotificationController();
        this.logger = new CustomLogger(SearchPage.class);
    }
    
    /**
     * Shows the search page where you can search users
     *
     * @param userId  userid of the person 
     */ 
    public void showSearchPage(String userId){
        boolean isGoBack = false;  
        int selectedOption;
        String searchMenu = generateSearchMenu();

        while (!isGoBack) {
            System.out.print(searchMenu);
            selectedOption = getOption();

            switch (selectedOption) {
            case Constants.SEARCH:
                Profile profile = search(userId);

                if (profile != null) {
                    if (profile.getUserName().equals(profileController.getUserName(userId))) {
                        profileView.displayProfilePage(userId);
                    } else {
                        showUserProfile(profile, userId);
                    }
                } else {
                        logger.info("There is no account with this username ");
                }
                break;

            case Constants.TO_GO_BACK:
                isGoBack = true;
                break;
             
            default: 
                logger.warn("Entered wrong option\n");                    
                break;               
            }
        }     
    }
    
    /**
     * Shows the profile of the user who got searched
     *
     * @param profile profile of the user who got searched
     */
    public void showUserProfile(Profile profile, String profileId) {
        int selectedOption;
        String requestedUser = profileController.getUserName(profileId);      
        PostController postController = new PostController();
        String requestMenu = generateRequestMenu();

        if (!profile.getIsPrivate()) {                 
            System.out.print(profile);
            System.out.print(postController.getPostByUserName(profile.getUserName()));
        } else {
            System.out.print(profile);
            System.out.print("Account is private ");
        }

       System.out.println(requestMenu); 
       selectedOption = getOption();      
       
       switch (selectedOption) {
       case Constants.ADD_FRIEND:
           Notification notification = new Notification(requestedUser, LocalDate.now());
           notificationController.addNotification(profile.getUserName(), notification);      
           break;

       case Constants.REMOVE_FRIEND:     
           break;

       case Constants.BACK:
           break;
       }
    }
    
    /**
     * Search the user based on the username entered by the user
     * 
     * @return profile profile based on the username
     */
    public Profile search(String profileId) {
        String userName;
        System.out.print("Enter the username : ");
        userName = scanner.nextLine();
        return profileController.getUserProfile(userName);
    }

    /**
     * Gets input form the user 
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
     * Generates the Search Menu 
     *
     * @return searchMenu - search menu which have description 
     */ 
    private String generateSearchMenu() {
        StringBuilder searchMenu= new StringBuilder();

        searchMenu.append("\nEnter ").append(Constants.SEARCH)
                  .append(" --> To search the users ")
                  .append("\nEnter ").append(Constants.TO_GO_BACK)    
                  .append(" --> To go back ");

       return searchMenu.toString();
    }
    
    /**
     * Generates the profile menu
     *
     * @return profileMenu - profileMenu which have description
     */
    private String generateRequestMenu() {
        StringBuilder requestMenu = new StringBuilder();   

        requestMenu.append("\nEnter ").append(Constants.ADD_FRIEND)
                   .append(" --> To give friend request ")
                   .append("\nEnter ").append(Constants.REMOVE_FRIEND)              
                   .append(" --> To remove the friend request ")
                   .append("\nEnter ").append(Constants.BACK)
                   .append(" --> To go back ");

        return requestMenu.toString();
    }

}