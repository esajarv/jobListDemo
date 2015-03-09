/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.model;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

/**
 *
 * @author koulutus
 */
@Stateless
public class LoginManager {
    private String userName;
    private String password;
    
    public boolean authenticate() {
        //todo: query database.
        if (getUserName().compareTo("user") == 0 && getPassword().compareTo("user") == 0) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", userName);
            return true;
        }
        return false;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
