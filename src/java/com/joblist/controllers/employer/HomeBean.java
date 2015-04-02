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
import javax.inject.Inject;

/**
 *
 * @author esa
 */
@Named(value = "employerHomeBean")
@SessionScoped
public class HomeBean implements Serializable {
    @Inject
    LoginInfoBean loginInfo;
    
    @Inject
    JobDetailsBean detailsBean;
    
    @EJB
    JobFacadeLocal jobFacade;
    
    private List<Job> jobs;
    
    /**
     * Creates a new instance of HomeBean
     */
    public HomeBean() {
    }
    
    public boolean isClosed(Job job) {
        return job.getState() == Job.STATE_CLOSED;
    }
    
    public String getJobState(Job job) {
        String state;
        switch(job.getState()) {
            case Job.STATE_OPEN:
                state = "Open"; 
                break;
            case Job.STATE_CLOSED:
                state = "Closed";
                break;
            case Job.STATE_CANCELLED:
                state = "Cancelled";
                break;
            case Job.STATE_DONE:
                state = "Done";
                break;
            default:
                state = "Error";
        }
        return state;
    }
    
    public void notifyJobsModified() {
        jobs = null;
    }
    
    public String showDetailsPage(Job job) {
        detailsBean.setJob(job);
        return "jobdetails?faces-redirect=true";
    }

    /**
     * @return the jobs
     */
    public List<Job> getJobs() {
        if (jobs == null) {
            jobs = jobFacade.findAll(loginInfo.getLogin().getId());
        }
        return jobs;
    }
}
