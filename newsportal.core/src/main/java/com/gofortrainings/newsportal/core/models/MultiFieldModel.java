package com.gofortrainings.newsportal.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = SlingHttpServletRequest.class)
public class MultiFieldModel {
	

	String logoPath;
    
	@ChildResource
	private Resource pages;
	
	@SlingObject
	 Resource resource;
	
	private List<MultiFieldValuesModel> fieldSet;

	@PostConstruct
	public void init() {
		logoPath = resource.getValueMap().get("logoPath", String.class);
		fieldSet=new ArrayList<MultiFieldValuesModel>();
		Iterator<Resource> child=pages.listChildren();
		while (child.hasNext()) {
			MultiFieldValuesModel properties=child.next().adaptTo(MultiFieldValuesModel.class);
			fieldSet.add(properties);
			
		}
			
		}

	public String getLogoPath() {
		return logoPath;
	}

	public List<MultiFieldValuesModel> getFieldList() {
		return fieldSet;
	}
	

}


