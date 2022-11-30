package com.ideas2it.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ideas2it.constant.Messages;
import com.ideas2it.service.ProfileService;
import com.ideas2it.model.Profile;
import com.ideas2it.exception.CustomException;
import com.ideas2it.logger.CustomLogger;


/**
 * Implemtens create, get, update and delete operation for the profile
 *
 * @version 1.0 23-SEP-2022
 * @author  Venkatesh TM
 */
public class ProfileController extends HttpServlet {
    ProfileService profileService;
    private CustomLogger logger;

    public ProfileController() {
        this.profileService = new ProfileService();
        this.logger = new CustomLogger(ProfileController.class);
    }
    
    protected  void doGet(HttpServletRequest request,
                          HttpServletResponse response)
               throws ServletException, IOException{
        String path = request.getServletPath();

        String message;
        RequestDispatcher requestDispatcher =null;

        switch (path) {
        case "/viewProfile":
            viewProfile(request, response);
            break;

        case "/search":
            searchProfile(request, response);
            break;

        case "/updateProfile":
            getProfile(request, response);
            break;

        case "/update":
            updateProfile(request, response);
            break;
        }
    }
    
    /**
     * Updates the profile details 
     *  
     * @param request 
     * @param response 
     */
    private void updateProfile(HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher requestDispatcher;
        String userName = request.getParameter("userName");
        Profile profile = getProfile((String)session.getAttribute("userId"));
        String message;

        if (!userName.equals(profile.getUserName())) {
            if (!isUserNameExist(userName)){
                update(request, response);
            }else {
                message = Messages.USERNAME_ALREADY_EXIST;
                request.setAttribute("Message", message);
                request.setAttribute("profile", getProfile((String) session.getAttribute("userId")));
                requestDispatcher = request.getRequestDispatcher("update-profile.jsp");
                requestDispatcher.forward(request, response);
            }
        } else {
            update(request, response);
        }
    }
    
    /**
     * Gets the profile of the user 
     * 
     * @param request 
     * @param response 
     */
    private void getProfile(HttpServletRequest request,
                            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher requestDispatcher;
        request.setAttribute("profile",getProfile((String)session.getAttribute("userId")));
        requestDispatcher = request.getRequestDispatcher("update-profile.jsp");
        requestDispatcher.forward(request, response);
    }
    
    /**
     * Search the profile based on the username 
     * 
     * @param request 
     * @param response 
     */
    private void searchProfile(HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher;
        request.setAttribute("profile", getProfileByUserName(request.getParameter("userName")));
        requestDispatcher = request.getRequestDispatcher("search.jsp");
        requestDispatcher.forward(request, response);
    }
    
    /**
     * Shows the profile details of the user 
     *
     * @param request 
     * @parma response 
     */
    private void viewProfile(HttpServletRequest request,
                             HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher requestDispatcher;
        request.setAttribute("profile",getProfile((String)session.getAttribute("userId")));
        requestDispatcher = request.getRequestDispatcher("profile.jsp");
        requestDispatcher.forward(request, response);
    }

    /**
     * Creates the profile
     * 
     * @param  profile - profile contain the details of the profile 
     * @return boolean - true or false based on the response
     */
    public boolean create(Profile profile) {
        return profileService.create(profile);
    }
    
    /**
     * Shows the profile of the user
     * 
     * @param  userId -  userId of the user
     * @return Profile - profile of the user
     */
    public Profile getProfile(String userId) {
        return profileService.getProfile(userId);
    } 
    
    /**
     * Updates the profile
     * 
     * @param request
     * @param response
     */
    private void update(HttpServletRequest request,
                        HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher requestDispatcher;
        Profile profile = getProfile((String) session.getAttribute("userId"));
        profile.setUserName(request.getParameter("userName"));
        profile.setBio(request.getParameter("bio"));
        profileService.update(profile);
        requestDispatcher = request.getRequestDispatcher("profile.jsp");
        request.setAttribute("profile", getProfile((String) session.getAttribute("userId")));
        requestDispatcher.forward(request, response);
    }

    /**
     * Deletes the profile based on the userId
     *
     * @param  userId - id of the user which need to be deleted 
     * @return boolean - true or false based on the response 
     */
    public boolean delete(String userId) {
        return profileService.delete(userId);
    }    
    
    /**
     * Gets the profileId based on the username 
     * 
     * @param  userName  - userName of the profile 
     * @return profile - profile of the profile
     */
    public Profile getProfileByUserName(String userName) { 
        Profile profile = null;
         
        try {
            profile = profileService.getUserProfileByUserName(userName); 
        } catch (CustomException customException) { 
            logger.error(customException.getMessage());
        }
        return profile;
    }
    
    /**
     * Check is the username is exist or not
     * 
     * @param  userName username given by the user
     * @return boolean  true if the name is not exist else false
     */
    public boolean isUserNameExist(String userName) {
        return profileService.isUserNameExist(userName);
    }
      
    /**
     * Sets the profile as public 
     * 
     * @param  userId - id of the user
     * @return boolean - true or false based on the response
     */
    public boolean setPublic(String userId) {
        return profileService.setPublic(userId);
    }

    /**
     * Sets the profile as private
     * 
     * @param  userId - id of the user
     * @return boolean - true or false based on the response
     */
    public boolean setPrivate(String userId) {
        return profileService.setPrivate(userId);
    }
} 
