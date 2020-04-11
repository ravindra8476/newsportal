package com.gofortrainings.newsportal.core;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.engine.EngineConstants;
import org.osgi.framework.Constants;

@Component (
		name="Newsportal Component",
		service=NewsportalComponent.class,
				property = {
		                   Constants.SERVICE_DESCRIPTION + "=News Portal First OSGI Service/Component"
		}
		  )
  

public class NewsportalComponent {
	final Logger log = LoggerFactory.getLogger(NewsportalComponent.class);
	@Activate
	public void componentActivate() {
		log.info("news portal Activate copmponent");
	}
	@Deactivate
	public void componentDeactivate() {
		log.info("News portalDeactivate Component");
	}
	@Modified
	public void componentModified() {
		log.info("News portal Update Component");
	}
}
