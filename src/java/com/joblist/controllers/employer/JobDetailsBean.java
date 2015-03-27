/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.employer;

import com.joblist.model.Job;
import com.joblist.model.JobSeeker;
import com.joblist.model.facades.JobFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author esa
 */
@Named(value = "employerJobDetailsBean")
@SessionScoped
public class JobDetailsBean implements Serializable {
    private Job job;
    private String applyURL;
    private int state;
    List<JobSeeker> applicants;
    @EJB
    JobFacadeLocal jobFacade;

    /**
     * Creates a new instance of JobDetailsBean
     */
    public JobDetailsBean() {
    }

    /**
     * @return the job
     */
    public Job getJob() {
        return job;
    }

    /**
     * @param job the job to set
     */
    public void setJob(Job job) {
        this.job = job;
        applicants = null;
        applyURL = null;
    }
    
    public List<JobSeeker> getJobSeekers() {
        if (applicants == null) {
            applicants = job.getJobSeekers();
        }
        return applicants;
    }
    
    public String getSubmitText() {
        return "Change";
    }
    
    public String submit() {
        jobFacade.edit(job);
        return "jobdetails?faces-redirect=true";
    }

    /**
     * @return the applyURL
     */
    public String getApplyURL() {
        if (applyURL == null) {
            String serverRoot = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("JOB_SEEKER_SERVER_ROOT");
            applyURL = serverRoot 
                    + "jobseeker/forms/apply.xhtml?jobid="
                    + job.getId();
            
        }
        return applyURL;
    }

    /**
     * @return the state
     */
    public int getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(int state) {
        this.state = state;
    }
    
}
