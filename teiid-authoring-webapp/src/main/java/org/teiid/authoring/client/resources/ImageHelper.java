/*
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.teiid.authoring.client.resources;

import org.teiid.authoring.share.Constants;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

/**
 * Contains methods for working with images
 */
public class ImageHelper {

	// ============================================
	// Static Variables

	private static ImageHelper instance = new ImageHelper();

	// ============================================
	// Static Methods
	/**
	 * Get the singleton instance
	 *
	 * @return instance
	 */
	public static ImageHelper getInstance() {
		return instance;
	}

	/*
	 * Create a VdbHelper
	 */
	private ImageHelper() {
	}

	/**
	 * Get the image resource for the provided datasource type
	 * @param dsType the dataSource type
	 * @return the image resource
	 */
    public ImageResource getDataSourceImageForType(String dsType) {
    	ImageResource img = null;
    	if(dsType.equals(Constants.DS_TYPE_FILE)) {
        	img = AppResource.INSTANCE.images().dsType_file_Image();
    	} else if(dsType.equals(Constants.DS_TYPE_GOOGLE)) {
        	img = AppResource.INSTANCE.images().dsType_google_Image();
    	} else if(dsType.equals(Constants.DS_TYPE_H2)) {
        	img = AppResource.INSTANCE.images().dsType_h2_Image();
    	} else if(dsType.equals(Constants.DS_TYPE_INFINISPAN)) {
        	img = AppResource.INSTANCE.images().dsType_infinispan_Image();
    	} else if(dsType.equals(Constants.DS_TYPE_LDAP)) {
        	img = AppResource.INSTANCE.images().dsType_ldap_Image();
    	} else if(dsType.equals(Constants.DS_TYPE_MODESHAPE)) {
        	img = AppResource.INSTANCE.images().dsType_modeshape_Image();
    	} else if(dsType.equals(Constants.DS_TYPE_MONGODB)) {
        	img = AppResource.INSTANCE.images().dsType_mongodb_Image();
    	} else if(dsType.equals(Constants.DS_TYPE_SALESFORCE)) {
        	img = AppResource.INSTANCE.images().dsType_salesforce_Image();
    	} else if(dsType.equals(Constants.DS_TYPE_TEIID)) {
        	img = AppResource.INSTANCE.images().dsType_teiid_Image();
    	} else if(dsType.equals(Constants.DS_TYPE_TEIID_LOCAL)) {
        	img = AppResource.INSTANCE.images().dsType_teiid_Image();
    	} else if(dsType.equals(Constants.DS_TYPE_WEBSERVICE)) {
        	img = AppResource.INSTANCE.images().dsType_webservice_Image();
    	} else {
    		img = AppResource.INSTANCE.images().dsType_blankbox_Image();
    	}
    	return img;
    }
    
	/**
	 * Determine if the type has a known image 
	 * @param dsType the dataSource type
	 * @return 'true' if a specific image is found, 'false' if not.
	 */
    public boolean hasKnownImage(String dsType) {
    	boolean hasImage = false;
    	if(dsType.equals(Constants.DS_TYPE_FILE) ||
    	   dsType.equals(Constants.DS_TYPE_GOOGLE) ||
    	   dsType.equals(Constants.DS_TYPE_H2) ||
    	   dsType.equals(Constants.DS_TYPE_INFINISPAN) ||
    	   dsType.equals(Constants.DS_TYPE_LDAP) ||
    	   dsType.equals(Constants.DS_TYPE_MODESHAPE) ||
    	   dsType.equals(Constants.DS_TYPE_MONGODB) ||
    	   dsType.equals(Constants.DS_TYPE_SALESFORCE) ||
    	   dsType.equals(Constants.DS_TYPE_TEIID) ||
    	   dsType.equals(Constants.DS_TYPE_TEIID_LOCAL) ||
    	   dsType.equals(Constants.DS_TYPE_WEBSERVICE)) {
    		hasImage = true;
    	} else {
    		hasImage = false;
    	}
    	return hasImage;
    }
    
	/**
	 * Get the image html string for the provided datasource type
	 * @param dsType the dataSource type
	 * @return the image html
	 */
    public String getDataSourceForTypeImageHtml(String dsType) {
    	String imageHtml = null;

    	if(dsType.equals(Constants.DS_TYPE_FILE)) {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_file_Image()).getHTML();
    	} else if(dsType.equals(Constants.DS_TYPE_GOOGLE)) {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_google_Image()).getHTML();
    	} else if(dsType.equals(Constants.DS_TYPE_H2)) {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_h2_Image()).getHTML();
    	} else if(dsType.equals(Constants.DS_TYPE_INFINISPAN)) {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_infinispan_Image()).getHTML();
    	} else if(dsType.equals(Constants.DS_TYPE_LDAP)) {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_ldap_Image()).getHTML();
    	} else if(dsType.equals(Constants.DS_TYPE_MODESHAPE)) {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_modeshape_Image()).getHTML();
    	} else if(dsType.equals(Constants.DS_TYPE_MONGODB)) {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_mongodb_Image()).getHTML();
    	} else if(dsType.equals(Constants.DS_TYPE_SALESFORCE)) {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_salesforce_Image()).getHTML();
    	} else if(dsType.equals(Constants.DS_TYPE_TEIID)) {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_teiid_Image()).getHTML();
    	} else if(dsType.equals(Constants.DS_TYPE_TEIID_LOCAL)) {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_teiid_Image()).getHTML();
    	} else if(dsType.equals(Constants.DS_TYPE_WEBSERVICE)) {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_webservice_Image()).getHTML();
    	} else {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_blankbox_Image()).getHTML();
    	}
    	
    	return imageHtml;
    }
    
	/**
	 * Get the image html string for the provided datasource type
	 * @param dsType the dataSource type
	 * @return the image html
	 */
    public String getDataSourceForTypeSmallImageHtml(String dsType) {
    	String imageHtml = null;

    	if(dsType.equals(Constants.DS_TYPE_FILE)) {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_file_small_Image()).getHTML();
    	} else if(dsType.equals(Constants.DS_TYPE_GOOGLE)) {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_google_small_Image()).getHTML();
    	} else if(dsType.equals(Constants.DS_TYPE_H2)) {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_h2_small_Image()).getHTML();
    	} else if(dsType.equals(Constants.DS_TYPE_INFINISPAN)) {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_infinispan_small_Image()).getHTML();
    	} else if(dsType.equals(Constants.DS_TYPE_LDAP)) {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_ldap_small_Image()).getHTML();
    	} else if(dsType.equals(Constants.DS_TYPE_MODESHAPE)) {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_modeshape_small_Image()).getHTML();
    	} else if(dsType.equals(Constants.DS_TYPE_MONGODB)) {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_mongodb_small_Image()).getHTML();
    	} else if(dsType.equals(Constants.DS_TYPE_SALESFORCE)) {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_salesforce_small_Image()).getHTML();
    	} else if(dsType.equals(Constants.DS_TYPE_TEIID)) {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_teiid_small_Image()).getHTML();
    	} else if(dsType.equals(Constants.DS_TYPE_TEIID_LOCAL)) {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_teiid_small_Image()).getHTML();
    	} else if(dsType.equals(Constants.DS_TYPE_WEBSERVICE)) {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_webservice_small_Image()).getHTML();
    	} else {
    		imageHtml = AbstractImagePrototype.create(AppResource.INSTANCE.images().dsType_blankbox_small_Image()).getHTML();
    	}
    	
    	return imageHtml;
    }
    
}

