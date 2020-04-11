package com.gofortainings.newsportal.core.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component(service=PersonalInfoService.class,immediate=true)
@Designate(ocd=PersonInfoConfig.class)
public class PersonalInfoService {
	
  
   private static final Logger log=LoggerFactory.getLogger(PersonalInfoService.class);
   
   //private String name="bhasaka technologies";
   private boolean enable = true;
   private String name = "Bhasaka Technologies";
   private String gender="male";
   private String[] nicknames= {"AEM","OSGI"};
   @Activate
   public void activate(PersonInfoConfig config) {
	   log.info("Enable Profile :{}",config.profile_enable());
	   log.info("Profile Name :{}",config.profile_name());
	   log.info("Profile Gender :{}",config.profile_gender());
	   if(config.profile_nicknames()!=null) {
	   log.info("Profile NickNames :{}",config.profile_nicknames().length);
	   }
}
}