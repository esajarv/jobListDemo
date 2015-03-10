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
    public boolean authenticate(Login login) {
        //todo: query database.
        if (login.getUsername().compareTo("user") == 0 && login.getPassword().compareTo("user") == 0) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", login.getUsername());
            return true;
        }
        return false;
    }
    
    public void register(Login login) {
        //todo:
    }
}
