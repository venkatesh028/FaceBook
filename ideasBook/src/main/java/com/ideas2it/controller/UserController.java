package com.ideas2it.controller;

import java.time.format.DateTimeParseException;
import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.ideas2it.model.Profile;
import com.ideas2it.model.User;
import com.ideas2it.service.ProfileService;
import com.ideas2it.service.UserService;
import com.ideas2it.service.serviceimpl.ProfileServiceImpl;
import com.ideas2it.service.serviceimpl.UserServiceImpl;
import com.ideas2it.constant.Constants;
import com.ideas2it.constant.Messages;
import com.ideas2it.exception.CustomException;
import com.ideas2it.logger.CustomLogger;

/**
 * Performs the create, update, delete, view 
 * and validation operation of the user
 *
 * @version 2.0 14-NOV-2022
 * @author Venkatesh TM
 */
public class UserController extends HttpServlet {

    private UserService userService;
    private ProfileService profileService;
    private CustomLogger logger;
    
    public UserController() {
        userService = new UserServiceImpl();
        profileService = new ProfileServiceImpl();
        logger = new CustomLogger(UserController.class);
    }

    /** 
     * Gets the request and response form the browser and performs the 
     * task based on the request
     * 
     * @param request  - The request object is used to get the request parameters.
     * @param response - This is the response object that is used to send data back to the client.
     */
    protected  void doGet(HttpServletRequest request,
                          HttpServletResponse response) throws IOException,
                                                         ServletException {
        logger.info("====Doget from user Controller===");
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

    /** 
     * Gets the request and response form the browser and performs the 
     * task based on the request
     * 
     * @param request  - The request object is used to get the request parameters.
     * @param response - This is the response object that is used to send data back to the client.
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException,
                                                         ServletException {
        String path = request.getServletPath();
        
        switch (path) {
        case "/login":
            login(request, response);
            break;

        case "/register":
            createUser(request, response);
            break;
        
        case "/update-info":
            update(request, response);
            break;
        
        case "/update-password":
            updatePassword(request, response); 
            break;
        
        case "/delete-account":
            deleteUser(request, response);
            break;

        case "/setting":
            getUser(request, response);
            break;
        }    
    }

    /**
     * Allows the user to login when the email and password is Valid
     *
     * @param request  - The request object is used to get the request parameters.
     * @param response - This is the response object that is used to send data back to the client.
     */
    protected void login(HttpServletRequest request,
                         HttpServletResponse response) throws IOException,
                                                        ServletException {
        
        try { 
            if (isValidCredentials(request.getParameter("email"),
                request.getParameter("password"))) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", 
                                      getUserId(request.getParameter("email")));
                response.sendRedirect("newsFeed");
            } else {
                request.setAttribute("Message", Messages.WRONG_CREDENTIALS);
                RequestDispatcher requestDispatcher = request
                                        .getRequestDispatcher("login.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (CustomException customException) {
              logger.info(customException.getMessage());
              RequestDispatcher requestDispatcher = request
                                     .getRequestDispatcher("errorPage.jsp");
              request.setAttribute("Error",customException.getMessage());
              requestDispatcher.forward(request, response);
        }
    }
    
    /**
     * Logouts the user and remove the id stored in the session
     * 
     * @param request  - The request object is used to get the request parameters.
     * @param response - This is the response object that is used to send data back to the client.
     */
    private void logout(HttpServletRequest request,
                        HttpServletResponse response) throws IOException,
                                                       ServletException {
        HttpSession session = request.getSession();
        session.removeAttribute("userId");
        response.sendRedirect("login.jsp");
    }

    /**
     * Creates the user with the given details after performing the validation
     *
     * @param request  - The request object is used to get the request parameters.
     * @param response - This is the response object that is used to send data back to the client.
     */
    private void createUser(HttpServletRequest request,
                        HttpServletResponse response) throws IOException,
                                                       ServletException {
        logger.info("====Create page from userController===");
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            LocalDate dateOfBirth = LocalDate.parse(request.getParameter("DOB"));
            int age = calculateAge(dateOfBirth);
            String userName = request.getParameter("userName");

            if (age > Constants.AGE){
                if (!isEmailExist(email)) {
                    if (!isUserNameExist(userName)) {
                        User user = new User(email, password, dateOfBirth, age);
                        Profile profile = new Profile(userName);
                        userService.create(user, profile);
                    } else {
                        goBackToRegisterPage(request, response, Messages.USERNAME_ALREADY_EXIST);
                    }
                } else {
                    goBackToRegisterPage(request, response, Messages.EMAIL_ALREADY_EXIST);
                }
            } else {
                goBackToRegisterPage(request, response, Messages.INVALID_AGE);
            }
            RequestDispatcher requestDispatcher = request
                                       .getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        } catch (DateTimeParseException parseException) {
            logger.error(parseException.getMessage());
            RequestDispatcher requestDispatcher = request
                                       .getRequestDispatcher("register.jsp");
            request.setAttribute("info", Messages.WRONG_INPUT);
            requestDispatcher.forward(request, response);
        } catch (CustomException customException) {
            logger.info(customException.getMessage());
            RequestDispatcher requestDispatcher = request
                                       .getRequestDispatcher("register.jsp");
            request.setAttribute("info",Messages.ACCOUNT_NOT_CREATED);
            requestDispatcher.forward(request, response);
        }
    }
    
    /**
     * Takes you to the register page with the warning message 
     * 
     * @param request  - The request object is used to get the request parameters.
     * @param response - This is the response object that is used to send data back to the client.
     */
    private void goBackToRegisterPage(HttpServletRequest request,
                                      HttpServletResponse response,
                                      String message) throws IOException,
                                                       ServletException {
        request.setAttribute("Message", message);
        RequestDispatcher requestDispatcher = request
                                    .getRequestDispatcher("register.jsp");
        requestDispatcher.forward(request, response);
    }
    
    /**
     * Gets the user details and takes to the setting page 
     *
     * @param request  - The request object is used to get the request parameters.
     * @param response - This is the response object that is used to send data back to the client.
     */
    private void getUser(HttpServletRequest request, 
                         HttpServletResponse response) throws IOException,
                                                        ServletException {
        try {
            HttpSession session = request.getSession();
            User user = userService.getById((String) session
                                                 .getAttribute("userId"));
            request.setAttribute("user", user);
            RequestDispatcher requestDispatcher = request
                                    .getRequestDispatcher("setting.jsp");
            requestDispatcher.forward(request, response);
        } catch (CustomException customException) {
            logger.info(customException.getMessage());
            RequestDispatcher requestDispatcher = request
                                  .getRequestDispatcher("errorPage.jsp");
            request.setAttribute("error", Messages.SOMETHING_WENT_WRONG);
            requestDispatcher.forward(request, response);
        }
    }
    
    /**
     * Updates the details of the user only when it satisfy the condition
     *
     * @param request  - The request object is used to get the request parameters.
     * @param response - This is the response object that is used to send data back to the client.
     */
    private void update(HttpServletRequest request,
                        HttpServletResponse response) throws IOException,
                                                       ServletException {
        try {
            HttpSession session = request.getSession();
            User user = userService.getById((String) session
                                                 .getAttribute("userId"));           
            int age = calculateAge(LocalDate.parse(
                                            request.getParameter("DOB")));
         
            if (!isEmailExist(request.getParameter("email")) ||
                 request.getParameter("email").equals(user.getEmail())) {
                if (age > Constants.AGE) {
                    user.setEmail(request.getParameter("email"));
                    user.setDateOfBirth(LocalDate.parse(request.getParameter("DOB")));
                    user.setAge(age);
                    user.setPhoneNumber(Long.parseLong(request.getParameter("phoneNumber")));
                    userService.update(user);   
                    RequestDispatcher requestDispatcher = request
                                                 .getRequestDispatcher("setting");
                    request.setAttribute("message", Messages.USER_UPDATED);
                    requestDispatcher.forward(request, response);  
                } else {
                    RequestDispatcher requestDispatcher = request
                                                 .getRequestDispatcher("setting");
                    request.setAttribute("message", Messages.INVALID_DOB);
                    requestDispatcher.forward(request, response);                    
                }   
            } else {
                RequestDispatcher requestDispatcher = request
                                                 .getRequestDispatcher("setting");
                request.setAttribute("message", Messages.EMAIL_ALREADY_EXIST);
                requestDispatcher.forward(request, response);   
            }      
        } catch (CustomException customException) {
            RequestDispatcher requestDispatcher = request
                                     .getRequestDispatcher("errorPage.jsp");
            request.setAttribute("error",customException.getMessage());
            requestDispatcher.forward(request, response);
        } catch (Exception exception) {  
            logger.info(exception.getMessage());
        }
    }
    
    /**
     * Updates the password of the user after the password verfication 
     * 
     * @param request  - The request object is used to get the request parameters.
     * @param response - This is the response object that is used to send data back to the client.
     */
    private void updatePassword(HttpServletRequest request,
                                HttpServletResponse response) throws IOException,
                                                               ServletException {
        try {
            HttpSession session = request.getSession();
            User user = userService.getById((String) session
                                                 .getAttribute("userId")); 
            if (userService.isPasswordMatches(user.getEmail(),
                                   request.getParameter("oldPassword"))) {
                user.setPassword(request.getParameter("newPassword"));
                userService.updatePassword(user);  
                RequestDispatcher requestDispatcher = request
                                                 .getRequestDispatcher("setting");
                request.setAttribute("message", Messages.PASSWORD_UPDATED);
                requestDispatcher.forward(request, response);
            } else {
                RequestDispatcher requestDispatcher = request
                                                 .getRequestDispatcher("setting");
                request.setAttribute("message", Messages.PASSWORD_IS_NOT_UPDATED);
                requestDispatcher.forward(request, response);
            }  
        } catch (CustomException customException) {
            RequestDispatcher requestDispatcher = request
                                     .getRequestDispatcher("errorPage.jsp");
            request.setAttribute("error",customException.getMessage());
            requestDispatcher.forward(request, response);       
        }
    }
    
    /**
     * Deletes the User account based only if the password is correct 
     * 
     * @param request  - The request object is used to get the request parameters.
     * @param response - This is the response object that is used to send data back to the client.
     */
    private void deleteUser(HttpServletRequest request,
                            HttpServletResponse response) throws IOException,
                                                           ServletException { 
        try {
            HttpSession session = request.getSession();
            User user = userService.getById((String) session
                                                 .getAttribute("userId"));
            
            if (userService.isValidCredentials(user.getEmail(),
                                          request.getParameter("password"))) {
                userService.delete(user);      
                RequestDispatcher requestDispatcher = request
                                                 .getRequestDispatcher("login.jsp");
                request.setAttribute("message", Messages.ACCOUNT_DELETED);
                requestDispatcher.forward(request, response);     
            } else {
                RequestDispatcher requestDispatcher = request
                                                 .getRequestDispatcher("setting");
                request.setAttribute("message", Messages.PASSWORD_IS_WRONG);
                requestDispatcher.forward(request, response);
            }
        } catch (CustomException customException) {
            RequestDispatcher requestDispatcher = request
                                               .getRequestDispatcher("setting");
            request.setAttribute("error", customException.getMessage());
            requestDispatcher.forward(request, response);
        }
    }

    /**
     * Checks the given credentials are valid 
     * 
     * @param email - email id of the user 
     * @param password - password of the user 
     */
    private boolean isValidCredentials(String email,
                                       String password) throws CustomException {
        return userService.isValidCredentials(email, password);
    }

    /**
     * Gets the userId of the user
     *
     * @param email - email of the user
     * @return userId - userId of the user
     */
    private String getUserId(String email) throws CustomException {
        return userService.getUserId(email);
    }

    /**
     * Checks that email is exist or not 
     *
     * @param  email  email of the user
     * @return boolean true if the account is exist else false
     */
    private boolean isEmailExist(String email) throws CustomException {
        return userService.isEmailExist(email);
    }

    /**
     * Calculates the age based on the dateOfBirth given by the user
     *
     * @param  dateOfBirth dateOfBirth given by the user
     * @return age - age based on the dateOfBirth
     */
    private int calculateAge(LocalDate dateOfBirth) throws CustomException {
        return userService.calculateAge(dateOfBirth);
    }
    
    /**
     * Checks the given username is exist 
     * 
     * @param userName - userName given by the user 
     * @return boolean  - true or false based on the result
     */ 
    private boolean isUserNameExist(String userName) throws CustomException {
        return profileService.isUserNameExist(userName);
    }
}