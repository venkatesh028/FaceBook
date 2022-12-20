package com.ideas2it.action;

import com.opensymphony.xwork2.ActionSupport;

import com.ideas2it.constant.Messages;
import com.ideas2it.exception.CustomException;
import com.ideas2it.logger.CustomLogger;
import com.ideas2it.service.UserService;
import com.ideas2it.service.serviceimpl.UserServiceImpl;

public class Login extends ActionSupport{
    private String email;
    private String password;
    private CustomLogger logger = new CustomLogger(Login.class);
    private UserService userService = new UserServiceImpl();

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String execute() {
        UserService userService = new UserServiceImpl();                       
        String result;
        
        try {
            if (userService.isValidCredentials(email, password)) {
                result = "success";  
            } else {
                result = "invalid";               
            }
        } catch (CustomException customException) {
            logger.info(customException.getMessage());
            result = "error";
        }   
        return result;   
    }
}