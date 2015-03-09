/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.employer;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author koulutus
 */
@Named(value = "newJobBean")
@RequestScoped
public class NewJobBean implements Serializable {
    private String title;
    private String advertisementLink;
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
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the advertisementLink
     */
    public String getAdvertisementLink() {
        return advertisementLink;
    }

    /**
     * @param advertisementLink the advertisementLink to set
     */
    public void setAdvertisementLink(String advertisementLink) {
        this.advertisementLink = advertisementLink;
    }
    
    public void submit()
    {
        formLink = "<a href=\"http://localhost:8080/Joblist/faces/jobseeker/forms/apply.xhtml?jobid=123\"> Apply </a>";
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
