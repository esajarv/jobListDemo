/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.jobseeker;

import java.io.Serializable;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author esa
 */
@Named(value = "jobSeekerTitleBarBean")
@SessionScoped
public class TitleBarBean implements Serializable {
    private boolean showUploadCV;
    private boolean showBack;

    /**
     * Creates a new instance of TitleBarBean
     */
    public TitleBarBean() {
    }
    
    public void show(boolean showUploadCV) 
    {
        setShowUploadCV(showUploadCV);
    }
    
    public void withBrowserHistoryBackShow(boolean showUploadCV)
    {
        setShowUploadCV(showUploadCV);
        showBack = true;
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/jobseeker/login?faces-redirect=true";
    }

    /**
     * @return the showUploadCV
     */
    public boolean isShowUploadCV() {
        return showUploadCV;
    }

    /**
     * @param showUploadCV the showUploadCV to set
     */
    public void setShowUploadCV(boolean showUploadCV) {
        this.showUploadCV = showUploadCV;
    }
    
    public String getUsername() {
        Map<String, Object> sessionMap =
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        return sessionMap.get("j_username").toString();
    }
    
    /**
     * @return the showBack
     */
    public boolean isShowBrowserHistoryBack() {
        return showBack;
    }
}
