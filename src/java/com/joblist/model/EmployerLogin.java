/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author esa
 */
@Entity
public class EmployerLogin implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Id
    private String username;
    private String password;
    private String email;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof EmployerLogin)) {
            return false;
        }
        EmployerLogin other = (EmployerLogin) object;
        if ((this.username == null && other.username != null) || 
                (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
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
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "com.joblist.model.EmployerLogin[ username=" + username + " ]";
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
}
