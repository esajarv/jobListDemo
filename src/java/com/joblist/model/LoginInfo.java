/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author koulutus
 */
@SessionScoped
public class LoginInfo implements Serializable{
    final private String userName;
    final private Map<String, Integer> jobs = new TreeMap<>();
    final private Map<Integer, String> jobIDs = new HashMap<>();
    private boolean wizardDone;
    
    public LoginInfo() {
        System.out.println("construct LoginInfo");
        Map<String,Object> sessionMap = 
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        userName = sessionMap.get("username").toString();
        System.out.println("LoginInfo.LoginInfo: username = " + userName);
    }
    
    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

    public String getUserName() {
        return userName;
    }
    public Iterable<Map.Entry<String,Integer>> getJobs() {
        return jobs.entrySet();
    }
    
    public boolean hasJobs() {
        return !jobIDs.isEmpty();
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

    /**
     * @return the wizardDone
     */
    public boolean isWizardDone() {
        return wizardDone;
    }

    /**
     * @param wizardDone the wizardDone to set
     */
    public void setWizardDone(boolean wizardDone) {
        this.wizardDone = wizardDone;
    }
}
