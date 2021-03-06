/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author koulutus
 */
@Entity
public class JobSeeker implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String firstname;
    private String lastname;
    private String street;
    private String city;
    private String postalCode;
    private String info;
    private String email;
    private String phone;
    private boolean wizardSubmitted;
    
    @OneToOne(mappedBy = "jobSeeker")
    private JobSeekerLogin login;
    
    @ManyToMany
    private List<Job> jobs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobSeeker)) {
            return false;
        }
        JobSeeker other = (JobSeeker) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
 
    public String getFirstname() {
        return firstname;
    }
 
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
 
    public String getLastname() {
        return lastname;
    }
 
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
 
    public String getStreet() {
        return street;
    }
 
    public void setStreet(String street) {
        this.street = street;
    }
 
    public String getCity() {
        return city;
    }
 
    public void setCity(String city) {
        this.city = city;
    }
 
    public String getPostalCode() {
        return postalCode;
    }
 
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
 
    public String getInfo() {
        return info;
    }
 
    public void setInfo(String info) {
        this.info = info;
    }
     
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public String getPhone() {
        return phone;
    }
 
    public void setPhone(String phone) {
        this.phone = phone;
    }    

    @Override
    public String toString() {
        return "com.joblist.model.JobSeeker[ id=" + id + " ]";
    }

    /**
     * @return the login
     */
    public JobSeekerLogin getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(JobSeekerLogin login) {
        this.login = login;
    }

    /**
     * @return the jobs
     */
    public List<Job> getJobs() {
        return jobs;
    }

    /**
     * @return the wizardSubmitted
     */
    public boolean isWizardSubmitted() {
        return wizardSubmitted;
    }

    /**
     * @param wizardSubmitted the wizardSubmitted to set
     */
    public void setWizardSubmitted(boolean wizardSubmitted) {
        this.wizardSubmitted = wizardSubmitted;
    }
    
}
