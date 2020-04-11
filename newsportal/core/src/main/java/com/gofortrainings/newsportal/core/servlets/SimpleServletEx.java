package com.gofortrainings.newsportal.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;

import java.util.Map; 
import java.util.HashMap;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.api.resource.ValueMap;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;


//AEM Workflow APIs
//import com.aem.community.wf.core.Object;
//import com.aem.community.wf.core.String;
import com.day.cq.workflow.model.WorkflowModel ; 
import com.day.cq.workflow.WorkflowService ; 
import com.day.cq.workflow.WorkflowSession; 
import com.day.cq.workflow.exec.WorkflowData; 

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.jcr.api.SlingRepository;
 
import javax.jcr.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//Use the NODE API
import javax.jcr.Node; 

/**
* A Servlet that invokes an AEM Workflow
*/
@Component(service=Servlet.class,
    property={
            Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
            "sling.servlet.methods=" + HttpConstants.METHOD_GET,
            "sling.servlet.paths="+ "/bin/invokeWF"
       })
public class SimpleServletEx extends SlingSafeMethodsServlet {

private static final long serialVersionUid = 1L;
 
/** Default log. */
protected final Logger log = LoggerFactory.getLogger(this.getClass());
 
@Reference
private WorkflowService workflowService;

private Session session;
   
@Reference
private ResourceResolverFactory resolverFactory;
 
 

@Override
protected void doGet(final SlingHttpServletRequest req,
        final SlingHttpServletResponse resp) throws ServletException, IOException {
     
     
    Map<String, Object> param = new HashMap<String, Object>();
    param.put(ResourceResolverFactory.SUBSERVICE, "datawrite");
    ResourceResolver resolver = null;
        
    try {
                   
        //Invoke the adaptTo method to create a Session used to create a QueryManager
        resolver = resolverFactory.getServiceResourceResolver(param);
        session = resolver.adaptTo(Session.class);
     
        log.info("IN SERVET") ; 
     
        //Need to read the parameters from the AJAX operation 
        String assetPath = req.getParameter("assetPath");
        String email = req.getParameter("email");
     
     
        log.info("IN SERVET - asset path "+assetPath) ; 
        log.info("IN SERVET - email "+email) ;
           
        //Create a workflow session 
    WorkflowSession wfSession = workflowService.getWorkflowSession(session);
                 
   //String workflowName = "/etc/workflow/models/approveasset-"; //need to update with real WF
     
    String workflowName = "/var/workflow/models/approveasset"; //etc/workflow/models/approveasset-
     
    // Get the workflow model
   WorkflowModel wfModel = wfSession.getModel(workflowName); 
                 
    // Get the workflow data
    // The first param in the newWorkflowData method is the payloadType.  Just a fancy name to let it know what type of workflow it is working with.
   WorkflowData wfData = wfSession.newWorkflowData("JCR_PATH", assetPath);
                 
    
   //Before we Send Payload to the Workflow - we need to update email prop on the Payload node
   updateEmailProp(assetPath, email);
      
    
   // Run the Workflow.
    wfSession.startWorkflow(wfModel, wfData);
     
    //Return the JSON formatted data
    resp.getWriter().write("Servlet invoked");
     
     
    session.save(); 
    session.logout();
     
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
 
}
 
 
 
private void updateEmailProp(String path, String email)
{
     
    try{
     
         
    //Create APp ;ogic to change image NODE PROP
    //Create a node that represents the root node
    Node root = session.getRootNode(); 
     
     
    String metaPath = path +"/jcr:content/metadata"  ;
     
     
    log.info("**** ABOUT TO GET PATH"); 
     
  //Remove the first / char - JCR API does not like that
    String newPath = path.replaceFirst("/", "");  
     
    log.info("**** NEW PATH IS "+newPath);
     
    String finalPath = newPath+"/jcr:content/metadata" ;
     
    log.info("**** FINAL PATH IS "+finalPath); 
      
    //This returns the metadata node under the asset node
    Node rcontent = root.getNode(finalPath);
     
    rcontent.setProperty("email", email);
   /*
     
    if (rcontent.hasProperty("email") == false)
   {
       //create email prop on payload 
       log.info("**** Created an EMAIL PROP  - email is " +email);
        
   }
   else
   {
        
   }
   */
    

     
    }
    catch(Exception e)
    {
        e.printStackTrace(); 
    }
}
 
 
 
 
}