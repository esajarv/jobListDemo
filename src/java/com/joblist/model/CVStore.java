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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
        Path path = Paths.get(CV_directory, id + ".pdf");
        Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
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
    
    @Override
    public boolean remove(Long id) {
        String filePath = CV_directory + id + ".pdf";
        File f = new File(filePath);
        return f.delete();
    }
}
