/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.jobseeker;

import com.joblist.model.CVStoreLocal;
import com.joblist.model.JobSeeker;
import com.joblist.model.facades.JobSeekerFacadeLocal;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

//ui-wizard-nav-next
//ui-wizard-nav-back
/**
 *
 * @author esa
 */
@Named(value = "wizardBean")
@SessionScoped
public class WizardBean implements Serializable {
    @Inject LoginInfoBean loginInfo;
    @EJB
    JobSeekerFacadeLocal jobSeekerFacade;
    @EJB
    CVStoreLocal CVStore;
    
    private JobSeeker applicant;
    private UploadedFile CV;
    
    public WizardBean() {
    }
    
    @PostConstruct
    public void init() {
        applicant = loginInfo.getJobSeeker();
    }

    public JobSeeker getApplicant() {
        return applicant;
    }
    
    public UploadedFile getCV() {
        return CV;
    }
 
    public void setCV(UploadedFile CV) {
        this.CV = CV;
    }
    
    public void upload(FileUploadEvent event) {
        FacesContext fc = FacesContext.getCurrentInstance();
        
        try {
            CVStore.storeCV(event.getFile().getFileName(), 
                    applicant.getId(), event.getFile().getInputstream());
        } catch (IOException e) {
            FacesMessage message = new FacesMessage("uploading failed.");
            fc.addMessage(null, message);
            return;
        }
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        fc.addMessage(null, message);
        System.out.println("upload done");
    }

    public String save() {
        applicant.setWizardSubmitted(true);
        jobSeekerFacade.edit(applicant);
        return "home?faces-redirect=true";
    }
     
    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }
}
