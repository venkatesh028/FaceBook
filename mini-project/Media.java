/*
 * This is a Social media application where user can create account 
 * And add post and view other users post 
 * Can give like and comment to the post
 */

import com.ideas2it.view.UserView;

/**
 * call the home page in userView 
 *
 * @version 1.0 22-SEP-2022
 * @author Venkatesh TM 
 */
public class Media {
    public static void main(String[] args) {
        UserView userView = new UserView(); 
        userView.showHomePage();  
    }      
}