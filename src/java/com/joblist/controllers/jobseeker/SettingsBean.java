/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.jobseeker;

import com.joblist.model.JobSeeker;
import com.joblist.model.JobSeekerLogin;
import com.joblist.model.facades.JobSeekerFacadeLocal;
import com.joblist.model.facades.JobSeekerLoginFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author esa
 */
@Named(value = "jobSeekerSettingsBean")
@SessionScoped
public class SettingsBean implements Serializable {
    @Inject
    LoginInfoBean loginInfo;
    @Inject 
    TitleBarBean titleBar;
    @EJB
    JobSeekerLoginFacadeLocal jobSeekerLoginFacade;
    @EJB
    JobSeekerFacadeLocal jobSeekerFacade;

    /**
     * Creates a new instance of SettingsBean
     */
    public SettingsBean() {
    }
    
    public String deleteAccount() {
        JobSeekerLogin login = jobSeekerLoginFacade.find(loginInfo.getUserName());
        JobSeeker jobSeeker = loginInfo.getJobSeeker();
        jobSeeker.getJobs().clear();
        jobSeekerFacade.edit(jobSeeker);
        jobSeekerLoginFacade.remove(login);
        jobSeekerFacade.remove(jobSeeker);
        return titleBar.logout();
    }
}
