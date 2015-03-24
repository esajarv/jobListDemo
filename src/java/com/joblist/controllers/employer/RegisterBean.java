/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.employer;

import com.joblist.model.EmployerLogin;
import com.joblist.model.EmployerLoginManager;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author esa
 */
@Named(value = "employerRegisterBean")
@RequestScoped
public class RegisterBean {
    @EJB EmployerLoginManager loginManager;
    private EmployerLogin login = new EmployerLogin();
    private UIComponent userNameInput;
    
    /**
     * Creates a new instance of RegisterBean
     */
    public RegisterBean() {
    }    
    
    public UIComponent getUserNameInput() {
        return userNameInput;
    }
    
    public void setUserNameInput(UIComponent component) {
        userNameInput = component;
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
    
    public void register() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (loginManager.isUserNameReserved(login.getUsername())) {
            FacesMessage msg = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "User name is reserved", "User name is reserved");
            fc.addMessage(userNameInput.getClientId(fc), msg);
        } else {
            loginManager.register(login);
            login = new EmployerLogin();
            fc.addMessage(null, new FacesMessage("register successful", "You can now log in"));
        }
    }
}
