package com.gofortrainings.newsportal.core.schedulers;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import com.adobe.granite.ui.components.Config;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gofortrainings.newsportal.core.listeners.ActivationListener;

@Component(service=Runnable.class)
@Designate(ocd=WhiteBoardSceduler.Config.class)
public class WhiteBoardSceduler implements Runnable {
	private static final Logger log=LoggerFactory.getLogger(ActivationListener.class);
	@ObjectClassDefinition(name="White BoardScheduler Configeration")	
  public static @interface Config {
		@AttributeDefinition(name="cross dedinition ")
	String scheduler_expression() default "*/10 * * * * ?"; 
		//long scheduler_period() default 20;
		//String scheduler_concurrent() default "false";
  }
	@Override
	public void run() {
		log.info("White BoardSceduler Logger Information");
	}
	@Activate
	public void activate(Config config) {
		
	}
}
