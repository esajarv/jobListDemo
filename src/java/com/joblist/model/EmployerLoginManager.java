/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.model;

import com.joblist.model.facades.EmployerLoginFacadeLocal;
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

    public EmployerLogin authenticate(EmployerLogin login) {
        EmployerLogin el = employerLoginFacade.findByUsername(login.getUsername());
        if (el == null) {
            return null;
        }
        if (login.getPassword().compareTo(el.getPassword()) == 0) {
            return el;
        }
        return null;
    }
    
    public boolean isUserNameReserved(String userName) {
        return employerLoginFacade.findByUsername(userName) != null;
    }
    
    public void register(EmployerLogin login) {
        employerLoginFacade.create(login);
    }    
}
