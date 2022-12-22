package com.ideas2it.action;


import java.util.Map;  

import com.opensymphony.xwork2.ActionSupport;
  
import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;  

import com.ideas2it.service.UserService;
import com.ideas2it.service.serviceimpl.UserServiceImpl;
import com.ideas2it.exception.CustomException;

public class Login extends ActionSupport implements SessionAware {
    private String email;
    private String password;
    private SessionMap<String, Object> sessionMap;
    private UserService userService = new UserServiceImpl();
    
    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap) map;
    }

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
        String result = "invalid";
        
        try {
            if (userService.isValidCredentials(email, password)) {
                sessionMap.put("userId", userService.getUserId(email));
                result = "success"; 
            }  
        } catch (CustomException customException) {
            result = "error";
        }   
        return result;  
    }
}