/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.employer;

import com.joblist.model.JobSeeker;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author esa
 */
@Named(value = "employerApplicantDetailsBean")
@SessionScoped
public class ApplicantDetailsBean implements Serializable{
    JobSeeker applicant;

    /**
     * Creates a new instance of applicantDetailsBean
     */
    public ApplicantDetailsBean() {
    }
    
    public void setApplicant(JobSeeker applicant) {
        this.applicant = applicant;
    }
    
    public JobSeeker getApplicant() {
        return applicant;
    }
}
