package com.ideas2it.controller;

import com.ideas2it.model.Profile;
import com.ideas2it.model.User;
import com.ideas2it.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

/**
 * It perform the create, update, delete, view and validation oeration of the user
 *
 * @version 2.0 14-NOV-2022
 * @author Venkatesh TM
 */
public class UserController extends HttpServlet {
    UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        String email;
        String password;
        LocalDate dateOfBirth;
        int age;
        String message;
        String userName;
        Profile profile = null;
        User user = null;

        switch (path) {

            case "/login":
                email = request.getParameter("email");
                password = request.getParameter("password");

                if (isValidCredentials(email, password)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("userId", getUserId(email));
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("newsFeed");
                    requestDispatcher.forward(request, response);
                } else {
                    message = "Sorry Email Id or Password is wrong";
                    request.setAttribute("Message", message);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
                    requestDispatcher.forward(request, response);
                }
                break;

            case "/register":
                email = request.getParameter("email");
                password = request.getParameter("password");
                dateOfBirth = LocalDate.parse(request.getParameter("DOB"));
                age = calculateAge(dateOfBirth);
                userName = request.getParameter("userName");

                if (!isEmailExist(email)) {
                    if (age > 18){
                        if (!isUserNameExist(userName)) {
                            user = new User();
                            profile = new Profile();
                            profile.setUserName(userName);
                            user.setEmail(email);
                            user.setPassword(password);
                            user.setDateOfBirth(dateOfBirth);
                            user.setAge(age);
                            create(user, profile);
                            message = "Account Created SuccessFully";
                            request.setAttribute("Message", message);
                            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
                            requestDispatcher.forward(request, response);
                        } else {
                            message = "Sorry the username is already Exist";
                            request.setAttribute("Message", message);
                            RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
                            requestDispatcher.forward(request, response);
                        }
                    } else {
                        message = "Sorry Your Age is Below 18";
                        request.setAttribute("Message", message);
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
                        requestDispatcher.forward(request, response);
                    }
                } else {
                    message = "Oohs This Email is Already Exist";
                    request.setAttribute("Message", message);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
                    requestDispatcher.forward(request, response);
                }
        }
    }

    /**
     * Create new account for the user
     *
     * @param  user     details of the user
     * @return boolean  true if account is created successfully else false
     */
    private boolean create(User user, Profile profile){
        return userService.create(user, profile);
    }

    private boolean isValidCredentials(String email, String password) {
        return userService.isValidCredentials(email, password);
    }

    /**
     * Gets the userId of the user
     *
     * @param email email of the user
     * @return userId - userId of the user
     */
    private String getUserId(String email) {
        return userService.getUserId(email);
    }

    /**
     * Check is that email is exist
     *
     * @param  email   email of the user
     * @return boolean true if the account is exist else false
     */
    public boolean isEmailExist(String email) {
        return userService.isEmailExist(email);
    }

    /**
     * Calculate the age based on the dateOfBirth given by the user
     *
     * @param  dateOfBirth dateOfBirth given by the user
     * @return age - age based on the dateOfBirth
     */
    public int calculateAge(LocalDate dateOfBirth) {
        return userService.calculateAge(dateOfBirth);
    }
    
    public boolean isUserNameExist(String userName) {
        return userService.isUserNameExist(userName);
    }
}