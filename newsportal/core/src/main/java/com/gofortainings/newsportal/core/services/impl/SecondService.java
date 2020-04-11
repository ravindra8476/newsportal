package com.gofortainings.newsportal.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gofortainings.newsportal.core.services.FirstService;

@Component(name="Second Service OSGI Component",immediate=true)
public class SecondService {
	
	private static final Logger logger = LoggerFactory.getLogger(SecondService.class);
	
	@Reference
	FirstService fs;
	
	@Activate
	public void activate() {
		logger.error("Second Service activated",fs.getInfo());
	}
    @Deactivate
	public void deActivate() {
    	logger.error("Second Service deActivated");
				
	}
    @Modified
	public void update() {
    	logger.error("Second Service modified");
		
	}
}
