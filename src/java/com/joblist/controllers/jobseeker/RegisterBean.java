/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.jobseeker;

import com.joblist.model.Login;
import com.joblist.model.LoginManager;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author koulutus
 */
@Named(value = "registerBean")
@RequestScoped
public class RegisterBean {
    @EJB LoginManager loginManager;
    private final Login login = new Login();

    /**
     * Creates a new instance of RegisterBean
     */
    public RegisterBean() {
    }
    
    public String getUserName() {
        return login.getUsername();
    }
    
    public void setUserName(String userName) {
        login.setUsername(userName);
    }

    public String getEmail() {
        return login.getEmail();
    }

    public void setEmail(String email) {
        login.setEmail(email);
    }    
    
    public String getPassword() {
        return login.getPassword();
    }
    
    public void setPassword(String password) {
        login.setPassword(password);
    }
    
    public String register() {
        System.out.println("RegisterBean:register");
        loginManager.register(login);
        return null;
    }
}
