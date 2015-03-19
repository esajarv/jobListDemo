/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.jobseeker;

import com.joblist.model.JobSeeker;
import com.joblist.model.JobSeekerLogin;
import com.joblist.model.facades.JobSeekerLoginFacadeLocal;
import javax.inject.Named;
import java.io.Serializable;
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
    JobSeekerLoginFacadeLocal loginFacade;
    
    private final JobSeeker applicant;
    private UploadedFile CV;
    
    public WizardBean() {
        applicant = new JobSeeker();
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
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public String save() {
        JobSeekerLogin l = loginInfo.getLogin();
        l.setJobSeeker(applicant);
        loginFacade.edit(l);
        return "home.xhtml";
    }
     
    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }
}
