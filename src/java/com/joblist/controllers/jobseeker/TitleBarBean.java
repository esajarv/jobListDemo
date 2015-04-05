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

    /**
     * Creates a new instance of TitleBarBean
     */
    public TitleBarBean() {
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/jobseeker/login?faces-redirect=true";
    }
    
    public String getUsername() {
        Map<String, Object> sessionMap =
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        return sessionMap.get("j_username").toString();
    }
}
