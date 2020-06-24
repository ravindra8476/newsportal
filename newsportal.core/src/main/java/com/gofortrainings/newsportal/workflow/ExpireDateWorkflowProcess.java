package com.gofortrainings.newsportal.workflow;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service=WorkflowProcess.class,
       property= {"process.label=Update Expiry Date "}
		)
public class ExpireDateWorkflowProcess implements WorkflowProcess {

	@Reference
	private ResourceResolverFactory factory;
	@Override
	public void execute(WorkItem workItem, WorkflowSession wfSession, MetaDataMap metadata) throws WorkflowException {
		String payload = workItem.getWorkflowData().getPayload().toString();
		
		try {
			Map<String, Object> props = new HashMap<>();
			props.put(factory.SUBSERVICE, "writeservice");
			ResourceResolver resolver = factory.getServiceResourceResolver(props);
			Resource resource = resolver.getResource(payload + "/jcr:content"); 
			if (resource !=null && payload !=null) {
				Node node = resource.adaptTo(Node.class);
				Calendar cal= Calendar.getInstance();
				cal.set(Calendar.DATE, 20);
				node.setProperty("expiryDate", cal);
				node.getSession().save();
			}
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
