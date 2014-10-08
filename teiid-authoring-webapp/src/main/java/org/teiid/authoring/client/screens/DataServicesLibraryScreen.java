/*
 * Copyright 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.teiid.authoring.client.screens;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.teiid.authoring.client.messages.ClientMessages;
import org.teiid.authoring.client.services.NotificationService;
import org.teiid.authoring.client.services.QueryRpcService;
import org.teiid.authoring.client.services.VdbRpcService;
import org.teiid.authoring.client.services.rpc.IRpcServiceInvocationHandler;
import org.teiid.authoring.client.widgets.ServiceFlowListWidget;
import org.teiid.authoring.client.widgets.ServiceRow;
import org.teiid.authoring.share.Constants;
import org.teiid.authoring.share.beans.NotificationBean;
import org.teiid.authoring.share.beans.VdbDetailsBean;
import org.teiid.authoring.share.beans.VdbModelBean;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.lifecycle.OnStartup;
import org.uberfire.mvp.PlaceRequest;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * DataServicesLibraryScreen - shows all published Data Services.
 *
 */
@Dependent
@Templated("./DataServicesLibraryScreen.html#page")
@WorkbenchScreen(identifier = "DataServicesLibraryScreen")
public class DataServicesLibraryScreen extends Composite {

    @Inject
    private ClientMessages i18n;
    @Inject
    private NotificationService notificationService;
    
    @Inject
    protected VdbRpcService vdbService;
 
    @Inject
    protected QueryRpcService queryService;
 
    @Inject
    private PlaceManager placeManager;
    
    @Inject ServiceFlowListWidget serviceFlowListWidget;
    
    @Inject @DataField("btn-create-service")
    protected Button createServiceButton;
        
    @Inject @DataField("grid-services")
    protected VerticalPanel servicesPanel;
    
    @Override
    @WorkbenchPartTitle
    public String getTitle() {
      return Constants.BLANK;
    }
    
    @WorkbenchPartView
    public IsWidget getView() {
        return this;
    }
    
    /**
     * Called after construction.
     */
    @PostConstruct
    protected void postConstruct() {
    	ensureVdbDeployed(Constants.SERVICES_VDB);

    	servicesPanel.add(serviceFlowListWidget);
    }
    
    @OnStartup
    public void onStartup( final PlaceRequest place ) {
    	// Process delete and clone requests from serviceWidget
    	String deleteName = place.getParameter(Constants.DELETE_SERVICE_KEY, "NONE");
    	String cloneName = place.getParameter(Constants.CLONE_SERVICE_KEY, "NONE");
    	if(!deleteName.equals("NONE")) {
    		doRemoveService(deleteName);
    	} else if(!cloneName.equals("NONE")) {
    		doCloneService(cloneName);
    	} else {
    		doGetServices();
    	}
    }
    
    private void populateGrid(List<ServiceRow> serviceList) {
        serviceFlowListWidget.setItems(serviceList);
    }
    
    /**
     * Make sure the Services VDB is deployed.  If not, deploy empty services VDB
     * @param dataSourceName the name of the source
     */
    protected void ensureVdbDeployed(String vdbName) {
    	vdbService.createAndDeployDynamicVdb(vdbName, new IRpcServiceInvocationHandler<Void>() {
            @Override
            public void onReturn(Void data) {
            }
            @Override
            public void onError(Throwable error) {
                notificationService.sendErrorNotification(i18n.format("dslibrary.error-with-vdb-deployment"), error); //$NON-NLS-1$
                //noDataMessage.setVisible(true);
                //searchInProgressMessage.setVisible(false);
            }
        });    	
    }
    
    /**
     * Get the public services for the supplied VDB
     */
    protected void doGetServices( ) {
    	vdbService.getVdbDetails(Constants.SERVICES_VDB, new IRpcServiceInvocationHandler<VdbDetailsBean>() {
    		@Override
    		public void onReturn(VdbDetailsBean vdbDetailsBean) {
    			List<ServiceRow> serviceTableRows = new ArrayList<ServiceRow>();
    			
    			Collection<VdbModelBean> modelList = vdbDetailsBean.getModels();
    			for(VdbModelBean model : modelList) {
    				String modelName = model.getName();
    				String description = model.getDescription();
    				String modelType = model.getType();
    				boolean isVisible = model.isVisible();
    				if(modelType.equals(Constants.VIRTUAL)) {
						ServiceRow srow = new ServiceRow();
						srow.setName(modelName);
						srow.setDescription(description);
						srow.setVisible(isVisible);
						serviceTableRows.add(srow);
    				}
    			}
    			if(serviceTableRows.isEmpty()) {
    				placeManager.goTo("DataServicesEmptyLibraryScreen");

    			} else {
    		     	populateGrid(serviceTableRows);
    			}
    		}
    		@Override
    		public void onError(Throwable error) {
                notificationService.sendErrorNotification(i18n.format("dslibrary.fetch-services-error"), error); //$NON-NLS-1$
    		}
    	});
    }
        	
   protected void doRemoveService(String serviceName) {
        final NotificationBean notificationBean = notificationService.startProgressNotification(
                i18n.format("dslibrary.service-deleting"), //$NON-NLS-1$
                i18n.format("dslibrary.service-deleting-msg", serviceName)); //$NON-NLS-1$
        
    	Map<String,String> modelNameTypeMap = new HashMap<String,String>();
    	modelNameTypeMap.put(serviceName, Constants.VIRTUAL);
    	
    	vdbService.removeModelsAndRedeploy(Constants.SERVICES_VDB, 1, modelNameTypeMap, new IRpcServiceInvocationHandler<VdbDetailsBean>() {
    		@Override
    		public void onReturn(VdbDetailsBean vdbDetailsBean) {
                notificationService.completeProgressNotification(notificationBean.getUuid(),
                        i18n.format("dslibrary.delete-success"), //$NON-NLS-1$
                        i18n.format("dslibrary.delete-success-msg")); //$NON-NLS-1$
                
    			List<ServiceRow> serviceTableRows = new ArrayList<ServiceRow>();
    			
    			Collection<VdbModelBean> modelList = vdbDetailsBean.getModels();
    			for(VdbModelBean model : modelList) {
    				String modelName = model.getName();
    				String description = model.getDescription();
    				String modelType = model.getType();
    				boolean isVisible = model.isVisible();
    				if(modelType.equals(Constants.VIRTUAL)) {
						ServiceRow srow = new ServiceRow();
						srow.setName(modelName);
						srow.setDescription(description);
						srow.setVisible(isVisible);
						serviceTableRows.add(srow);
    				}
    			}
    			if(serviceTableRows.isEmpty()) {
    				placeManager.goTo("DataServicesEmptyLibraryScreen");
    			} else {
    		     	populateGrid(serviceTableRows);
    			}
    		}
    		@Override
    		public void onError(Throwable error) {
    			notificationService.completeProgressNotification(notificationBean.getUuid(),
    					i18n.format("dslibrary.service-delete-error"), //$NON-NLS-1$
    					error);
    		}
    	});
    }
    
    protected void doCloneService(String serviceName) {
        final NotificationBean notificationBean = notificationService.startProgressNotification(
                i18n.format("dslibrary.service-cloning"), //$NON-NLS-1$
                i18n.format("dslibrary.service-cloning-msg", serviceName)); //$NON-NLS-1$
        
    	vdbService.cloneViewModelAndRedeploy(Constants.SERVICES_VDB, 1, serviceName, new IRpcServiceInvocationHandler<VdbDetailsBean>() {
    		@Override
    		public void onReturn(VdbDetailsBean vdbDetailsBean) {
                notificationService.completeProgressNotification(notificationBean.getUuid(),
                        i18n.format("dslibrary.clone-success"), //$NON-NLS-1$
                        i18n.format("dslibrary.clone-success-msg")); //$NON-NLS-1$
                
    			List<ServiceRow> serviceTableRows = new ArrayList<ServiceRow>();
    			
    			Collection<VdbModelBean> modelList = vdbDetailsBean.getModels();
    			for(VdbModelBean model : modelList) {
    				String modelName = model.getName();
    				String description = model.getDescription();
    				String modelType = model.getType();
    				boolean isVisible = model.isVisible();
    				if(modelType.equals(Constants.VIRTUAL)) {
						ServiceRow srow = new ServiceRow();
						srow.setName(modelName);
						srow.setDescription(description);
						srow.setVisible(isVisible);
						serviceTableRows.add(srow);
    				}
    			}
    			if(serviceTableRows.isEmpty()) {
    				placeManager.goTo("DataServicesEmptyLibraryScreen");
    			} else {
    		     	populateGrid(serviceTableRows);
    			}
    		}
    		@Override
    		public void onError(Throwable error) {
    			notificationService.completeProgressNotification(notificationBean.getUuid(),
    					i18n.format("dslibrary.service-clone-error"), //$NON-NLS-1$
    					error);
    		}
    	});
    }
    
    /**
     * Event handler that fires when the user clicks the CreateService button.
     * @param event
     */
    @EventHandler("btn-create-service")
    public void onCreateServiceButtonClick(ClickEvent event) {
    	doCreateService();
    }
    
    /**
     * Create Service - transitions to the Create Services page
     */
    protected void doCreateService() {
    	placeManager.goTo("CreateDataServiceScreen");
    }
    
}
