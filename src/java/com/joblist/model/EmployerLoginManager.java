/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.model;

import com.joblist.model.facades.EmployerLoginFacadeLocal;
import java.security.SecureRandom;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author esa
 */
@Stateless
public class EmployerLoginManager {
    @EJB
    EmployerLoginFacadeLocal employerLoginFacade;    

    public EmployerLogin authenticate(EmployerLogin login, String password) throws Exception {
        EmployerLogin el = employerLoginFacade.findByUsername(login.getUsername());
        if (el == null) {
            return null;
        }
        if (PasswordEncryptor.match(password,
                el.getPassword(),
                el.getIV(),
                el.getSalt())) {
            return el;
        }
        return null;
    }
    
    public boolean isUserNameReserved(String userName) {
        return employerLoginFacade.findByUsername(userName) != null;
    }
    
    public void changePassword(EmployerLogin login, String password) throws Exception {
        byte result[][] = PasswordEncryptor.encrypt(password, new SecureRandom());
        login.setPassword(result[0]);
        login.setIV(result[1]);
        login.setSalt(result[2]);
        
        employerLoginFacade.edit(login);
    }
    
    public void register(EmployerLogin login, String password) throws Exception {
        byte result[][] = PasswordEncryptor.encrypt(password, new SecureRandom());
        login.setPassword(result[0]);
        login.setIV(result[1]);
        login.setSalt(result[2]);
        
        employerLoginFacade.create(login);
    }
}
