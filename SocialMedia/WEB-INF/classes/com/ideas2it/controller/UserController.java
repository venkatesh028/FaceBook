package com.ideas2it.controller;

import com.ideas2it.constant.Constants;
import com.ideas2it.model.Profile;
import com.ideas2it.model.User;
import com.ideas2it.service.UserService;
import com.ideas2it.constant.Messages;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

/**
 * It perform the create, update, delete, view and validation operation of the user
 *
 * @version 2.0 14-NOV-2022
 * @author Venkatesh TM
 */
public class UserController extends HttpServlet {
    UserService userService = new UserService();

    protected  void doGet(HttpServletRequest request,
                          HttpServletResponse response)
               throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
        case "/logout":
            logout(request, response);
            break;
        
        case "/setting":
            getUser(request, response);
            break;
        }
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) 
              throws ServletException, IOException {
        String path = request.getServletPath();

        switch (path) {
        case "/login":
            login(request, response);
            break;

        case "/register":
            registerUser(request, response);
            break;
        }
    }

    /**
     * Allows the user to login when the email and password is Valid
     *
     * @param request
     * @param response
     */
    protected void login(HttpServletRequest request,
                         HttpServletResponse response)
              throws ServletException, IOException {
        String message;

        if (isValidCredentials(request.getParameter("email"),
                request.getParameter("password"))) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", getUserId(request.getParameter("email")));
            response.sendRedirect("newsFeed");
        } else {
            message = "Sorry Email Id or Password is wrong";
            request.setAttribute("Message", message);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        }
    }
    
    /**
     * Logouts the user and remove the session value
     * 
     * @param request
     * @param response
     */
    private void logout(HttpServletRequest request,
                          HttpServletResponse response)
              throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("userId");
        response.sendRedirect("login.jsp");
    }
    
    /**
     * Registers the user information 
     * 
     * @param request
     * @parma response
     */
    private void registerUser(HttpServletRequest request,
                                HttpServletResponse response)
              throws ServletException, IOException {
        String message;

        if (!isEmailExist(request.getParameter("email"))) {
            if (calculateAge(LocalDate.parse(request.getParameter("DOB"))) > Constants.AGE) {
                if (!isUserNameExist(request.getParameter("userName"))) {
                    create(request, response);
                } else {
                    message = Messages.USERNAME_ALREADY_EXIST;
                    goBackToRegisterPage(request, response, message);
                }
            } else {
                message = Messages.INVALID_AGE;
                goBackToRegisterPage(request, response, message);
            }
        } else {
            message = Messages.EMAIL_ALREADY_EXIST;
            goBackToRegisterPage(request, response, message);
        }
    }

    /**
     * Create new account for the user
     *
     * @param  request
     * @param  response
     */
    private void create(HttpServletRequest request,  
                          HttpServletResponse response)
              throws ServletException, IOException {
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("DOB"));
        
        User user = new User(request.getParameter("email"), request.getParameter("password"), dateOfBirth, calculateAge(dateOfBirth));
        Profile profile = new Profile(request.getParameter("userName"));
        userService.create(user, profile);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);
    }
    
    /**
     * Takes you to the register page with the message 
     * 
     * @param request
     * @param response
     */
    private void goBackToRegisterPage(HttpServletRequest request,
                                        HttpServletResponse response,
                                        String message)
              throws ServletException, IOException {
        request.setAttribute("Message", message);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
        requestDispatcher.forward(request, response);
    }
    
    /**
     * Checks the given credentials are valid 
     * 
     * @param email - email id of the user 
     * @param password - password of the user 
     */
    private boolean isValidCredentials(String email, String password) {
        return userService.isValidCredentials(email, password);
    }

    /**
     * Gets the userId of the user
     *
     * @param email - email of the user
     * @return userId - userId of the user
     */
    private String getUserId(String email) {
        return userService.getUserId(email);
    }

    /**
     * Check is that email is exist
     *
     * @param  email  email of the user
     * @return boolean true if the account is exist else false
     */
    private boolean isEmailExist(String email) {
        return userService.isEmailExist(email);
    }

    /**
     * Calculate the age based on the dateOfBirth given by the user
     *
     * @param  dateOfBirth dateOfBirth given by the user
     * @return age - age based on the dateOfBirth
     */
    private int calculateAge(LocalDate dateOfBirth) {
        return userService.calculateAge(dateOfBirth);
    }
    
    /**
     * Checks the given username is exist 
     * 
     * @param userName - userName given by the user 
     * @return boolean  - true or false based on the result
     */ 
    private boolean isUserNameExist(String userName) {
        return userService.isUserNameExist(userName);
    }
   
    private void getUser(HttpServletRequest request, 
                         HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = userService.getUser((String) session.getAttribute("userId"));
        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("setting.jsp");
        requestDispatcher.forward(request, response);
    }    
}