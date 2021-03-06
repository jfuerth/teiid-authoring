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
package org.teiid.authoring.client.widgets;

import java.util.List;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.teiid.authoring.client.dialogs.UiEvent;
import org.teiid.authoring.client.dialogs.UiEventType;
import org.teiid.authoring.share.beans.DataSourcePageRow;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.view.client.SelectionModel;

/**
 * Composite for display of DataSource list and controls
 */
@Templated("./DataSourceListPanel.html")
public class DataSourceListPanel extends Composite {

    @Inject @DataField("btn-dslist-panel-add")
    protected Button addButton;
    @Inject @DataField("btn-dslist-panel-delete")
    protected Button deleteButton;
    @Inject @DataField("listwidget-dslist-panel")
    protected DataSourceListWidget listWidget;
    
    @Inject Event<UiEvent> buttonEvent;
    
    public DataSourceListPanel() {
    }
    
    /**
     * Event handler that fires when the user clicks the add button.
     * @param event
     */
    @EventHandler("btn-dslist-panel-add")
    public void onAddButtonClick(ClickEvent event) {
    	buttonEvent.fire(new UiEvent(UiEventType.DATA_SOURCE_ADD));
    }

    /**
     * Event handler that fires when the user clicks the delete button.
     * @param event
     */
    @EventHandler("btn-dslist-panel-delete")
    public void onDeleteButtonClick(ClickEvent event) {
    	buttonEvent.fire(new UiEvent(UiEventType.DATA_SOURCE_DELETE));
    }
 
    public void setData(List<DataSourcePageRow> rows) {
    	listWidget.setData(rows);
    }
    
    public List<DataSourcePageRow> getData( ) {
    	return listWidget.getData();
    }
    
    public List<String> getDataSourceNames() {
    	return listWidget.getDataSourceNames();
    }
    
    public void setSelection(String dsName) {
    	listWidget.setSelection(dsName);
    }
    
    public void setSelectionModel( final SelectionModel<DataSourcePageRow> selectionModel ) {
    	listWidget.setSelectionModel( selectionModel );
    }

}
