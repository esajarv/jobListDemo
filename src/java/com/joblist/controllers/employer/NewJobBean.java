/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.employer;

import com.joblist.model.Job;
import com.joblist.model.facades.JobFacadeLocal;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author koulutus
 */
@Named(value = "newJobBean")
@RequestScoped
public class NewJobBean implements Serializable {
    private Job job = new Job();
    
    @Inject
    HomeBean homeBean;
    
    @Inject
    LoginInfoBean loginInfo;
    @EJB
    JobFacadeLocal jobFacade;
    private String formLink;

    /**
     * Creates a new instance of NewJobBean
     */
    public NewJobBean() {
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return job.getTitle();
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        job.setTitle(title);
    }

    /**
     * @return the advertisementLink
     */
    public String getAdvertisementLink() {
        return job.getAdvertisementLink();
    }

    /**
     * @param advertisementLink the advertisementLink to set
     */
    public void setAdvertisementLink(String advertisementLink) {
        job.setAdvertisementLink(advertisementLink);
    }
    
    public void submit()
    {
        job.setEmployerID(loginInfo.getLogin().getId());
        jobFacade.create(job);
        homeBean.notifyJobsModified();
        formLink = "<a href=\"http://localhost:8080/joblist/faces/jobseeker/forms/apply.xhtml?jobid="
                + job.getId() + "\"> Apply </a>";
    }

    /**
     * @return the formLink
     */
    public String getFormLink() {
        return formLink;
    }

    /**
     * @param formLink the formLink to set
     */
    public void setFormLink(String formLink) {
        this.formLink = formLink;
    }
}
