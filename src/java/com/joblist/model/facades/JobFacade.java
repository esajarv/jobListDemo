/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.model.facades;

import com.joblist.model.Job;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author esa
 */
@Stateless
public class JobFacade extends AbstractFacade<Job> implements JobFacadeLocal {
    @PersistenceContext(unitName = "JoblistPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JobFacade() {
        super(Job.class);
    }
    
    @Override
    public List<Job> findAll(Object employerId) {
        return em.createQuery("SELECT j FROM Job j WHERE j.employerID=?1", Job.class)
                .setParameter(1, employerId)
                .getResultList();
    }
    
    private final static String cancelQuery =
            "Update Job j SET j.state = " + Job.STATE_CANCELLED
                + " WHERE j.employerID=?1 AND j.state <> " + Job.STATE_DONE;
    @Override
    public void cancelAll(Object employerId) {
        em.createQuery(cancelQuery, Job.class)
                .setParameter(1, employerId)
                .executeUpdate();
    }
    
    @Override
    public void deleteAll(Object employerId) {
        em.createQuery("DELETE FROM Job j WHERE j.employerID=?1", Job.class)
                .setParameter(1, employerId)
                .executeUpdate();
    }
    
}
