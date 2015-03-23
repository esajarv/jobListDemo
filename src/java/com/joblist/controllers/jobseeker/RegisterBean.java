/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.jobseeker;

import com.joblist.model.JobSeeker;
import com.joblist.model.JobSeekerLogin;
import com.joblist.model.JobSeekerLoginManager;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author koulutus
 */
@Named(value = "jobSeekerRegisterBean")
@RequestScoped
public class RegisterBean {
    @EJB JobSeekerLoginManager loginManager;
    private final JobSeekerLogin login = new JobSeekerLogin();
    private UIComponent userNameInput;
    private int tabActiveIndex;
    private String email;
    
    public UIComponent getUserNameInput() {
        return userNameInput;
    }
    
    public void setUserNameInput(UIComponent component) {
        userNameInput = component;
    }

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
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
            JobSeeker jobSeeker = new JobSeeker();
            jobSeeker.setEmail(email);
            login.setJobSeeker(jobSeeker);
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
