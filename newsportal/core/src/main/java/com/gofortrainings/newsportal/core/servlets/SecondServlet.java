package com.gofortrainings.newsportal.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service=Servlet.class,
        property= {
        		    "sling.servlet.resourceTypes=gofortrainings/components/paymentpage",
        		    "sling.servlet.methods=POST",
        		    "sling.servlet.methods=GET",
        		    "sling.servlet.extensions=html",
        		    "sling.servlet.selectors=data",
        		    "sling.servlet.prefix=0"
        		}
)
public class SecondServlet extends SlingSafeMethodsServlet {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log=LoggerFactory.getLogger(SecondServlet.class);
	
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
	          
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("Welcome to Second Servlet");
		Resource resource= request.getResource();
		
		log.info("Resource Name:: {}",resource.getName());
		ResourceResolver rr=request.getResourceResolver();
		
	}

}
