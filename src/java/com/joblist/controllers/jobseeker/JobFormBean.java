/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.jobseeker;

import com.joblist.model.Job;
import com.joblist.model.JobSeeker;
import com.joblist.model.facades.JobFacadeLocal;
import com.joblist.model.facades.JobSeekerFacadeLocal;
import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

class JobComparator implements Comparator<Job> {

    @Override
    public int compare(Job o1, Job o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}

/**
 *
 * @author esa
 */
@Named(value = "jobFormBean")
@SessionScoped
public class JobFormBean implements Serializable{
    @Inject
    LoginInfoBean loginInfo;
    
    @EJB
    JobFacadeLocal jobFacade;
    
    @EJB
    JobSeekerFacadeLocal jobSeekerLocal;
    
    final private Map<Job, Long> jobs = new TreeMap<>(new JobComparator());
    final private Map<Long, Job> jobIDs = new HashMap<>();
    
    /**
     * Creates a new instance of JobFormBean
     */
    public JobFormBean() {
    }
    
    public boolean getLoggedWithJob() {
        return !jobIDs.isEmpty();
    }
    
    public Iterable<Job> getJobs() {
        return jobs.keySet();
    }
    
    public void applyJob(Long jobID) {
        JobSeeker js = loginInfo.getLogin().getJobSeeker();
        js.getJobs().add(jobIDs.get(jobID));
        jobSeekerLocal.edit(js);
        removeJob(jobID);
    }
    
    public void removeJob(Long jobID) {
        Job j = jobIDs.remove(jobID);
        jobs.remove(j);
    }
    
    public void addJobID(Long jobID) {
        Job j = jobFacade.find(jobID);
        if (j != null) {
            jobIDs.put(jobID, j);
            jobs.put(j, jobID);
        }
    }
    
    public void addJobID(String jobID) {
        Long iJob = parseJob(jobID);
        if (iJob != null) {
            addJobID(iJob);
        }
    }
    
    private Long parseJob(Object jobID) {
        Long j = null;
        try {
            j = Long.parseLong(jobID.toString());
        } catch(NumberFormatException e) {}
        return j;
    }
}
