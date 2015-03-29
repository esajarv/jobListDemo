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
    private JobSeekerLogin login = new JobSeekerLogin();
    private UIComponent userNameInput;
    private String password;
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
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void register() {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (loginManager.isUserNameReserved(login.getUsername())) {
            FacesMessage msg = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "User name is reserved", "User name is reserved");
            fc.addMessage(userNameInput.getClientId(fc), msg);
        } else {
            JobSeeker jobSeeker = new JobSeeker();
            jobSeeker.setEmail(email);
            login.setJobSeeker(jobSeeker);
            try {
                loginManager.register(login, password);
            } catch (Exception ex) {
                fc.addMessage(null, new FacesMessage("Register failed. Try again later.", 
                        "Register failed. Try again later."));
                return;
            }
            fc.addMessage(null, new FacesMessage("register successful", "You can now log in"));
            login = new JobSeekerLogin();
            email = null;
        }
    }
}
