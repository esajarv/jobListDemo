/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.employer;

import com.joblist.model.EmployerLogin;
import com.joblist.model.EmployerLoginManager;
import com.joblist.model.facades.EmployerLoginFacadeLocal;
import com.joblist.model.facades.JobFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author esa
 */
@Named(value = "employerSettingsBean")
@SessionScoped
public class SettingsBean implements Serializable {
    private String oldPassword;
    private String newPassword;
    private UIComponent passwordField;
    
    @Inject
    LoginInfoBean loginInfo;
    @Inject 
    TitleBarBean titleBar;
    @EJB
    EmployerLoginFacadeLocal loginFacade;
    @EJB
    JobFacadeLocal jobFacade;
    @EJB 
    EmployerLoginManager loginManager;    

    /**
     * Creates a new instance of SettingsBean
     */
    public SettingsBean() {
    }
    
    public String deleteAccount() {
        System.out.println("delete account");
        EmployerLogin login = loginInfo.getLogin();
        jobFacade.cancelAll(login.getId());
        loginFacade.remove(login);
        return titleBar.logout();
    }
    
    public void changePassword()
    {
        FacesContext fc = FacesContext.getCurrentInstance();
        EmployerLogin login = loginInfo.getLogin();
        try {
            if (loginManager.authenticate(login, oldPassword) != null) {
                loginManager.changePassword(login, newPassword);
                fc.addMessage(null, new FacesMessage("Password changed."));
            } else {
                FacesMessage msg = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Invalid password", "Invalid password");
                fc.addMessage(passwordField.getClientId(fc), msg);
            }
        } catch(Exception e) {
            fc.addMessage(null, new FacesMessage("Changing the password failed."));
        }
    }

    /**
     * @return the oldPassword
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * @param oldPassword the old password to set
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return the passwordField
     */
    public UIComponent getPasswordField() {
        return passwordField;
    }

    /**
     * @param passwordField the passwordField to set
     */
    public void setPasswordField(UIComponent passwordField) {
        this.passwordField = passwordField;
    }
}
