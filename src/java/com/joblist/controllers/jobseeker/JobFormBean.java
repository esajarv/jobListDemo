/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.jobseeker;

import com.joblist.model.JobSeekerLoginInfo;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
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
    @Inject JobSeekerLoginInfo loginInfo;
    final private Map<String, Integer> jobs = new TreeMap<>();
    final private Map<Integer, String> jobIDs = new HashMap<>();    
    
    /**
     * Creates a new instance of JobFormBean
     */
    public JobFormBean() {
    }
    
    public boolean getLoggedWithJob() {
        return !jobIDs.isEmpty();
    }
    
    public Iterable<Map.Entry<String,Integer>> getJobs() {
        return jobs.entrySet();
    }
    
    public String logout() {
        loginInfo.logout();
        System.out.println("logout");
        return "/jobseeker/login";
    }
    
    public void applyJob(Integer jobID) {
        removeJob(jobID);
    }
    
    public String getUser() {
        return loginInfo.getUserName();
    }
    
    public void removeJob(Integer jobID) {
        String name = jobIDs.remove(jobID);
        jobs.remove(name);
    }
    
    public void addJobID(Integer jobID) {
        String name = "Web developer: " + jobID;
        jobIDs.put(jobID, name);
        jobs.put(name, jobID);
    }
    
    public void addJobID(String jobID) {
        Integer iJob = parseJob(jobID);
        if (iJob != null) {
            addJobID(iJob);
        }
    }
    
    private Integer parseJob(Object jobID) {
        Integer j = null;
        try {
            j = Integer.parseInt(jobID.toString());
        } catch(NumberFormatException e) {}
        return j;
    }
}
