/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.model;

import java.io.IOException;
import java.io.InputStream;
import javax.ejb.Local;

/**
 *
 * @author esa
 */
@Local
public interface CVStoreLocal {
    public void storeCV(String fileName, Long id, InputStream in) throws IOException;
    public InputStream loadCV(Long id) throws IOException;
    public boolean remove(Long id);
}
