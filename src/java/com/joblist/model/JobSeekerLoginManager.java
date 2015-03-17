/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.model;

import com.joblist.model.facades.JobSeekerLoginFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

/**
 *
 * @author koulutus
 */
@Stateless
public class JobSeekerLoginManager {
    @EJB
    JobSeekerLoginFacadeLocal jobSeekerLoginFacade;
    
    public JobSeekerLogin authenticate(JobSeekerLogin login) {
        JobSeekerLogin f = jobSeekerLoginFacade.find(login.getUsername());
        if (f == null) {
            return null;
        }
        if (login.getPassword().compareTo(f.getPassword()) == 0) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("login", f);
            return f;
        }
        return null;
    }
    
    public boolean isUserNameReserved(String userName) {
        return jobSeekerLoginFacade.find(userName) != null;
    }
    
    public void register(JobSeekerLogin login) {
        jobSeekerLoginFacade.create(login);
    }
}
