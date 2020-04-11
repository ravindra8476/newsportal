package com.gofortrainings.newsportal.core.listeners;

import org.osgi.service.component.annotations.Component;

import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.replication.ReplicationAction;
//import com.day.cq.wcm.api.PageEvent;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;;

@Component(service=EventHandler.class,
           property= {EventConstants.EVENT_TOPIC +"="+ReplicationAction.EVENT_TOPIC,
        		      EventConstants.EVENT_FILTER +"=(&(type=ACTIVATE)(paths=/content/FlaxSEO/en_in/index/*))"
        		      
        		      
        		     // EventConstants.EVENT_TOPIC +"="+PageEvent.EVENT_TOPIC
        		      
        		     }
) 
public class ActivationListener implements EventHandler {
   private static final Logger log=LoggerFactory.getLogger(ActivationListener.class);
   
	@Override
	public void handleEvent(Event event) {
		log.info("Topic ::{}",event.getTopic());
		//log.info("PageEvents Topic ::{}",event.getTopic());
		String[] props=event.getPropertyNames();
		for(String prop:props)
		{
			log.info("Property Name ::{},Property Value ::{}",prop,event.getProperty(prop));
		}
		
	}
	

}
