package com.gofortainings.newsportal.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name = "Person info configuration")
public @interface PersonInfoConfig {
	
	@AttributeDefinition(name = "Enable Profile")
	public boolean profile_enable();
	
	@AttributeDefinition(name = "Enable Profile ",options= {
			@Option(label="MALE",value="male"),
			@Option(label="FEMALE",value="female")

	})
	public String profile_gender();
	
	@AttributeDefinition(name = "Profile Name")
	public String profile_name();
	
	@AttributeDefinition(name = "Profile nickName ")
	public String[] profile_nicknames();

}
