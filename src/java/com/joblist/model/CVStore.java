/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

/**
 *
 * @author esa
 */
@Stateless
public class CVStore implements CVStoreLocal {
    private String CV_directory;    
    
    public CVStore() {
        CV_directory = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("CVSTORE.CV_DIRECTORY_PATH");
        if (CV_directory == null || CV_directory.isEmpty()) {
            CV_directory = System.getProperty("user.home") + "/CV/";
        }
    }
    
    @Override
    public void storeCV(String fileName, Long id, InputStream in) throws IOException {
        String filePath = CV_directory + id + ".pdf";
        
        File f = new File(filePath);
        OutputStream out = new FileOutputStream(f);

        int read;
        byte[] bytes = new byte[1024];
        while ((read = in.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        in.close();
        out.flush();
        out.close();
    }
    
    @Override
    public InputStream loadCV(Long id) throws IOException {
        String filePath = CV_directory + id + ".pdf";
        InputStream in = null;
        File f = new File(filePath);
        if (f.exists()) {
            in = new FileInputStream(f);
        }
        return in;
    }
}
