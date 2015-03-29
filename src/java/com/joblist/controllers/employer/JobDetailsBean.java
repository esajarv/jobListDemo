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
import javax.inject.Inject;

/**
 *
 * @author esa
 */
@Named(value = "employerJobDetailsBean")
@SessionScoped
public class JobDetailsBean implements Serializable {
    private Job job;
    private String applyURL;
    List<JobSeeker> applicants;
    @EJB
    JobFacadeLocal jobFacade;
    @Inject
    ApplicantDetailsBean applicantDetailsBean;

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
        this.job = jobFacade.find(job.getId());
        applicants = null;
        applyURL = null;
    }
    
    public List<JobSeeker> getJobSeekers() {
        if (applicants == null) {
            applicants = job.getJobSeekers();
        }
        return applicants;
    }
    
    public String refresh() {
        this.job = jobFacade.find(job.getId());
        applicants = null;
        return "jobdetails";
    }
    
    public String getSubmitText() {
        return "Change";
    }
    
    public String submit() {
        jobFacade.edit(job);
        return "jobdetails?faces-redirect=true";
    }
    
    public String showDetailsPage(JobSeeker applicant) {
        applicantDetailsBean.setApplicant(applicant);
        return "applicantdetails?faces-redirect=true";
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
}
