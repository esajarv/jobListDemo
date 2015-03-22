/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.controllers.employer;

import java.net.MalformedURLException;
import java.net.URL;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author esa
 */
@FacesConverter("com.joblist.controllers.employer.URLConverter")
public class URLConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        URL url = null;
        try {
            url = new URL(value);
        } catch (MalformedURLException e) {
            FacesMessage msg = new FacesMessage("URL Conversion error.", 
                                                "Invalid URL format.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	    throw new ConverterException(msg);
        }
        return url.toString();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }

}

