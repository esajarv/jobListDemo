/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joblist.Filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.UriBuilder;

//https://zenidas.wordpress.com/recipes/java-web-filter-with-ajax-requests/
/**
 * This is configured in web.xml.
 * When accessing the login page, URIIsLogin is set to true, false otherwise.
 * @author koulutus
 */
public class AuthorizationFilter implements Filter {
    
    private String loginPagePath;
    private String loginUrlParameter;
    private String loggedInRedirectPath;
    
    private URI createRedirectUri(ServletRequest request, String path) {
        UriBuilder ub = UriBuilder.fromPath(path);
        String values[] = request.getParameterValues(loginUrlParameter);
        if (values != null) {
            ub.queryParam(loginUrlParameter, values[0]);
        }
        return ub.build();
    }
    
    private boolean isAJAXRequest(HttpServletRequest request) {
      boolean check = false;
      String facesRequest = request.getHeader("Faces-Request");
      if (facesRequest != null && facesRequest.equals("partial/ajax")) {
        check = true;
      }
      return check;
    }
    
    private void redirect(HttpServletRequest request,
            HttpServletResponse response, 
            String path) throws IOException{
        if (isAJAXRequest(request)) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/xml");
            PrintWriter pw = response.getWriter();
            pw.println(sb.toString());
            pw.flush();
        } else {
            response.sendRedirect(path);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (filterConfig != null) {
            loginPagePath = filterConfig.getInitParameter("loginPagePath");
            loggedInRedirectPath = filterConfig.getInitParameter("loggedInRedirectPath");
            loginUrlParameter = filterConfig.getInitParameter("loginUrlParameter");
        }
    }

    @Override
    public void doFilter(ServletRequest request, 
                         ServletResponse response, 
                         FilterChain chain) throws IOException, ServletException {
        //sendRedirect(req.getContextPath() + "/facese/login.xhtml")
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        System.out.println("AuthorizationFilter.doFilter: " + httpServletRequest.getRequestURI());
        
        HttpSession session = httpServletRequest.getSession(false);
        Object userObj = null;
        if (session != null) {
            userObj = session.getAttribute("username");
        }
        if (userObj == null) {
            if (loginPagePath != null) {
                //not logged in, redirect to login page.
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                URI uri = createRedirectUri(request, loginPagePath);
                redirect(httpServletRequest, httpServletResponse, uri.toString());
            } else {
                chain.doFilter(request, response);
            }
        } else {
            if (loggedInRedirectPath != null) {
                //accessing login page, but already logged -> skip it.
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                redirect(httpServletRequest, httpServletResponse, loggedInRedirectPath);
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {
    }
    
}
