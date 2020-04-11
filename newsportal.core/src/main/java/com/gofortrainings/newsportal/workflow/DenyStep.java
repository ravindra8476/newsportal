package com.gofortrainings.newsportal.workflow;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
     
  
import javax.jcr.Session;
import javax.jcr.Node; 
  
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
      
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
      
import javax.jcr.Repository; 
import javax.jcr.SimpleCredentials; 
import javax.jcr.Node; 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
  
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import com.day.cq.dam.api.Asset; 
import java.util.Collections;
       
import org.apache.jackrabbit.commons.JcrUtils;
      
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
  
      
import javax.jcr.Session;
import javax.jcr.Node; 
import org.osgi.framework.Constants;
    
import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
  
//AssetManager
import com.day.cq.dam.api.AssetManager; 
  
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream ; 
import java.io.OutputStream ; 
import java.io.ByteArrayInputStream ; 
import java.io.FileOutputStream ; 
    
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
    
     
//Sling Imports
import org.apache.sling.api.resource.ResourceResolverFactory ; 
import org.apache.sling.api.resource.ResourceResolver; 
import org.apache.sling.api.resource.Resource; 
import com.day.cq.wcm.api.Page; 
     
  
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
  
import com.adobe.granite.workflow.model.WorkflowNode;
import com.adobe.granite.workflow.exec.WorkflowData;
  
  
@Component(service=WorkflowProcess.class, property = {"process.label=Deny Process"})
  
public class DenyStep implements WorkflowProcess 
{
          
         
/** Default log. */
protected final Logger log = LoggerFactory.getLogger(this.getClass());
         
//Inject a MessageGatewayService 
//Inject a Sling ResourceResolverFactory
@Reference
private ResourceResolverFactory resolverFactory;
   
private Session session;
         
public void execute(WorkItem item, WorkflowSession wfsession,MetaDataMap args) throws WorkflowException {
             
try
{
    log.info("**** Here in execute method");    //ensure that the execute method is invoked    
             
    //Get the Assets from the file system for a test
    WorkflowNode myNode = item.getNode(); 
       
    String myTitle = myNode.getTitle(); //returns the title of the workflow step
       
    log.info("**** The title is "+myTitle);  
       
    WorkflowData workflowData = item.getWorkflowData(); //gain access to the payload data
    String path = workflowData.getPayload().toString();//Get the path of the asset
       
       
    //Get only the name of the asset - including the ext
    int index = path.lastIndexOf("/");
    String fileName = path.substring(index + 1);
         
            
   log.info("**** This asset was rejected " +fileName );  
}
     
    catch (Exception e)
    {
    e.printStackTrace()  ; 
    }
 }
   
 
}