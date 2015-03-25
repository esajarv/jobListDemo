/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.jobseeker;

import com.joblist.model.JobSeeker;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

/**
 *
 * @author esa
 */
@Named(value = "jobSeekerLoginInfoBean")
@SessionScoped
public class LoginInfoBean implements Serializable {
    private JobSeeker login;
    private String userName;
    
    /**
     * Creates a new instance of LoginInfoBean
     */
    public LoginInfoBean() {
    }
    
    @PostConstruct
    void init() {
        Map<String,Object> sessionMap = 
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        login = (JobSeeker) sessionMap.get("jobseeker");
        userName = sessionMap.get("j_username").toString();
        System.out.println("LoginInfoBean.init: user = " + userName);
    }
    
    public JobSeeker getJobSeeker() {
        return login;
    }
    /**
     * @return the wizardDone
     */
    public boolean isWizardSubmitted() {
        return login.isWizardSubmitted();
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
