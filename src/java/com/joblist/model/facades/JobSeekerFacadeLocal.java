/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.model.facades;

import com.joblist.model.JobSeeker;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author koulutus
 */
@Local
public interface JobSeekerFacadeLocal {

    void create(JobSeeker jobSeeker);

    void edit(JobSeeker jobSeeker);

    void remove(JobSeeker jobSeeker);

    JobSeeker find(Object id);

    List<JobSeeker> findAll();

    List<JobSeeker> findRange(int[] range);

    int count();
    
}
