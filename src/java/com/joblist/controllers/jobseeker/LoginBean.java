/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.jobseeker;

import com.joblist.model.Login;
import com.joblist.model.LoginManager;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author koulutus
 */
@Named(value = "loginJobseekerBean")
@RequestScoped
public class LoginBean implements Serializable{
    @EJB LoginManager loginManager;
    private Login login = new Login();
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
        Login tmp = loginManager.authenticate(login);
        if (tmp != null) {
            login = tmp;
            if (jobID != null && !jobID.isEmpty()) {
                return "forms/apply?faces-redirect=true&jobid=" + jobID;
            }
            if (login.isWizardDone()) {
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
