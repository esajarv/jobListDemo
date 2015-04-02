/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author esa
 */
@Entity
public class Job implements Serializable {
    public static final int STATE_OPEN = 0;      //open for apply
    public static final int STATE_CLOSED = 1;    //closed for apply
    public static final int STATE_DONE = 2;      //employee found
    public static final int STATE_CANCELLED = 3; //cancelled
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long employerID;
    private String title;
    private String advertisementLink;
    private int jobState;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    
    @ManyToMany(mappedBy = "jobs")
    private List<JobSeeker> jobSeekers;

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
        if (!(object instanceof Job)) {
            return false;
        }
        Job other = (Job) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.joblist.model.Job[ id=" + id + " ]";
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

    /**
     * @return the state
     */
    public int getState() {
        return jobState;
    }

    /**
     * @param state the closed to set
     */
    public void setState(int state) {
        this.jobState = state;
    }

    /**
     * @return the employerID
     */
    public Long getEmployerID() {
        return employerID;
    }

    /**
     * @param employerID the employerID to set
     */
    public void setEmployerID(Long employerID) {
        this.employerID = employerID;
    }

    /**
     * @return the jobSeekers
     */
    public List<JobSeeker> getJobSeekers() {
        return jobSeekers;
    }

    /**
     * @param jobSeekers the jobSeekers to set
     */
    public void setJobSeekers(List<JobSeeker> jobSeekers) {
        this.jobSeekers = jobSeekers;
    }

    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }
}
