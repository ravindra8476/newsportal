package com.gofortrainings.newsportal.core.models;

import javax.annotation.PostConstruct;
//import javax.inject.Inject;
//import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.Source;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.designer.Style;
import com.gofortainings.newsportal.core.services.GenericInfo;

@Model(adaptables= {Resource.class,SlingHttpServletRequest.class})
public class ArticleinfoModel {
	@ValueMapValue @Default(values="Articlie Info Default Title")
	   private String articleTitle;
	   
	@ValueMapValue(name="sling:resourceType")
	   private String slingResourceType;
	   
	   @ValueMapValue
	   private String articleContent;
	   @OSGiService
	   GenericInfo genericInfo;
	   //@Inject @Source("sling-object") //To Replace this directly we use SlingObject
	   @SlingObject
	   Resource resource;
	   @ChildResource(name="childarticle")
	   private Resource childResource;
	   private String  childArticleTitleName;
	   
	   @ScriptVariable(name="currentPage")
	    Page page;
	   @ScriptVariable
	   Style currentStyle;
	   
	   
	  // @Self
	  // private ProductinfoModel productinfomodel;
	   @ResourcePath(path="/content/gofortrainings/en_in/index/payments/deposit/jcr:content/content-par/deposit")
	   Resource data;
	  // @RequestAttribute
	  // private String name;
	   private static final Logger log=LoggerFactory.getLogger(ArticleinfoModel.class);
	   @PostConstruct
	   public void init() {
		   
		   String rootPath = currentStyle.get("/content/gofortrainings/en_in/index",String.class);
		   
		   log.info("Inside Post Construct method");
		   log.info("Generic service:: {}",genericInfo.getInfo());
		   childArticleTitleName = childResource.getValueMap().get("childArticleTitle", String.class);
		  log.info("Resource Name :: {}",resource.getParent());
		   //log1.info("About Script Variable Log1");
		  log.info("ResourcePathname ::{}",data);
		 // log.info("currentpage name ::{}",currentpage.getLanguage());
		  log.info("pagepath ::{}",page.getPath());
		  log.info("currentstyle pathname ::{}",rootPath);
		 
		//log.info("Resource Name:: {}",request.getResource());
	   }
	public String getArticleTitle() {
		return articleTitle;
	}
	public String getSlingResourceType() {
		return slingResourceType;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public String getChildArticleTitle() {
		return childArticleTitleName;
	}
	public Resource getResource() {
		return resource;
	}
	//public String getName() {
		//return name;
	//}
	public String getPagePath() {
        return  page.getPath();
    }
	
	
}
