package com.gofortrainings.newsportal.core.schedulers;

import java.io.Serializable;
import java.util.Map;

import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gofortrainings.newsportal.core.listeners.ActivationListener;

@Component
public class SchedulerAPIImpl {
	private static final Logger log=LoggerFactory.getLogger(SchedulerAPIImpl.class);
  @Reference
  Scheduler scheduler;
  @Activate
  public void activate() throws Exception {
	  Runnable job=new Runnable() {

		@Override
		public void run() {
		
			log.info("Log information SchedularAPI");
		}
  };
        //scheduler.addJob("job1", job, null, "*/60 * * * * ?", false);
        scheduler.addPeriodicJob("job2", job, null, 30, true);
         // scheduler.fireJobAt("job3", job, config3, date);
        //scheduler.PROPERTY_SCHEDULER_CONCURRENT;
        
        
}
}
