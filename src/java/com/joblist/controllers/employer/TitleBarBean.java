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
    private boolean showBack;

    /**
     * Creates a new instance of TitleBarBean
     */
    public TitleBarBean() {
    }
    
    public void show(boolean showAddJob)
    {
        setShowAddJob(showAddJob);
        this.previousPage = null;
        showBack = false;
    }
    
    public void show(boolean showAddJob, String previousPage) {
        setShowAddJob(showAddJob);
        this.previousPage = previousPage;
        showBack = false;
    }
    
    public void withBrowserHistoryBackShow(boolean showAddJob)
    {
        setShowAddJob(showAddJob);
        this.previousPage = null;
        showBack = true;
    }
    
    public String back() {
        return previousPage;
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/employer/login?faces-redirect=true";
    }
    
    public String getUsername() {
        Map<String, Object> sessionMap = 
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap();        
        return sessionMap.get("e_username").toString();
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
    
    public boolean isShowBack() {
        return previousPage != null;
    }

    /**
     * @return the showBack
     */
    public boolean isShowBrowserHistoryBack() {
        return showBack;
    }
}
