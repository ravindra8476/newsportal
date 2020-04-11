package com.gofortainings.newsportal.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gofortainings.newsportal.core.services.FirstService;

@Component(service = FirstServiceImpl.class,name="First Service Component",immediate=true)
public class FirstServiceImpl implements FirstService {
	
	private static final Logger logger = LoggerFactory.getLogger(FirstServiceImpl.class);
	
	@Activate
	public void activate() {
		logger.error("First Service activated");
	}
    @Deactivate
	public void deActivate() {
    	logger.error("First Service deActivated");
				
	}
    @Modified
	public void update() {
    	logger.error("First Service modified");
		
	}
	@Override
	public String getInfo() {
		
		return "First OSGI Service";
	}
}
