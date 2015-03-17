/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.model.facades;

import com.joblist.model.EmployerLogin;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author esa
 */
@Stateless
public class EmployerLoginFacade extends AbstractFacade<EmployerLogin> implements EmployerLoginFacadeLocal {
    @PersistenceContext(unitName = "JoblistPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployerLoginFacade() {
        super(EmployerLogin.class);
    }
    
}
