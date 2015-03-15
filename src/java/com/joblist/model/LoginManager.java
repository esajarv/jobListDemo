/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.model;

import com.joblist.model.facades.LoginFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

/**
 *
 * @author koulutus
 */
@Stateless
public class LoginManager {
    @EJB
    LoginFacadeLocal loginFacade;
    
    public Login authenticate(Login login) {
        Login f = loginFacade.find(login.getUsername());
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
        return loginFacade.find(userName) != null;
    }
    
    public void register(Login login) {
        loginFacade.create(login);
    }
}
