/*
* Templates.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.jsfutils project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.netbeans.jsfutils;

/**
 * The Interface Templates.
 *
 * @author ffischer
 */
public interface Templates {

    /**
     * The Constant PF_TAB_VIEW.
     */
    public static final String PF_TAB_VIEW = "<p:tabView> <p:tab title=\"tabTitle\"> \n</p:tab> \n<!-- TODO: Add additional tabs as required -->\n</p:tabView>";
    
    /**
     * The Constant PF_TAB.
     */
    public static final String PF_TAB = "<p:tab title=\"tabTitle\"> \n\n</p:tab>";
    
    /**
     * The Constant PF_DATA_TABLE.
     */
    public static final String PF_DATA_TABLE = "<p:dataTable value=\"\" var=\" \"> \n \n</p:dataTable>";
    
    /**
     * The Constant PF_DATA_TABLE_COLUMN.
     */
    public static final String PF_DATA_TABLE_COLUMN = "<p:column> <f:facet name=\"header\"> <h:outputText value=\"columnName\"/> </f:facet> <h:outputText value=\"value\"/> </p:column>";
    
    /**
     * The Constant PF_TEXT_AREA.
     */
    public static final String PF_TEXT_AREA = "<h:outputLabel value=\"Description\" for=\"ID\" /> <p:inputTextarea id=\"ID\" value=\"\" title=\"MyTitle\"/>";
    
    /**
     * The Constant PF_ACCORDEON_PANEL.
     */
    public static final String PF_ACCORDEON_PANEL = "<p:accordionPanel> \n<p:tab title=\"tabTitle\"> \n\n</p:tab> \n<!-- TODO: Add additional tabs as required --> \n</p:accordionPanel>";
}
