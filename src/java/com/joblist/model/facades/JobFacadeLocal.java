/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.model.facades;

import com.joblist.model.Job;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author esa
 */
@Local
public interface JobFacadeLocal {

    void create(Job job);

    void edit(Job job);

    void remove(Job job);

    Job find(Object id);
    
    void refresh(Job job);

    List<Job> findAll();
    List<Job> findAll(Object employerId);
    void cancelAll(Object employerId);
    void deleteAll(Object employerId);
    List<Job> findRange(int[] range);

    int count();
    
}
