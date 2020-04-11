package com.gofortrainings.newsportal.workflow;

import java.util.List;

import javax.jcr.Session;

/*import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Service;*/
//Sling Imports
import org.apache.sling.api.resource.ResourceResolverFactory ;
import org.apache.sling.caconfig.annotation.Property;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.HistoryItem;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.adobe.granite.workflow.model.WorkflowNode;
  
  
@Component(service=WorkflowProcess.class, property = {"process.label=Good Process"})
/*@Component
@Service
@Properties({
        @Property(name = Constants.SERVICE_DESCRIPTION, value = "Riverbed - Good Process"),
        @Property(name = Constants.SERVICE_VENDOR, value = "Rauxa"),
        @Property(name = "process.label", value = "Riverbed - Good Process") }) */
public class ApproveStep implements WorkflowProcess 
{
          
         
/** Default log. */
protected final Logger log = LoggerFactory.getLogger(this.getClass());
         
//Inject a MessageGatewayService 
//Inject a Sling ResourceResolverFactory
@Reference
private ResourceResolverFactory resolverFactory;
 
 
@Reference
private com.gofortainings.newsportal.core.services.ModNode theNode;  

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
         
   String approveUser =   getUserWhomApproved(wfsession,item);
    
    
   //Get the user whom approved the workflow
   log.info("**** This asset was accepted " +fileName +" and approved by "+approveUser);  
    
   theNode.updateNode(path) ; 
    
    
}
     
    catch (Exception e)
    {
    e.printStackTrace()  ; 
    }
 }
 
//Gets the User from approves the payload
 private String getUserWhomApproved(WorkflowSession wfsession,WorkItem item)
 {
          
     try{
     List<HistoryItem> historyList = wfsession.getHistory(item.getWorkflow());
 
      int listSize = historyList.size();
 
      // log.info("listSize = {}", listSize);
 
      HistoryItem lastItem = historyList.get(listSize - 1);
 
      String lastComment = lastItem.getComment();
 
      String lastAction = lastItem.getAction();
 
      String lastUser = lastItem.getUserId();
      return lastUser; 
     }
         
        catch (Exception e)
        {
        e.printStackTrace()  ; 
        }
      return "error  - no user" ; 
       
      
 }     
  
   
 
}
