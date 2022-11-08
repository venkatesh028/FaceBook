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
import com.ideas2it.model.FriendRequest;
import com.ideas2it.controller.FriendRequestController;

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
    private PostController postController;
    private NotificationController notificationController;
    private CustomLogger logger;
    private FriendRequestController friendRequestController;
    
   
    public SearchPage() {
        this.scanner  = new Scanner(System.in);
        this.profileController = new ProfileController();
        this.profileView = new ProfileView();
        this.notificationController = new NotificationController();
        this.logger = new CustomLogger(SearchPage.class);
        this.friendRequestController = new FriendRequestController();
        this.postController = new PostController();
    }
    
    /**
     * Shows the search page where you can search users
     *
     * @param userId  userid of the person 
     */ 
    public void showSearchPage(String userId){
        boolean isGoBack = false;  
        int selectedOption;
        String userName;
        String searchMenu = generateSearchMenu();
        Profile profile;

        while (!isGoBack) {
            System.out.print(searchMenu);
            selectedOption = getOption();

            switch (selectedOption) {
            case Constants.SEARCH:
                System.out.print("Enter the username : ");
                userName = scanner.nextLine();
                profile = search(userName);
                
                if (null != profile) {
                    showRequestOption(profile, userId);
                } else {
                    System.out.println("There is no profile with this username");
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
    public void showUserProfile(Profile profile) {        
        int selectedOption;            
             
        System.out.println(profile);
        
        if (profile.getVisibility().equals("public")) {
            System.out.println(postController.getPostOfParticularUser(profile.getUserId())); 
        } else {
            System.out.println("Account is private ");
        }
    }
    
    /**
     * Shows the option to give the friend request to the user
     * 
     * @param profile - details of the profile who got searched 
     * @param userId - id of the user who is searching
     */
    public void showRequestOption(Profile profile, String userId) {
        String requestMenu = generateRequestMenu();
        int selectedOption;
        showUserProfile(profile);
        System.out.println(requestMenu); 
        selectedOption = getOption();       
       
        switch (selectedOption) {
        case Constants.ADD_FRIEND:
            FriendRequest friendRequest = new FriendRequest();
            friendRequest.setUserId(profile.getUserId());
            friendRequest.setRequestedUserId(userId);

            if (friendRequestController.create(friendRequest)) {
                System.out.println("Request is Given");
            } else {
                System.out.println("Request is not given");
            }
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
    public Profile search(String userName) {
        return profileController.getProfileByUserName(userName);
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