package com.gofortrainings.newsportal.core;

import java.util.Iterator;

import org.apache.geronimo.transaction.manager.GeronimoTransactionManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

public class ProductInfo extends WCMUsePojo  {
  
	public static final Logger log = LoggerFactory.getLogger(ProductInfo.class);
	
	
	
	private String productTitle;
    private String productPagePath;
    private String productDesc;
    private String resourceName;
    private String resourcePath;
	private Resource parentResource;
	private String resourceTypeName;
    private boolean b;
    private ResourceResolver res;
    private ResourceMetadata metadata;
    private String supertypeName;
    private ValueMap valuemap;
    private Iterable<Resource>childName;
    private Iterator<Resource>childList;

	@Override
	public void activate() throws Exception {
		ValueMap properties=getProperties();
		productTitle=properties.get("product Title",String.class);
		productDesc=properties.get("productDescription",String.class);
		productPagePath=properties.get("productPath",String.class);
		Resource resource=getResource();
		 resourceName=resource.getName();
		 resourcePath=resource.getPath();
		 Resource parentResource=resource.getParent();
		 resourceTypeName=resource.getResourceType();
		b= resource.hasChildren();
		ResourceResolver res=resource.getResourceResolver();
		 metadata=resource.getResourceMetadata();
		 supertypeName=resource.getResourceSuperType();
		 valuemap=resource.getValueMap();
		 childName=resource.getChildren();
		 childList=resource.listChildren();
		 
		 Page currentPage = getCurrentPage();
		 PageManager pagemanager=getPageManager();
		 try {
			 
			 log.info("Page Title {}",currentPage.getTitle());
			 log.info("product title : {} product description : {}",productTitle,productDesc);
		 } catch (Exception e) {
			log.error("Error while executing product info data {}",e.getMessage());
		}	 
		 
	}
	
	public String getResourceName() {
		return resourceName;
	}



	public String getResourcePath() {
		return resourcePath;
	}


	public Resource getParentResource() {
		return parentResource;
	}


	public String getResourceTypeName() {
		return resourceTypeName;
	}


	public boolean isB() {
		return b;
	}

	public ResourceResolver getRes() {
		return res;
	}



	public ResourceMetadata getMetadata() {
		return metadata;
	}



	



	public String getSupertypeName() {
		return supertypeName;
	}



	public ValueMap getValuemap() {
		return valuemap;
	}



	public Iterable<Resource> getChildName() {
		return childName;
	}



	public Iterator<Resource> getChildList() {
		return childList;
	}



	public String getProductTitle() {
		return productTitle;
	}

	public String getProductPagePath() {
		return productPagePath;
	}
	public String getProductDesc() {
		return productDesc;
	}
}
