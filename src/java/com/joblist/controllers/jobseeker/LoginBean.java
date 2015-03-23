/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.jobseeker;

import com.joblist.model.JobSeekerLogin;
import com.joblist.model.JobSeekerLoginManager;
import java.io.Serializable;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author koulutus
 */
@Named(value = "loginJobseekerBean")
@RequestScoped
public class LoginBean implements Serializable{
    @EJB JobSeekerLoginManager loginManager;
    private JobSeekerLogin login = new JobSeekerLogin();
    private String jobID;

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
    
    public String login()
    {
        System.out.println("login: jobID = " + jobID);
        JobSeekerLogin tmp = loginManager.authenticate(login);
        if (tmp != null) {
            login = tmp;
            Map<String, Object> sessionMap = 
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            sessionMap.put("jobseeker", login.getJobSeeker());
            sessionMap.put("username", login.getUsername());
            
            if (jobID != null && !jobID.isEmpty()) {
                return "forms/apply?faces-redirect=true&jobid=" + jobID;
            }
            if (login.isWizardSubmitted()) {
                return "forms/home?faces-redirect=true";
            }
            return "forms/wizard?faces-redirect=true";
        }
        return null;
    }
    
    /**
     * @return the jobId
     */
    public String getJobID() {
        return jobID;
    }

    /**
     * @param jobId the jobId to set
     */
    public void setJobID(String jobId) {
        System.out.println("login.setJobId: jodID = " + jobId);
        this.jobID = jobId;
    }
}
