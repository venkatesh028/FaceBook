package com.ideas2it.view;

import java.util.List;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.ideas2it.controller.ProfileController;
import com.ideas2it.controller.UserController;
import com.ideas2it.controller.NotificationController;
import com.ideas2it.constant.Constants;
import com.ideas2it.controller.NotificationController;
import com.ideas2it.model.Notification;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.model.Friend;
import com.ideas2it.model.FriendRequest;
import com.ideas2it.model.Profile;
import com.ideas2it.controller.FriendController;
import com.ideas2it.controller.FriendRequestController;
import com.ideas2it.constant.Constants;

/**
 * Notification view shows the friend request received from the different users
 * User can Accept or rejecet the request based on the user wish
 *
 * @version 1.0 06-OCT-2022
 * @author Venkatesh TM
 */
public class NotificationView {
    private ProfileController profileController;
    private NotificationController notificationController;
    private UserController userController;
    private CustomLogger logger;
    private FriendController friendController;
    private FriendRequestController friendRequestController;
    private SearchPage searchPage;

    public NotificationView () {
        this.profileController = new ProfileController();
        this.notificationController = new NotificationController();
        this.userController = new UserController();
        this.friendController = new FriendController();
        this.friendRequestController = new FriendRequestController();
        this.logger = new CustomLogger(NotificationView.class);
        this.searchPage = new SearchPage();
    }
    
    public void viewRequest(String userId,Notification notification) {
        Profile profile ;
        int selectedOption;
        FriendRequest friendRequest = friendRequestController.get(notification.getRequestId());
        profile = profileController.getProfile(friendRequest.getRequestedUserId());
        searchPage.showUserProfile(profile);
        StringBuilder requestMessage = new StringBuilder();
        requestMessage.append("\nEnter ").append(Constants.ACCEPT)
                           .append(" --> To accept friend request ")
                           .append("\nEnter ").append(Constants.REJECT)
                           .append(" --> To reject the request ")
                           .append("\nEnter ").append(Constants.SKIP)
                           .append( " --> To skip now "); 
        System.out.println(requestMessage);
        selectedOption = getOption();
        switch (selectedOption) {
        case Constants.ACCEPT:         
            friendController.create(new Friend(userId, profile.getUserId()));
            friendController.create(new Friend(profile.getUserId(), userId));
            notificationController.clearNotification(notification.getId());
            break;

        case Constants.REJECT:
             //notificationController.clearNotification();
             break;

        case Constants.SKIP:
             break;
        } 
    }
    
    /**
     * Shows the requests received from the users
     * 
     * @param userId   id of the user
     */ 
    public void showNotification(String userId) {
        int selectedOption;
        String userName;
        String requestedProfileId = "";   
        StringBuilder notificationMessage = new StringBuilder();     
        List<Notification> notifications = notificationController.getNotifications(userId); 
        notificationMessage.append("\nEnter ").append(Constants.VIEW_REQUEST)
                           .append(" --> To View The Request ")
                           .append("\nEnter ").append(Constants.TO_GO_BACK)
                           .append(" --> To go back ");
                           

        if (notifications != null) {                                        
            for (Notification notification : notifications) {
                System.out.println(notification);
                System.out.print(notificationMessage);
                selectedOption = getOption();
               
                switch(selectedOption) {
                case Constants.VIEW_REQUEST:
                    viewRequest(userId, notification);
                    break;

                case Constants.TO_GO_BACK:
                    break;
                }
            }
        } else {
            logger.info("There is no notofication\n");
        }  
    }

    /**
     * Get option form the user 
     * 
     * @return option option given by the user
     */
    private int getOption() {
        Scanner scanner = new Scanner(System.in);
        int option = 0 ;

        try {
            option = scanner.nextInt();
        } catch(InputMismatchException e) {
            logger.error("Enter Only Number not String\n");
            return option;
        }
        return option; 
    }  
}