package com.gofortrainings.newsportal.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Servlet.class,
    property = {
        "sling.servlet.paths=/apps/newsportal/firstservlet",
        "sling.servlet.paths=/newsportal/firstservlet",
        "sling.servlet.resourceTypes=gofortrainings/components/page/homepage",
        "sling.servlet.methods=POST",
        "sling.servlet.methods=GET",
        "sling.servlet.extensions=html",
        "sling.servlet.selectors=data",
        "sling.servlet.prefix=0"
    }
)
public class FirstServlet extends SlingSafeMethodsServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1 ;
    private static final Logger log = LoggerFactory.getLogger(FirstServlet.class);
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
    throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Welcome to First Servlet");
        ResourceResolver rr = request.getResourceResolver();
        Resource resource = rr.getResource("/content/gofortrainings/en_in/index");
        Node node = resource.adaptTo(Node.class);
        try {
			node.setProperty("text", "value");
			
		} catch (ValueFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (VersionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //	response.setCharacterEncoding("UTF-8");
        String paramName="ravi";
        String paramValue=request.getParameter(paramName);
        log.info("Resource Name:: {}", resource.getName());
        log.info("Content Path :: {}", resource.getPath());

        log.info("User ID :: {}", rr.getUserID());
        log.info("New Resurce Name :: {}", rr.getResource("/apps/gofortrainings/components/content/articleInfo/"));
        log.info("Response Type :: {}", response.getContentType());
        log.info("Response Header Name :: {}", response.getHeader("/content/gofortrainings/en_in/index/jcr:content"));
        log.info("Request Parameter :: {}", paramValue);

    }

}