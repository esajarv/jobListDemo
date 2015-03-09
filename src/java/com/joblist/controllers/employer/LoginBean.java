/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.employer;

import com.joblist.model.LoginManager;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author koulutus
 */
@Named(value = "loginEmployerBean")
@RequestScoped
public class LoginBean {
    @EJB LoginManager loginManager;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return loginManager.getUserName();
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        loginManager.setUserName(userName);
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return loginManager.getPassword();
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        loginManager.setPassword(password);
    }
    
    public String login() {
        if (loginManager.authenticate()) {
            return "forms/home?faces-redirect=true";
        }
        return null;
    }
}
