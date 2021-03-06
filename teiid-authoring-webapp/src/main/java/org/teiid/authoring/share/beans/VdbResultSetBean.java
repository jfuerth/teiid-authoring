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
package org.teiid.authoring.share.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * Models the set of VDB summary objects returned by an vdb retrieval
 *
 * @author mdrillin@redhat.com
 */
@Portable
public class VdbResultSetBean implements Serializable {

    private static final long serialVersionUID = VdbResultSetBean.class.hashCode();

    private List<VdbSummaryBean> vdbs;
    private long totalResults;
    private int itemsPerPage;
    private int startIndex;
    private Collection<String> allVdbNames;

    /**
     * Constructor.
     */
    public VdbResultSetBean() {
    }

    /**
     * @return the Data sources
     */
    public List<VdbSummaryBean> getVdbs() {
        return vdbs;
    }

    /**
     * @param datasources the datasources to set
     */
    public void setVdbs(List<VdbSummaryBean> vdbs) {
        this.vdbs = vdbs;
    }

    /**
     * @return the totalResults
     */
    public long getTotalResults() {
        return totalResults;
    }

    /**
     * @param totalResults the totalResults to set
     */
    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * @return the itemsPerPage
     */
    public int getItemsPerPage() {
        return itemsPerPage;
    }

    /**
     * @param itemsPerPage the itemsPerPage to set
     */
    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    /**
     * @return the startIndex
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * @param startIndex the startIndex to set
     */
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }
    
    /**
     * @return the collection of all VDB names
     */
    public Collection<String> getAllVdbNames() {
        return allVdbNames;
    }

    /**
     * @param allVdbName the collection of all vdb names
     */
    public void setAllVdbNames(Collection<String> allVdbNames) {
        this.allVdbNames = allVdbNames;
    }

}
