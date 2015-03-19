/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.model;

import com.joblist.model.facades.JobSeekerLoginFacadeLocal;
import java.util.Map;
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
        JobSeekerLogin jsl = jobSeekerLoginFacade.find(login.getUsername());
        if (jsl == null) {
            return null;
        }
        if (login.getPassword().compareTo(jsl.getPassword()) == 0) {
            return jsl;
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
