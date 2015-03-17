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
    private final EmployerLogin login = new EmployerLogin();
    private UIComponent userNameInput;
    private int tabActiveIndex;
    
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
    
    public String register() {
        if (loginManager.isUserNameReserved(login.getUsername())) {
            FacesMessage msg = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "User name is reserved", "User name is reserved");
            FacesContext.getCurrentInstance().addMessage(userNameInput.getClientId(
                    FacesContext.getCurrentInstance()), msg);
        } else {
            loginManager.register(login);
            tabActiveIndex = 0;
        }
        return "login";
    }

    /**
     * @return the tabActiveIndex
     */
    public int getTabActiveIndex() {
        return tabActiveIndex;
    }

    /**
     * @param tabActiveIndex the tabActiveIndex to set
     */
    public void setTabActiveIndex(int tabActiveIndex) {
        this.tabActiveIndex = tabActiveIndex;
    }
}
