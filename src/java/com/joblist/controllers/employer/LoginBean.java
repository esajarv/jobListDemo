/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.employer;

import com.joblist.model.EmployerLogin;
import com.joblist.model.EmployerLoginManager;
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
    private final EmployerLogin login = new EmployerLogin();
    @EJB EmployerLoginManager loginManager;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return login.getUsername();
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        login.setUsername(userName);
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return login.getPassword();
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        login.setPassword(password);
    }
    
    public String login() {
        if (loginManager.authenticate(login) != null) {
            return "forms/home?faces-redirect=true";
        }
        return null;
    }
}
