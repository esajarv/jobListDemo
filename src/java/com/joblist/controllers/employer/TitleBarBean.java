/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.employer;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author esa
 */
@Named(value = "employerTitleBarBean")
@RequestScoped
public class TitleBarBean {

    /**
     * Creates a new instance of TitleBarBean
     */
    public TitleBarBean() {
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/employer/login";
    }
    
    public String getUsername() {
        return "user"; //todo
    }    
    
}
