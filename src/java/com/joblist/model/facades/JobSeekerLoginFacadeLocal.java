/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.model.facades;

import com.joblist.model.JobSeekerLogin;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author koulutus
 */
@Local
public interface JobSeekerLoginFacadeLocal {

    void create(JobSeekerLogin login);

    void edit(JobSeekerLogin login);

    void remove(JobSeekerLogin login);

    JobSeekerLogin find(Object id);

    List<JobSeekerLogin> findAll();

    List<JobSeekerLogin> findRange(int[] range);

    int count();
    
}
