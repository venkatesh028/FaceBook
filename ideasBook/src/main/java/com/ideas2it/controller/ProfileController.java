package com.ideas2it.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.ideas2it.model.Profile;
import com.ideas2it.service.ProfileService;
import com.ideas2it.service.FriendRequestService;
import com.ideas2it.service.serviceimpl.FriendRequestServiceImpl;
import com.ideas2it.service.serviceimpl.PostServiceImpl;
import com.ideas2it.service.serviceimpl.ProfileServiceImpl;
import com.ideas2it.service.PostService;
import com.ideas2it.constant.Messages;
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
    private PostService postService;
    private FriendRequestService friendRequestService;

    public ProfileController() {
        this.profileService = new ProfileServiceImpl();
        this.postService = new PostServiceImpl();
        this.friendRequestService = new FriendRequestServiceImpl();
        this.logger = new CustomLogger(ProfileController.class);
    }
    
    protected  void doGet(HttpServletRequest request,
                          HttpServletResponse response) throws IOException,
                                                         ServletException {
        try {
            String path = request.getServletPath();

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
            }
        } catch (ServletException | IOException exception) {
            logger.error(exception.getMessage());
            response.sendRedirect("errorPage.jsp");
        }
    }
    
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException,
                                                         ServletException {
        try {
            String path = request.getServletPath();
        
            switch (path) {
            case "/viewProfile":
                viewProfile(request, response);
            }
        } catch (ServletException | IOException exception) {
            logger.error(exception.getMessage());
            response.sendRedirect("error.jsp");
        }
    }
    
    /**
     * Updates the profile details only one the new userName is unique
     *  
     * @param request  - The request object is used to get the request parameters.
     * @param response - This is the response object that is used to send data back to the client.
     */
    private void updateProfile(HttpServletRequest request,
                               HttpServletResponse response) throws IOException,
                                                              ServletException {
        try {
            HttpSession session = request.getSession();
            RequestDispatcher requestDispatcher;
            String userName = request.getParameter("userName");
            Profile profile = profileService.getProfile((String) session.getAttribute("userId"));

            if (!profileService.isUserNameExist(userName) ||
                userName.equals(profile.getUserName())){
                profile.setUserName(request.getParameter("userName"));
                profile.setBio(request.getParameter("bio"));
                profileService.update(profile);
                requestDispatcher = request.getRequestDispatcher("viewProfile");
                requestDispatcher.forward(request, response);
            }else {
                request.setAttribute("Message", Messages.USERNAME_ALREADY_EXIST);
                request.setAttribute("profile", profile);
                requestDispatcher = request
                            .getRequestDispatcher("update-profile.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (CustomException customException) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
            request.setAttribute("error", Messages.SOMETHING_WENT_WRONG);
            requestDispatcher.forward(request, response);
        }
    }
    
    /**
     * Gets the profile of the user 
     * 
     * @param request  - The request object is used to get the request parameters.
     * @param response - This is the response object that is used to send data back to the client.
     */
    private void getProfile(HttpServletRequest request,
                            HttpServletResponse response) throws IOException,
                                                           ServletException {
        try {
            HttpSession session = request.getSession();
            RequestDispatcher requestDispatcher;
            Profile profile = profileService.getProfile((String) session.getAttribute("userId"));
            request.setAttribute("profile", profile);
            requestDispatcher = request.getRequestDispatcher("update-profile.jsp");
            requestDispatcher.forward(request, response);
        } catch (CustomException customException) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
            request.setAttribute("error", Messages.SOMETHING_WENT_WRONG);
            requestDispatcher.forward(request, response);            
        }
    }
    
    /**
     * Search the profile based on the username 
     * 
     * @param request  - The request object is used to get the request parameters.
     * @param response - This is the response object that is used to send data back to the client.
     */
    private void searchProfile(HttpServletRequest request,
                               HttpServletResponse response) throws IOException,
                                                              ServletException {
        try {
            RequestDispatcher requestDispatcher;
            HttpSession session = request.getSession();
            Profile profile = profileService.getUserProfileByUserName(request.getParameter("userName"));
            request.setAttribute("profile", profile);
            
            if (null != profile) {
                request.setAttribute("isFriend", friendRequestService.checkIsFriend(profile.getUserName(),
                                                               (String) session.getAttribute("userId") ));
            }
            requestDispatcher = request.getRequestDispatcher("search.jsp");
            requestDispatcher.forward(request, response);
        } catch (CustomException customException) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
            request.setAttribute("error", customException.getMessage());
            requestDispatcher.forward(request, response);
        }
    }
    
    /**
     * Shows the profile details of the user 
     *
     * @param request  - The request object is used to get the request parameters.
     * @param response - This is the response object that is used to send data back to the client.
     */
    private void viewProfile(HttpServletRequest request,
                             HttpServletResponse response) throws IOException,
                                                            ServletException {
        try {
            HttpSession session = request.getSession();
            RequestDispatcher requestDispatcher;
            request.setAttribute("profile", profileService
                                                .getProfile((String) session.getAttribute("userId")));
            request.setAttribute("listOfPosts", 
                       postService.getPostOfParticularUser((String) session
                                                    .getAttribute("userId")));
            requestDispatcher = request.getRequestDispatcher("profile.jsp");
            requestDispatcher.forward(request, response);
       } catch (CustomException customException) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
            request.setAttribute("error", customException.getMessage());
            requestDispatcher.forward(request, response);           
       }
    }
      
    /**
     * Updates the profile visibility status from private to public and viceversa
     *
     * @param request  - The request object is used to get the request parameters.
     * @param response - This is the response object that is used to send data back to the client.
     */
    private void updateVisibility(HttpServletRequest request,
                                  HttpServletResponse response) 
                                       throws IOException, ServletException {
        try {
            profileService.updateVisibility(request.getParameter("profileId"),
                                            request.getParameter("visibilityStatus"));
            RequestDispatcher requestDispatcher = request
                                                    .getRequestDispatcher("setting");
            requestDispatcher.forward(request, response);   
        } catch (CustomException customException) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("errorPage.jsp");
            request.setAttribute("error", customException.getMessage());
            requestDispatcher.forward(request, response);
        }    
    }
} 
