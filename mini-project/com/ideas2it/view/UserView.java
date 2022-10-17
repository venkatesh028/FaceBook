package com.ideas2it.view;

import java.util.Scanner;
import java.lang.Exception;  
import java.util.InputMismatchException;
import java.time.LocalDate;

import com.ideas2it.controller.ProfileController;
import com.ideas2it.controller.UserController;
import com.ideas2it.constant.Constants;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.model.User;
import com.ideas2it.model.Profile;

/**
 * Shows the home page to the user based on the user option
 * it takes to the further pages
 *
 * @version 1.0 22-SEP-2022
 * @author  Venkatesh TM 
 */
public class UserView {
    private UserController userController;
    private ProfileController profileController;
    private String userId;
    private FeedView feedView;
    private Scanner scanner; 
    private CustomLogger logger;

    public UserView() {
        this.userController = new UserController();
        this.profileController = new ProfileController();
        this.scanner = new Scanner(System.in);
        this.feedView = new FeedView();
        this.logger = new CustomLogger(UserView.class);
    }

    /** 
     * Gets the emailId and password from the user
     * and pass data to the validation based on result 
     * values are stored
     */ 
    public void login() {
        String email;
        String password;
       
        boolean isAccountExist = false;
    
        while (!isAccountExist) {
            System.out.print("Enter your emailId : ");
            email = scanner.next(); 
            System.out.print("Enter your password : ");
            password = scanner.next();
 
            if (userController.isEmailExist(email)) {               
                if (userController.isValidCredentials(email, password)) {
                    feedView.showNewsFeed(profileController.getProfileId(userController.getUserId(email)));
                    isAccountExist = true;
                } else {
                    logger.info("Invalid password try again");
                }                   
            } else {
                logger.info("There is no account with this mailID ");
                isAccountExist = true;
            }
        } 
    }    
    
    /**
     * Get the datails from the user
     * and pass the data to the validation and based on result
     * the account is created
     */
    public void createAccount() {
        User user = new User();
        Profile profile = new Profile();
        String profileId;
        int age;

        user.setEmail(getEmail());  
        user.setPassword(getPassword());
        user.setDateOfBirth(getDateOfBirth());          
        age = userController.calculateAge(user.getDateOfBirth());

        if (age>=18) {
            user.setAge(age );            
            System.out.println("Set user name to keep your account unique"); 
            profile.setUserName(getUserName());       
            user.setProfile(profile);

            if (userController.create(user, profile) == null) { 
                userId = userController.getUserId(user.getEmail()); 
                profileId = profileController.getProfileId(userId);                 
                System.out.println("Account Created Succesfully");
                
                feedView.showNewsFeed(profileId); 
            } else {
                logger.info("This email Id is alredy exist");
            }  
        } else {
            logger.info("You are not elibile to create a account");
        }                        
    } 
    
    /**  
     * Shows the home page to the user 
     */
    public void showHomePage() {
        int selectedOption;
        boolean isPageActive = false;
        String homeMenu = createHomeMenu();
       
        while (!isPageActive) {                    
            System.out.println(homeMenu);
            selectedOption = getOption();      

            switch (selectedOption) {
            case Constants.CREATE_ACCOUNT:
                createAccount();
                break;

            case Constants.LOGIN:
                login();
                break;

            case Constants.EXIT_HOMEPAGE:
                isPageActive = true;
                break;

            default:
                System.out.println("you entered wrong choice "); 
            }            
        }       
    }  

    /**
     * Get dateofBirth of the user
     * And Validate it
     *
     * @return dateOfBirth if it is valid 
     */
    private LocalDate getDateOfBirth() {
        boolean isValid = false;
        String dateOfBirth= "";

        while (!isValid) {
            System.out.print("Enter the DateofBirth in given format (yyyy-mm-dd) : ");
            dateOfBirth = scanner.nextLine();
            
            if (userController.isValidDateOfBirth(dateOfBirth)) {                
                isValid= true;
            } else {
                logger.warn("Invalid date format\n");
            }
        } 
        return LocalDate.parse(dateOfBirth);        
    }
    
    /**
     * Get email of the user 
     * And validate it
     *
     * @return email email of the user if it is valid
     */
    private String getEmail() {
        boolean isValid = false;
        String email = "";

        while (!isValid) {
            System.out.print("Enter your emailId : ");
            email = scanner.nextLine();

            if (userController.isValidEmail(email)) {
                if (!userController.isEmailExist(email)) {
                    isValid = true;
                } else {
                    logger.warn("Email Already exist\n");
                }                
            } else {
                logger.warn("Invalid email format\n");     
            }  
        }
        return email;
    }
    
    /**
     * Get the password of the user
     * And validate it
     * 
     * @return password  password of the user if it is valid
     */
    private String getPassword() { 
        boolean isValid = false;
        String password = "";
        StringBuilder formatMessage = new StringBuilder();
        formatMessage.append("Invalid Password.")
                     .append("\npassword must contain (a-ZA-Z0-9)")
                     .append(" and Any Special Character range must be 8-20)");
         
        while (!isValid) {
            System.out.print("Enter your password (a-ZA-Z0-9 and Special Character) : ");
            password = scanner.nextLine();
            
            if (userController.isValidPassword(password)) {
                isValid = true;
            } else {
                System.out.println(formatMessage);    
            }            
        }  
        return password;        
    }
    
    /**
     * Get username of the user
     * And validate it
     * 
     * @return userName username of the user if it is valid
     */
    private String getUserName() {
        boolean isValid = false;
        String userName = "";

        while (!isValid) {
            System.out.print("UserName : ");
            userName = scanner.nextLine();
            
            if (userController.isValidUserName(userName)) {
                if (!profileController.isUserNameExist(userName)) {
                    isValid = true;    
                } else {
                    logger.warn("UserName is already exist Enter a new one\n");
                }
            } else {
                logger.warn("Invalid username\n");                
            } 
        }  
        return userName;        
    }  
 
    /**
     * Get input form the user 
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
     * Creates the home menu to show the options 
     *
     * @return homeMenu - Menu shows the option available in home
     */
    private String createHomeMenu() {
        StringBuilder homeMenu = new StringBuilder();

        homeMenu.append("\nEnter ").append(Constants.CREATE_ACCOUNT)
                .append(" --> To Create a new account ")
                .append("\nEnter ").append(Constants.LOGIN)
                .append(" --> To login ")
                .append("\nEnter ").append(Constants.EXIT_HOMEPAGE)
                .append(" --> To quit ");
        return homeMenu.toString();
    }
}