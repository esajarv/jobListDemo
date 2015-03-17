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
 * @author koulutus
 */
@Stateful
public class JobSeekerLoginInfo {
    private JobSeekerLogin login;
    
    public JobSeekerLoginInfo() {
        Map<String,Object> sessionMap = 
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        login = (JobSeekerLogin) sessionMap.get("jobseekerlogin");
        System.out.println("JobSeekerLoginInfo: username = " + login.getUsername());
    }
    
    public JobSeekerLogin getLogin() {
        return login;
    }

    public String getUserName() {
        return login.getUsername();
    }
    /**
     * @return the wizardDone
     */
    public boolean isWizardDone() {
        return login.isWizardDone();
    }
}
