package com.ideas2it.view;

import java.util.Scanner;
import java.util.InputMismatchException;

import com.ideas2it.constant.Constants;
import com.ideas2it.logger.CustomLogger;
/**
 * Shows the feed page to user based on the user action
 * It takes to the further pages 
 *
 * @version 1.0 22-SEP-2022
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
     * @param profileId 
     */
    public void showNewsFeed(String profileId) {        
        int action;
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        String feedMenu = generateFeedMenu();

        while (isRunning) {
            System.out.println(feedMenu);
            action = getInput();
            
            switch (action) {
            case Constants.SHOW_POST:
                postView.displayPost(profileId);
                break;
            
            case Constants.SHOW_PROFILE:
                profileView.displayProfilePage(profileId);
                break;

            case Constants.SHOW_NOTIFICATION:
                notificationView.showRequests(profileId);
                break;

            case Constants.SHOW_SEARCH:
                searchPage.showSearchPage(profileId);
                break;

            case Constants.SHOW_SETTING:
                settingView.displaySettingPage(profileId);
                break;

            case Constants.LOGUT:
                isRunning = false;
                break;
            }
        }                   
    }
    
    /**
     * Get input from the user 
     * 
     * @return input 
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