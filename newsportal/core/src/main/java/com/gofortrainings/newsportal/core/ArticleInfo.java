package com.gofortrainings.newsportal.core;

import java.util.Locale;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

public class ArticleInfo extends WCMUsePojo {
    private String pageName;
    private Resource content;
    private String articleInfoContent;
    private String articleInfoAuthor;
    private String pageTitleName;
    private int number;
    private Page parentName;
    private Locale pageLang;
    private Resource home;
    private Page payments;
	@Override
	public void activate() throws Exception {
	    
		final Logger log = LoggerFactory.getLogger(ArticleInfo.class);
		Page currentpage=getCurrentPage();
		pageName=currentpage.getTitle();
		content=currentpage.getContentResource();
		pageTitleName=currentpage.getPageTitle();
		ValueMap properties=getProperties();
		articleInfoContent=properties.get("articleContent", String.class);
		articleInfoAuthor=properties.get("articleAuthor", String.class);
		number =currentpage.getDepth();
		parentName=currentpage.getParent();
		pageLang = currentpage.getLanguage();
		PageManager pageManager = getPageManager();
		  pageManager.create("/content/gofortrainings/en_in/index/payments", "registration", "homepage","home"); 
		try {
			 
			 log.info("Page Title : {}; pageTitleName : {}; parentName : {}; pageDesc :{};",currentpage.getTitle(),currentpage.getPageTitle(),currentpage.getParent(),currentpage.getDescription());
			 log.info("Content Resource:{}; absParent :{}; templateName :{}; ",currentpage.getContentResource(),currentpage.getAbsoluteParent(1),currentpage.getTemplate());
			 log.info("articleContent : {}; articleAuthor : {};",articleInfoContent,articleInfoAuthor);
		     log.info("pageLanguage :{} ; pageAtpath :{}; createPage :{};",currentpage.getLanguage(),pageManager.getPage("/content/gofortrainings/en_in/index"), pageManager.create("/content/gofortrainings/en_in/index/payments", "registration", "homepage","home"));
		     log.info("page revision :{};",pageManager.createRevision(currentpage));
		     log.info("page copy :{};",pageManager.copy(payments, "/content/gofortrainings/en_in/index/my-profile", "payments", true, true));
		} catch (Exception e) {
			log.error("Error while executing product info data {}",e.getMessage()); 
		 }	
		
		
	}
	public String getPageTitleName() {
		return pageTitleName;
	}
	public String getPageName() {
		return pageName;
	}
	public Resource getContent() {
		return content;
	}
	public int getNumber() {
		return number;
	}
	public Page getParentName() {
		return parentName;
	}
	public Locale getPageLang() {
		return pageLang;
	}
   
}
