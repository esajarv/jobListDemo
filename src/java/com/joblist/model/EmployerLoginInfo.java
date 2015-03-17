/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.model;

import java.util.Map;
import javax.ejb.Stateful;
import javax.faces.context.FacesContext;

/**
 *
 * @author esa
 */
@Stateful
public class EmployerLoginInfo {
    private EmployerLogin login;

    public EmployerLoginInfo() {
        Map<String,Object> sessionMap = 
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        login = (EmployerLogin) sessionMap.get("login");
        System.out.println("EmployerLoginInfo: username = " + login.getUsername());
    }
    
    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    public EmployerLogin getLogin() {
        return login;
    }

    public String getUserName() {
        return login.getUsername();
    }
}
