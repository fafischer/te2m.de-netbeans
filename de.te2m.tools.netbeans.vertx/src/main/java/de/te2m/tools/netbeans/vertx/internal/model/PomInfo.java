/*
* PomInfo.java
*   
* Copyright 2009 - 2016 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
*/
package de.te2m.tools.netbeans.vertx.internal.model;

import static java.util.TimeZone.getDefault;

/**
 * The Class PomInfo.
 *
 * @author ffischer
 */
public class PomInfo {
 
    /**
     * The artifact id.
     */
    private String artifactID;
    
    /**
     * The group id.
     */
    private String groupID;
    
    /**
     * The version.
     */
    private String version;
    
    /**
     * The description.
     */
    private String description;
    
    /**
     * The user id.
     */
    private String userID;
    
    /**
     * The user display name.
     */
    private String userDisplayName;
    
    /**
     * The url.
     */
    private String url;
    
    /**
     * The name.
     */
    private String name;
    
    /**
     * The company name.
     */
    private String companyName;

    /**
     * The company url.
     */
    private String companyURL;
    
    /**
     * The user email.
     */
    private String userEmail;
    
    /**
     * The include user.
     */
    private boolean includeUser;
    
    /**
     * The include company.
     */
    private boolean includeCompany;
    
    

    /**
     * Gets the artifact id.
     *
     * @return the artifact id
     */
    public String getArtifactID() {
        return artifactID!=null?artifactID:"my.artifact.id";
    }

    /**
     * Gets the company name.
     *
     * @return the company name
     */
    public String getCompanyName()
    {
        return companyName!=null?companyName:"";
    }

    /**
     * Gets the company url.
     *
     * @return the company url
     */
    public String getCompanyURL() {
        return companyURL!=null?companyURL:"http://example.com";
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description!=null?description:"";
    }

    /**
     * Gets the group id.
     *
     * @return the group id
     */
    public String getGroupID() {
        return  groupID!=null?groupID:"tdl.domain.group";
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name!=null?name:"My Project Name";
    }

    /**
     * Gets the time zone id.
     *
     * @return the time zone id
     */
    public String getTimeZoneID()
    {
        return getDefault().getID();
    }

    /**
     * Gets the url.
     *
     * @return the url
     */
    public String getUrl() {
        return url!=null?url:"";
    }

    /**
     * Gets the user display name.
     *
     * @return the user display name
     */
    public String getUserDisplayName() {
        return userDisplayName!=null?userDisplayName:getUserID();
    }

    /**
     * Gets the user email.
     *
     * @return the user email
     */
    public String getUserEmail() {
        return userEmail!=null?userEmail:"";
    }

    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public String getUserID() {
        return userID!=null?userID:"";
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    public String getVersion() {
        return version!=null?version:"1.0-SNAPSHOT";
    }

    /**
     * Checks if is include company.
     *
     * @return true, if is include company
     */
    public boolean isIncludeCompany() {
        return includeCompany;
    }

    /**
     * Checks if is include user.
     *
     * @return true, if is include user
     */
    public boolean isIncludeUser() {
        return includeUser;
    }

    /**
     * Sets the artifact id.
     *
     * @param artifactID the new artifact id
     */
    public void setArtifactID(String artifactID) {
        this.artifactID = artifactID;
    }

    /**
     * Sets the company name.
     *
     * @param companyName the new company name
     */
    public void setCompanyName(String companyName) {
        this.companyName=companyName;
    }

    /**
     * Sets the company url.
     *
     * @param companyURL the new company url
     */
    public void setCompanyURL(String companyURL) {
        this.companyURL = companyURL;
    }
    
    
    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the group id.
     *
     * @param groupID the new group id
     */
    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    /**
     * Sets the include company.
     *
     * @param includeCompany the new include company
     */
    public void setIncludeCompany(boolean includeCompany) {
        this.includeCompany = includeCompany;
    }

    /**
     * Sets the include user.
     *
     * @param includeUser the new include user
     */
    public void setIncludeUser(boolean includeUser) {
        this.includeUser = includeUser;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the url.
     *
     * @param url the new url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Sets the user display name.
     *
     * @param userDisplayName the new user display name
     */
    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    /**
     * Sets the user email.
     *
     * @param userEmail the new user email
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * Sets the user id.
     *
     * @param userID the new user id
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Sets the version.
     *
     * @param version the new version
     */
    public void setVersion(String version) {
        this.version = version;
    }
    
    
}
