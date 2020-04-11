package com.gofortrainings.newsportal.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.SightlyWCMMode;
import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.designer.Design;
import com.day.cq.wcm.api.designer.Designer;
import com.day.cq.wcm.api.designer.Style;



public class Navigation extends WCMUsePojo {
	
	Map<String,String> childMap =  new HashMap<>();
private String pathName;
private Design designName;
private boolean inEditMode;
	@Override
	public void activate() throws Exception {
		final Logger log = LoggerFactory.getLogger(ArticleInfo.class);
		PageManager pageManager = getPageManager();
		 Page pageName=pageManager.getPage("/content/FlaxSEO/en_in/index");
		Iterator<Page>	pageList=pageName.listChildren();
		while (pageList.hasNext()) {
			Page childPage = (Page) pageList.next();
			childMap.put(childPage.getPath(), childPage.getTitle());
			
			
		} 
	
		Style style =getCurrentStyle();
	      pathName=style.getPath();
	      designName=style.getDesign();
	      log.info("styleResourceName :{};",style.getDefiningResource("navigation"));
	      SightlyWCMMode wcmmode=getWcmMode();
	       inEditMode=wcmmode.isEdit();

	      log.info("editMode :{};",wcmmode.isEdit());
	      log.info("disabledMode:{};",wcmmode.isDisabled());
	      
	      

	}

	public String getPathName() {
		return pathName;
	}
	

	public Design getDesignName() {
		return designName;
	}

	public Map<String, String> getChildMap() {
		return childMap;
	}

	public boolean isInEditMode() {
		return inEditMode;
	}
	
	

}
