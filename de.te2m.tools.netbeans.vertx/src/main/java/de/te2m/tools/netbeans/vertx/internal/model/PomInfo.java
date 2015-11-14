package de.te2m.tools.netbeans.vertx.internal.model;

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
    
    private String version;
    private String description;
    
    private String userID;
    
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
     * Gets the artifact id.
     *
     * @return the artifact id
     */
    public String getArtifactID() {
        return artifactID!=null?artifactID:"my.artifact.id";
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
     * Gets the group id.
     *
     * @return the group id
     */
    public String getGroupID() {
        return  groupID!=null?groupID:"tdl.domain.group";
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
     * Gets the url.
     *
     * @return the url
     */
    public String getUrl() {
        return url!=null?url:"";
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
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name!=null?name:"My Project Name";
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description!=null?description:"";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserID() {
        return userID!=null?userID:"";
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserDisplayName() {
        return userDisplayName!=null?userDisplayName:getUserID();
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public String getVersion() {
        return version!=null?version:"1.0-SNAPSHOT";
    }

    public void setVersion(String version) {
        this.version = version;
    }
    
    
}
