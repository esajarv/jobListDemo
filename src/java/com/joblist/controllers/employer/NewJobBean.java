/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.employer;

import com.joblist.model.Job;
import com.joblist.model.facades.JobFacadeLocal;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author koulutus
 */
@Named(value = "newJobBean")
@RequestScoped
public class NewJobBean implements Serializable {
    private Job job;
    
    @Inject
    HomeBean homeBean;
    @Inject
    LoginInfoBean loginInfo;
    @EJB
    JobFacadeLocal jobFacade;
    private String applyURL;

    /**
     * Creates a new instance of NewJobBean
     */
    public NewJobBean() {
    }
    
    @PostConstruct
    void init() {
        System.out.println("construct newjobbean: ");
        job = new Job();
        job.setAdvertisementLink("http://");
    }
    
    @PreDestroy
    void free() {
        System.out.println("destroy newjobbean: ");
        job = null;
    }
    
    /**
     * @return the job
     */
    public Job getJob() {
        return job;
    }
    
    public String getSubmitText() {
        return "Add";
    }
    
    public void submit() {
        job.setEmployerID(loginInfo.getLogin().getId());
        jobFacade.create(job);
        homeBean.notifyJobsModified();
        
        String serverRoot = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("JOB_SEEKER_SERVER_ROOT");
        applyURL = serverRoot 
                + "jobseeker/forms/apply.xhtml?jobid="
                + job.getId();
        init();
    }

    /**
     * @return the applyURL
     */
    public String getApplyURL() {
        return applyURL;
    }

    /**
     * @param applyURL the applyURL to set
     */
    public void setApplyURL(String applyURL) {
        this.applyURL = applyURL;
    }
}
