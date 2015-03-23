/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.employer;

import com.joblist.model.Job;
import com.joblist.model.JobSeeker;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author esa
 */
@Named(value = "employerJobDetailsBean")
@SessionScoped
public class JobDetailsBean implements Serializable {
    private Job job;

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
    }
    
    public List<JobSeeker> getJobSeekers() {
        return job.getJobSeekers();
    }
    
}
