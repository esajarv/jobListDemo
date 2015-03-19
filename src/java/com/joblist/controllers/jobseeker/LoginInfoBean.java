/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.jobseeker;

import com.joblist.model.JobSeekerLogin;
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
    private JobSeekerLogin login;
    
    /**
     * Creates a new instance of LoginInfoBean
     */
    public LoginInfoBean() {
    }
    
    @PostConstruct
    void init() {
        Map<String,Object> sessionMap = 
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        login = (JobSeekerLogin) sessionMap.get("jobseekerlogin");
        System.out.println("LoginInfoBean.init: user = " + login.getUsername() );
    }
    
    public JobSeekerLogin getLogin() {
        return login;
    }
    /**
     * @return the wizardDone
     */
    public boolean isWizardDone() {
        return login.isWizardDone();
    }
}
