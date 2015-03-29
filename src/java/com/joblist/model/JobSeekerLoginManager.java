/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.model;

import com.joblist.model.facades.JobSeekerLoginFacadeLocal;
import java.security.SecureRandom;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author koulutus
 */
@Stateless
public class JobSeekerLoginManager {
    @EJB
    JobSeekerLoginFacadeLocal jobSeekerLoginFacade;
    
    public JobSeekerLogin authenticate(JobSeekerLogin login, String password) throws Exception {
        JobSeekerLogin jsl = jobSeekerLoginFacade.find(login.getUsername());
        if (jsl == null) {
            return null;
        }
        if (PasswordEncryptor.match(password,
                jsl.getPassword(),
                jsl.getIV(),
                jsl.getSalt())) {
            return jsl;
        }
        return null;
    }
    
    public boolean isUserNameReserved(String userName) {
        return jobSeekerLoginFacade.find(userName) != null;
    }
    
    public void changePassword(JobSeekerLogin login, String password) throws Exception {
        byte result[][] = PasswordEncryptor.encrypt(password, new SecureRandom());
        login.setPassword(result[0]);
        login.setIV(result[1]);
        login.setSalt(result[2]);
        
        jobSeekerLoginFacade.edit(login);
    }
    
    public void register(JobSeekerLogin login, String password) throws Exception {
        byte result[][] = PasswordEncryptor.encrypt(password, new SecureRandom());
        login.setPassword(result[0]);
        login.setIV(result[1]);
        login.setSalt(result[2]);
        
        jobSeekerLoginFacade.create(login);
    }
}
