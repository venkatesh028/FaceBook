package com.ideas2it.view;

import java.util.InputMismatchException;
import java.time.LocalDate;
import java.util.Scanner;

import com.ideas2it.constant.Constants;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.controller.ProfileController;
import com.ideas2it.controller.UserController;
import com.ideas2it.model.User;

/**
 * Shows the setting page to the user
 *
 * @version 1.0 22-SEP-2022
 * @authro  Venkatesh TM
 */
public class SettingView {
    private UserController userController;
    private Scanner scanner;
    private ProfileController profileController;  
    private CustomLogger logger; 
    

    public SettingView() {
        this.userController = new UserController();
        this.scanner = new Scanner(System.in);
        this.profileController = new ProfileController();
        this.logger = new CustomLogger(SettingView.class);
    }
     
    /**
     * Deletes the account of the user 
     *
     * @param userId - userId of the user
     */
    private void deleteAccount(String userId) {
        userController.delete(userId);                    
    }
    
    /**
     * Shows the personal information of the user
     * 
     * @param userId - userId of the user
     */
    private void showPersonalInfo(String userId) {
        System.out.println(userController.getUser(userId));            
    }
    
    /**
     * Shows the option to update the personal information of the user
     *
     * @param userId - userId of the user
     */
    private void updatePersonalInfo(String userId) {        
        int selectedOption;
        boolean isContinue = true;
        User user = userController.getUser(userId);
        String updateMenu = generateUpdateMenu();
        
        while(isContinue) {
            System.out.println(updateMenu);
            selectedOption = getOption();

            switch (selectedOption) {
            case Constants.UPDATE_EMAIL:
                boolean isValid = false;
                String email;

                while (!isValid) {
                    System.out.print("Enter your emailId : ");
                    email = scanner.nextLine();

                    if (userController.isValidEmail(email)) {
                        if (!userController.isEmailExist(email)) {
                            user.setEmail(email);
                            isValid = true;
                        } else {
                            logger.warn("Email Already exist");
                        }                
                    } else {
                        logger.warn("Invalid email format");     
                    }  
                }
                break;

            case Constants.UPDATE_GENDER:
                System.out.print("Enter Gender : ");
                String gender = scanner.nextLine();
                user.setGender(gender);
                break;

            case Constants.UPDATE_DOB:
                String dateOfBirth;
                boolean isValidDateOfBirth = false;

                while (!isValidDateOfBirth) {
                    System.out.print("Enter the DateofBirth : ");
                    dateOfBirth = scanner.nextLine();
                    int age;

                    if (userController.isValidDateOfBirth(dateOfBirth)) {
                        age = userController.calculateAge(LocalDate.parse(dateOfBirth));

                        if (age>=18){
                            user.setDateOfBirth(LocalDate.parse(dateOfBirth));
                            user.setAge(age);
                            isValidDateOfBirth = true;
                        } else {
                            logger.warn("Not eligible to have a account\n");
                        }
                    } else {
                        logger.warn("Invalid date. \nEnter in given format (yyyy-mm-dd)\n");
                    }
                }   
                break;

            case Constants.UPDATE_PHONENUMBER:
                boolean isValidNumber = false;

                while (!isValidNumber) {
                    System.out.print("Enter Phone number : ");
                    String phoneNumber = scanner.nextLine();

                    if (userController.isValidPhoneNumber(phoneNumber)) {  
                        user.setPhoneNumber(Long.parseLong(phoneNumber)); 
                        isValidNumber = true;
                    } else {
                        logger.info("Enter in format (+91-6...)");
                    }
                }                
                break;

            case Constants.EXIT_UPDATE:
                userController.update(user);
                isContinue = false;
                break;
     
            default:
                userController.update(user);            
            }
        } 
    }
    
    /**
     * Updates the password of the user by getting new password 
     * from the user
     * 
     * @param userId - userId id of the user
     */
    private void updatePassword(String userId) {
        User user = userController.getUser(userId);
        String oldPassword;
        String newPassword;
        boolean isPasswordValid = false;
        System.out.print("Enter your old Password : ");
        oldPassword = scanner.nextLine();
        
        if (userController.isPasswordMatches(user.getEmail(),oldPassword)) {
            System.out.print("Enter New Password : ");
            newPassword = getPassword();
            user.setPassword(newPassword);            
        } else {
            System.out.println("Invalid password");
        }
        userController.update(user);    
    }
    
    /**
     * Changes the visbility of the profile from public to private and private to public
     *
     * @param userId  id of the user 
     */
    private void changeVisibility(String userId) {
        boolean isGoBack = false;
        int option;
        StringBuilder visibilityInstruction = new StringBuilder();
        visibilityInstruction.append("\nEnter ").append(Constants.SET_PUBLIC)
                             .append(" --> To set in Public ")
                             .append("\nEnter ").append(Constants.SET_PRIVATE)
                             .append(" --> To set in private ")
                             .append("\nEnter ").append(Constants.GO_BACK)
                             .append(" --> To Go back ");
        while (!isGoBack) {
            System.out.println(visibilityInstruction);
            option = getOption();

            switch (option) {
            case Constants.SET_PUBLIC:
                profileController.setPublic(userId);
                break;

            case Constants.SET_PRIVATE:
                profileController.setPrivate(userId);
                break;

            case Constants.GO_BACK:
                isGoBack = true;
                break;

            default:
                System.out.println("You Entered Wrong option"); 
            }
        }
    }
    
    /**
     * Shows the setting page of the user 
     *
     * @param userId userid of the user
     */
    public void displaySettingPage(String userId) {
        int selectedOption;
        boolean settingPage = true;
        String settingMenu = generateSettingMenu();


        while (settingPage) {
            System.out.print(settingMenu);
            selectedOption = getOption();
            
            switch (selectedOption) {
            case Constants.SHOW_PERSONAL_INFO:
                showPersonalInfo(userId);
                break;

            case Constants.UPDATE_PERSONAL_INFO:
                updatePersonalInfo(userId);
                break;

            case Constants.DELETE_ACCOUNT:
                deleteAccount(userId);
                break;  

            case Constants.UPDATE_PASSWORD:
                updatePassword(userId);
                break;

            case Constants.VISIBILITY:
                
                changeVisibility(userId);
                break;

            case Constants.EXIT_SETTING:
                settingPage = false; 
                break;
            }
        } 
    }  
     
    /**
     * Gets the input from the user 
     *
     * @return input input given the user 
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
     * Generates the update menu 
     * 
     * @return updateMenu - update menu have all the update options description 
     */
    private String generateUpdateMenu() {
        StringBuilder updateMenu = new StringBuilder();

        updateMenu.append("\nEnter ")
                  .append(Constants.UPDATE_EMAIL)
                  .append(" --> To update email").append("\nEnter ")   
                  .append(Constants.UPDATE_GENDER)
                  .append(" --> To update gender ").append("\nEnter ")
                  .append(Constants.UPDATE_DOB)
                  .append(" --> To update DOB ").append("\nEnter ")
                  .append(Constants.UPDATE_PHONENUMBER)
                  .append(" --> To update Phonenumber ").append("\nEnter")
                  .append(Constants.EXIT_UPDATE)
                  .append(" --> To exit ");

        return updateMenu.toString();
    }
    
    /**
     * Generates the setting menu to show
     *
     * @return settingMenu - Setting menu have all the setting options description
     */
    private String generateSettingMenu() {
        StringBuilder settingMenu = new StringBuilder(); 
       
        settingMenu.append("\nEnter ")
                   .append(Constants.SHOW_PERSONAL_INFO)
                   .append(" -->To View Personal Info ")
                   .append("\nEnter ")
                   .append(Constants.UPDATE_PERSONAL_INFO)
                   .append(" -->To update Personal Info ")
                   .append("\nEnter ")                                       
                   .append(Constants.DELETE_ACCOUNT)
                   .append(" -->To Delete Account ")
                   .append("\nEnter ")
                   .append(Constants.UPDATE_PASSWORD)
                   .append(" -->To Update password ")
                   .append("\nEnter ")
                   .append(Constants.VISIBILITY)
                   .append(" --> To change the profile visibility ")
                   .append("\nEnter ")
                   .append(Constants.EXIT_SETTING)
                   .append(" -->To View Exit");
        return settingMenu.toString();
    } 
 
    /**
     * Gets the password of the user
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
            password = scanner.nextLine();
            
            if (userController.isValidPassword(password)) {
                isValid = true;
            } else {
                System.out.println(formatMessage);    
            }            
        }  
        return password;        
    }
}