package com.ideas2it.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ideas2it.constant.Constants;
import com.ideas2it.logger.CustomLogger;
/**
 * Shows the feed page to user based on the user action
 * It takes to the further pages 
 *
 * @version 1.2 31-OCT-2022
 * @author Venkatesh TM
 */
public class FeedView {
    private PostView postView;
    private ProfileView profileView;
    private SettingView settingView;
    private Scanner scanner;
    private SearchPage searchPage;
    private NotificationView notificationView;
    private CustomLogger logger;
    
    /**
     * Creates a new object for the FeedView and initialize the feilds
     * of that class
     */    
    public FeedView() {
        this.postView = new PostView();
        this.settingView = new SettingView();
        this.scanner = new Scanner(System.in);
        this.profileView = new ProfileView();
        this.searchPage = new SearchPage();
        this.notificationView = new NotificationView();
        this.logger = new CustomLogger(FeedView.class);
    }
    
    /**
     * Shows the newsfeed page
     *
     * @param userId 
     */
    public void showNewsFeed(String userId) {        
        int action;
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        String feedMenu = generateFeedMenu();

        while (isRunning) {
            System.out.println(feedMenu);
            action = getOption();
            
            switch (action) {
            case Constants.SHOW_POST:
                postView.displayPost(userId);
                break;
            
            case Constants.SHOW_PROFILE:
                profileView.displayProfilePage(userId);
                break;

            case Constants.SHOW_NOTIFICATION:
                notificationView.showNotification(userId);
                break;

            case Constants.SHOW_SEARCH:
                searchPage.showSearchPage(userId);
                break;

            case Constants.SHOW_SETTING:
                settingView.displaySettingPage(userId);
                break;

            case Constants.LOGUT:
                isRunning = false;
                break;
            }
        }                   
    }
    
    /**
     * Gets option from the user 
     * 
     * @return option 
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
     * Generates the feed menu
     */
    private String generateFeedMenu() {
        StringBuilder feedMenu = new StringBuilder();

        feedMenu.append("\nEnter ").append(Constants.SHOW_POST)
                .append(" --> To View Post ").append("\nEnter ")
                .append(Constants.SHOW_PROFILE)
                .append(" --> To View your profile ")
                .append("\nEnter ").append(Constants.SHOW_NOTIFICATION)    
                .append(" --> To View Notification")
                .append("\nEnter ").append(Constants.SHOW_SEARCH)
                .append(" --> To go to Search page")
                .append("\nEnter ").append(Constants.SHOW_SETTING)
                .append(" --> To go to setting ")
                .append("\nEnter ").append(Constants.LOGUT)
                .append(" --> To Logout");
        return feedMenu.toString();
    }
}