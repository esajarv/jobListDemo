/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.employer;

import com.joblist.model.EmployerLogin;
import com.joblist.model.Job;
import com.joblist.model.facades.EmployerLoginFacadeLocal;
import com.joblist.model.facades.JobFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author esa
 */
@Named(value = "employerSettingsBean")
@SessionScoped
public class SettingsBean implements Serializable {
    @Inject
    LoginInfoBean loginInfo;
    @Inject 
    TitleBarBean titleBar;
    @EJB
    EmployerLoginFacadeLocal loginFacade;
    @EJB
    JobFacadeLocal jobFacade;

    /**
     * Creates a new instance of SettingsBean
     */
    public SettingsBean() {
    }
    
    public String deleteAccount() {
        EmployerLogin login = loginInfo.getLogin();
        jobFacade.cancelAll(login.getId());
        loginFacade.remove(login);
        return titleBar.logout();
    }
}
