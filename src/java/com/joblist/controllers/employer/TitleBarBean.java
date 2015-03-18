/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.employer;

import java.io.Serializable;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author esa
 */
@Named(value = "employerTitleBarBean")
@SessionScoped
public class TitleBarBean implements Serializable {
    private boolean showAddJob;
    private String previousPage;

    /**
     * Creates a new instance of TitleBarBean
     */
    public TitleBarBean() {
    }
    
    public void show(boolean showAddJob)
    {
        setShowAddJob(showAddJob);
        this.previousPage = null;
    }
    
    public void show(boolean showAddJob, String previousPage)
    {
        setShowAddJob(showAddJob);
        System.out.println(previousPage);
        this.previousPage = previousPage;
    }
    
    public String back(){
        System.out.println(previousPage);
        return previousPage;
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/employer/login?faces-redirect=true";
    }
    
    public String getUsername() {
        Map<String, Object> sessionMap = 
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap();        
        return sessionMap.get("username").toString();
    }

    /**
     * @return the showAddJob
     */
    public boolean isShowAddJob() {
        return showAddJob;
    }

    /**
     * @param showAddJob the showAddJob to set
     */
    public void setShowAddJob(boolean showAddJob) {
        this.showAddJob = showAddJob;
    }

    /**
     * @return the showBack
     */
    public boolean isShowBack() {
        return previousPage != null;
    }
}
