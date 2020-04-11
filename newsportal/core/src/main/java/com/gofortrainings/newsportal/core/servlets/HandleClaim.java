package com.gofortrainings.newsportal.core.servlets;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.ServerException;
import java.util.Dictionary;
import org.osgi.service.component.annotations.Reference;

 

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
//import org.apache.sling.commons.osgi.OsgiUtil;
import org.apache.sling.jcr.api.SlingRepository;

import org.osgi.framework.Constants;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Component;

import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.jcr.Node; 
//import org.json.simple.JSONObject;
import java.util.UUID;
 

@Component(service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths="+ "/bin/searchServlet"
   })
public class HandleClaim extends SlingSafeMethodsServlet {
     private static final long serialVersionUID = 2598426539166789515L;
      
     @Reference
     private SlingRepository repository;
      
     public void bindRepository(SlingRepository repository) {
            this.repository = repository; 
            }
           
     @Override
     protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)throws IOException {
       
      try
      {
         //Get the submitted form data that is sent from the
              //CQ web page  
          String id = UUID.randomUUID().toString();
          String firstName = request.getParameter("firstName");
          String lastName = request.getParameter("lastName");
          String address = request.getParameter("address");
          String cat = request.getParameter("cat");
          String state = request.getParameter("state");
          String details = request.getParameter("details");
          String date = request.getParameter("date"); 
          String city = request.getParameter("city"); 
       
          //Encode the submitted form data to JSON
          org.apache.sling.commons.json.JSONObject obj=new org.apache.sling.commons.json.JSONObject();
          obj.put("id",id);
          obj.put("firstname",firstName);
          obj.put("lastname",lastName);
          obj.put("address",address);
          obj.put("cat",cat);
          obj.put("state",state);
          obj.put("details",details);
          obj.put("date",date);
          obj.put("city",city);
           
             //Get the JSON formatted data    
          String jsonData = obj.toString();
           
             //Return the JSON formatted data
         response.getWriter().write(jsonData);
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }
    }
}

