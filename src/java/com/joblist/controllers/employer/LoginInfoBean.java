/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.employer;

import com.joblist.model.EmployerLogin;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

/**
 *
 * @author esa
 */
@Named(value = "employerLoginInfoBean")
@SessionScoped
public class LoginInfoBean implements Serializable {
    private EmployerLogin login;

    /**
     * Creates a new instance of LoginInfoBean
     */
    public LoginInfoBean() {
    }
    
    @PostConstruct
    void init() {
        Map<String,Object> sessionMap = 
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        login = (EmployerLogin) sessionMap.get("employerlogin");
        System.out.println("LoginInfoBean.init: username = " + login.getUsername());        
    }
    
    public EmployerLogin getLogin() {
        return login;
    }

    public String getUserName() {
        return login.getUsername();
    }    
    
}
