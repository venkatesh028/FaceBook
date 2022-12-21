package com.ideas2it.action;

import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport{
    private String email;
    private String password;

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
        return "success";   
    }
}