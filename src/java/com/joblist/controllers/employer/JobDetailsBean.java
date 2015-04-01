/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.employer;

import com.joblist.model.CVStoreLocal;
import com.joblist.model.Job;
import com.joblist.model.JobSeeker;
import com.joblist.model.facades.JobFacadeLocal;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

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
    private int state;
    @EJB
    JobFacadeLocal jobFacade;
    @EJB
    CVStoreLocal CVStore;
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
        this.job = job;
        state = job.getState();
        applicants = null;
        applyURL = null;
    }
    
    public boolean getStateNeedSave() {
        return state != job.getState();
    }
    
    public List<JobSeeker> getJobSeekers() {
        if (applicants == null) {
            applicants = jobFacade.find(job.getId()).getJobSeekers();
        }
        return applicants;
    }
    
    public String refresh() {
        applicants = null;
        return "jobdetails";
    }
    
    public String getSubmitText() {
        return "Change";
    }
    
    public void updateValue() {
        job.setState(state);
        jobFacade.edit(job);
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
    
    public boolean stateDisabled(int state) {
        switch(job.getState()) {
            case Job.STATE_OPEN:
                return false;
            case Job.STATE_CLOSED:
                return state == Job.STATE_CANCELLED;
            case Job.STATE_CANCELLED:
                return state == Job.STATE_CLOSED ||
                       state == Job.STATE_DONE;
            case Job.STATE_DONE:
                return !(state == Job.STATE_DONE);
            default:
                return true;
        }
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

    /**
     * @param applicant
     * @return the CV File
     */
    public StreamedContent getCVFile(JobSeeker applicant) {
        System.out.println("getCVFile: " + applicant.getId());
        StreamedContent file = null;
        try {
            InputStream in = CVStore.loadCV(applicant.getId());
            file = new DefaultStreamedContent(in, "application/pdf", "cv.pdf");
        } catch (IOException ex) {
            FacesMessage message = new FacesMessage("download failed.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        
        return file;
    }
}
