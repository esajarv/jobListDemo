/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.jobseeker;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author koulutus
 */
@Named(value = "addToApplyListBean")
@RequestScoped
public class AddToApplyListBean {
    @Inject LoginInfoBean loginInfo;
    @Inject HomeBean jobForm;
    private String jobID;

    /**
     * Creates a new instance of AddJobBean
     */
    public AddToApplyListBean() {
    }
    
    public void add() throws IOException {
        System.out.println("AddJobBean.addJob: jobid = " + jobID);
        ExternalContext c = FacesContext.getCurrentInstance().getExternalContext();
        jobForm.addJobID(jobID);
        if (loginInfo.isWizardSubmitted()) {
            c.redirect("home.xhtml");
        } else {
            c.redirect("wizard.xhtml");
        }
    }

    /**
     * @return the jobID
     */
    public String getJobID() {
        return jobID;
    }

    /**
     * @param jobID the jobID to set
     */
    public void setJobID(String jobID) {
        this.jobID = jobID;
    }
}
