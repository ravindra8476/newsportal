package com.gofortrainings.newsportal.core.models;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
//import com.day.cq.wcm.api.designer.Style;

public class TitleUsePojo extends WCMUsePojo {
	
	private String title;
	
	private String link;
	
	private List<String> months;

	@Override
	public void activate() throws Exception {
		ValueMap properties = getProperties();
		//ValueMap pageProperties = getPageProperties();
	    //Style currentStyle	= getCurrentStyle();
		
		title = properties.get("title", String.class);
		link = properties.get("link", String.class);
		if(link!=null) {
			link = link + ".html";
		}
		String[] monthsArr = properties.get("months", String[].class);
		if(monthsArr!=null) {
			months = Arrays.asList(monthsArr);
		}
		
		PageManager pageManager = getPageManager();
		Page page = pageManager.getPage("/content/gofortrainings/en_in/index");
		String pageTitle = page.getPageTitle();
		ValueMap indexProperties = page.getProperties();
		Iterator<Page> childList = page.listChildren();
		while(childList.hasNext()) {
			Page page2 = childList.next();
		}
		
		/*ResourceResolver resolver = getResourceResolver();
		Resource titleResource = resolver.getResource("/content/newsportal/en/index/jcr:content/root/helloworld");
		if(resolver.isLive()) {
			resolver.close();
		}*/
		
		Resource resource = getResource();
		Node node = resource.adaptTo(Node.class);
		node.setProperty("title", "tile is added from backend");
		Session jcrSession = node.getSession();
		jcrSession.save();
		if(jcrSession.isLive()) {
			jcrSession.logout();
		}
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public List<String> getMonths() {
		return months;
	}
   
}
