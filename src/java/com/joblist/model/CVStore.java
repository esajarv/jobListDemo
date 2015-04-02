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
    private final Path CV_directory;
    
    public CVStore() {
        String CV_dir = FacesContext.getCurrentInstance().getExternalContext()
                .getInitParameter("CVSTORE.CV_DIRECTORY_PATH");
        if (CV_dir == null || CV_dir.isEmpty()) {
            CV_directory = Paths.get(System.getProperty("user.home"), "CV");
            System.out.println("home: " + CV_directory);
        } else {
            CV_directory = Paths.get(CV_dir);
        }
    }
    
    @Override
    public void storeCV(String fileName, Long id, InputStream in) throws IOException {
        Path path = CV_directory.resolve(id + ".pdf");
        Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
    }
    
    @Override
    public InputStream loadCV(Long id) throws IOException {
        Path path = CV_directory.resolve(id + ".pdf");
        return Files.newInputStream(path);
    }
    
    @Override
    public boolean remove(Long id) throws IOException {
        Path path = CV_directory.resolve(id + ".pdf");
        return path.getFileSystem().provider().deleteIfExists(path);
    }
}
