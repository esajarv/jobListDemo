/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author koulutus
 */
@Entity
public class JobSeekerLogin implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private String username;
    @NotNull
    private byte[] password;
    @NotNull
    private byte[] IV;
    @NotNull
    private byte[] salt;
    
    @OneToOne(cascade = CascadeType.ALL)
    private JobSeeker jobSeeker;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof JobSeekerLogin)) {
            return false;
        }
        JobSeekerLogin other = (JobSeekerLogin) object;
        if ((this.username == null && other.username != null) || 
                (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.joblist.model.Login[ username=" + username + " ]";
    }

    /**
     * @return the user name
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param name the user name to set.
     */
    public void setUsername(String name) {
        this.username = name;
    }

    /**
     * @return the password
     */
    public byte[] getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(byte[] password) {
        this.password = password;
    }

    /**
     * @return the wizardDone
     */
    public boolean isWizardSubmitted() {
        return jobSeeker != null && jobSeeker.isWizardSubmitted();
    }

    /**
     * @return the jobSeeker
     */
    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    /**
     * @param jobSeeker the jobSeeker to set
     */
    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    /**
     * @return the IV
     */
    public byte[] getIV() {
        return IV;
    }

    /**
     * @param IV the IV to set
     */
    public void setIV(byte[] IV) {
        this.IV = IV;
    }

    /**
     * @return the salt
     */
    public byte[] getSalt() {
        return salt;
    }

    /**
     * @param salt the salt to set
     */
    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}
