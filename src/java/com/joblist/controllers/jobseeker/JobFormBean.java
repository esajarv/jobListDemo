/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.jobseeker;

import com.joblist.model.LoginInfo;
import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author esa
 */
@Named(value = "jobFormBean")
@SessionScoped
public class JobFormBean implements Serializable{
    @Inject LoginInfo loginInfo;
    
    /**
     * Creates a new instance of JobFormBean
     */
    public JobFormBean() {
    }
    
    public boolean getLoggedWithJob() {
        return loginInfo.hasJobs();
    }
    
    public Iterable<Map.Entry<String,Integer>> getJobs() {
        return loginInfo.getJobs();
    }
    
    public String logout() {
        loginInfo.logout();
        System.out.println("logout");
        return "/jobseeker/login";
    }
    
    public void applyJob(Integer jobID) {
        //todo:
        loginInfo.removeJob(jobID);
    }
    
    public void removeJob(Integer jobID) {
        loginInfo.removeJob(jobID);
    }
    
    public String getUser() {
        return loginInfo.getUserName();
    }
}
