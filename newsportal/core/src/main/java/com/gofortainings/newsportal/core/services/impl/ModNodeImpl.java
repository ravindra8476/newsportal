package com.gofortainings.newsportal.core.services.impl;

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
import com.gofortainings.newsportal.core.services.ModNode;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
  
import com.adobe.granite.workflow.model.WorkflowNode;
import com.adobe.granite.workflow.exec.WorkflowData;
 
//Use the NODE API
import javax.jcr.Node; 
 
import java.util.Map; 
import java.util.HashMap;
  
  
@Component
 
public class ModNodeImpl implements ModNode{
     
    @Reference
    private ResourceResolverFactory resolverFactory;
     
       
    private Session session;
     
    /** Default log. */
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
     
    public void updateNode(String path)
    {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(ResourceResolverFactory.SUBSERVICE, "datawrite");
        ResourceResolver resolver = null;
            
        try {
                       
            //Invoke the adaptTo method to create a Session used to create a QueryManager
            resolver = resolverFactory.getServiceResourceResolver(param);
            session = resolver.adaptTo(Session.class);
             
          
            //Create a node that represents the root node
            Node root = session.getRootNode(); 
             
             
            String metaPath = path +"/jcr:content/metadata"  ;
             
             
            log.info("**** ABOUT TO GET PATH"); 
             
          //Remove the first / char - JCR API does not like that
            String newPath = path.replaceFirst("/", "");   
             
            String finalPath = newPath+"/jcr:content/metadata" ;
              
            //This returns the metadata node under the asset node
            Node rcontent = root.getNode(finalPath);
             
            String ttt = rcontent.getPath(); 
             
            log.info("**** This meta path is " + ttt); 
             
            rcontent.setProperty("xmpRights:UsageTerms", "Approved"); 
             
            session.save(); 
            session.logout();
        }
        catch (Exception e)
        {
            e.printStackTrace()  ;
        }
    }
 
}