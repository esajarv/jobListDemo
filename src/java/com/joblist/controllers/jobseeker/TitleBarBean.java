/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.jobseeker;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author esa
 */
@Named(value = "titleBarBean")
@RequestScoped
public class TitleBarBean {
    
    private boolean showUploadCV;

    /**
     * Creates a new instance of TitleBarBean
     */
    public TitleBarBean() {
    }
    
    public void show(boolean showUploadCV) 
    {
        setShowUploadCV(showUploadCV);
    }
    
    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
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
        return "user"; //todo
    }
    
}
