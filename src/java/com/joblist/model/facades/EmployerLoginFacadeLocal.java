/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.model.facades;

import com.joblist.model.EmployerLogin;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author esa
 */
@Local
public interface EmployerLoginFacadeLocal {

    void create(EmployerLogin employerLogin);

    void edit(EmployerLogin employerLogin);

    void remove(EmployerLogin employerLogin);

    EmployerLogin find(Object id);

    List<EmployerLogin> findAll();

    List<EmployerLogin> findRange(int[] range);

    int count();
    
}
