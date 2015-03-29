/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.jobseeker;

import com.joblist.model.Job;
import com.joblist.model.JobSeeker;
import com.joblist.model.JobSeekerLogin;
import com.joblist.model.JobSeekerLoginManager;
import com.joblist.model.facades.JobFacadeLocal;
import com.joblist.model.facades.JobSeekerFacadeLocal;
import com.joblist.model.facades.JobSeekerLoginFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author esa
 */
@Named(value = "jobSeekerSettingsBean")
@SessionScoped
public class SettingsBean implements Serializable {
    private String oldPassword;
    private String newPassword;
    private UIComponent passwordField;
    
    @Inject
    LoginInfoBean loginInfo;
    @Inject 
    TitleBarBean titleBar;
    @EJB
    JobSeekerLoginFacadeLocal jobSeekerLoginFacade;
    @EJB
    JobSeekerFacadeLocal jobSeekerFacade;
    @EJB
    JobFacadeLocal jobFacadeLocal;
    @EJB
    JobSeekerLoginManager loginManager;
    @EJB
    JobSeekerLoginFacadeLocal loginFacade;

    /**
     * Creates a new instance of SettingsBean
     */
    public SettingsBean() {
    }
    
    public String deleteAccount() {
        JobSeekerLogin login = jobSeekerLoginFacade.find(loginInfo.getUserName());
        JobSeeker jobSeeker = loginInfo.getJobSeeker();
        List<Job> jobs = jobSeeker.getJobs();
        for(Job j : jobs) {
            j.getJobSeekers().remove(jobSeeker);
            jobFacadeLocal.edit(j);
        }
        jobs.clear();
        jobSeekerFacade.edit(jobSeeker);
        jobSeekerLoginFacade.remove(login);
        jobSeekerFacade.remove(jobSeeker);
        return titleBar.logout();
    }
    
    public void changePassword()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        JobSeekerLogin login = jobSeekerLoginFacade.find(loginInfo.getUserName());
        try {
            if (loginManager.authenticate(login, oldPassword) != null) {
                loginManager.changePassword(login, newPassword);
                fc.addMessage(null, new FacesMessage("Password changed."));
            } else {
                FacesMessage msg = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Invalid password", "Invalid password");
                fc.addMessage(passwordField.getClientId(fc), msg);
            }
        } catch(Exception e) {
            fc.addMessage(null, new FacesMessage("Changing the password failed."));
        }
        oldPassword = null;
        newPassword = null;
    }

    /**
     * @return the oldPassword
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * @param oldPassword the old password to set
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return the passwordField
     */
    public UIComponent getPasswordField() {
        return passwordField;
    }

    /**
     * @param passwordField the passwordField to set
     */
    public void setPasswordField(UIComponent passwordField) {
        this.passwordField = passwordField;
    }
}
