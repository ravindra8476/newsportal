package com.gofortrainings.newsportal.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables=Resource.class)
public class MultiFieldValuesModel {
	
	@ValueMapValue
	private String text;

	

	


	@ValueMapValue
	private String path;


	public String getText() {
		return text;
	}


	public String getPath() {
		return path;
	}
	
	

	
	
	
	

	

}