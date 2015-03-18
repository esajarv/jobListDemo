/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.employer;

import com.joblist.model.Job;
import com.joblist.model.facades.JobFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;

/**
 *
 * @author esa
 */
@Named(value = "employerHomeBean")
@SessionScoped
public class HomeBean implements Serializable {
    
    @EJB
    JobFacadeLocal jobFacade;
    
    private List<Job> jobs;
    
    /**
     * Creates a new instance of HomeBean
     */
    public HomeBean() {
    }
    
    public void closeJob(Job job) {
        System.out.println("close job");
        job.setClosed(true);
        jobFacade.edit(job);
    }
    
    public void notifyJobsModified() {
        jobs = null;
    }

    /**
     * @return the jobs
     */
    public List<Job> getJobs() {
        if (jobs == null) {
            jobs = jobFacade.findAll();
        }
        return jobs;
    }
}
