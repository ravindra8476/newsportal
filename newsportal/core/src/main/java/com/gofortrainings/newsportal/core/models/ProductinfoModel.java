package com.gofortrainings.newsportal.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

//import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables=Resource.class,defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)
public class ProductinfoModel {
	@Inject 
	private String productitle;
	   
	   @Inject @Named("sling:resourceType")@Required
	   private String slingResourceType;
	   
	   @Inject
	   private String text;
	   private static final Logger log=LoggerFactory.getLogger(ArticleinfoModel.class);
	   @PostConstruct
	   public void init() {
		   log.info("Inside Post Construct method");
	   }
	public String getProductitle() {
		return productitle;
	}
	public String getSlingResourceType() {
		return slingResourceType;
	}
	public String getText() {
		return text;
	}
	   
}
