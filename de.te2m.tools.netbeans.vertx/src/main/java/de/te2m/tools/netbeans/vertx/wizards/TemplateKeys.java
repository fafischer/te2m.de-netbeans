/*
* TemplateKeys.java
*   
* Copyright 2009 - 2016 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
*/
package de.te2m.tools.netbeans.vertx.wizards;

/**
 * The Interface TemplateKeys.
 * Contains constants for accessing certain values in options, wizards and Freemarker templates.
 *
 * @author ffischer
 */
public interface TemplateKeys {

    /**
     * The Constant DN_MVN_ARTIFACT_ID.
     * Used for accessing the configured Maven artifact Id info in the code generation properties
     */
    public static final String DN_MVN_ARTIFACT_ID="artifactID";
    
    /**
     * The Constant DN_MVN_GROUP_ID.
     * Used for accessing the configured Maven group ID info in the code generation properties
     */
    public static final String DN_MVN_GROUP_ID="groupID";
    
    /**
     * The Constant DN_VERTX_VERSION.
     */
    public static final String DN_VERTX_VERSION="Version";

    /**
     * The Constant GEN_CFG_CREATE_DOCKER.
     * Used for a flag for controlling the generation of a Dockerfile
     */
    public static final String GEN_CFG_CREATE_DOCKER="createDocker";

    /**
     * The Constant GEN_CFG_CREATE_FAT_JAR.
     * Used for a flag for controlling the generation of a fat jar
     */
    public static final String GEN_CFG_CREATE_FAT_JAR="createFatJar";
    
    /**
     * The Constant GEN_CFG_CREATE_VERTICLE_TEST.
     * Used for a flag for controlling the generation of a test case for a verticle
     */
    public static final String GEN_CFG_CREATE_VERTICLE_TEST="cfgCreateVerticleTest";

    /**
     * The Constant DN_DOCKER_IMAGE_NAME.
     * Used for accessing the docker image name in the code generation properties
     */
    public static final String DN_DOCKER_IMAGE_NAME="DockerImageName";
    
    /**
     * The Constant DN_POM_INFO.
     * Used for accessing the POM info in the code generation properties
     */
    public static final String DN_POM_INFO="pomInfo";
    
    /**
     * The Constant PROPERTY_COMPANY.
     */
    public static final String PROPERTY_COMPANY="DefaultCompanyName";

    /**
     * The Constant PROPERTY_COMPANY_URL.
     */
    public static final String PROPERTY_COMPANY_URL="DefaultCompanyURL";

    /**
     * The property creation date.
     */
    public static final String DN_CREATION_DATE = "CreationDate";

    /**
     * The Constant PROPERTY_DATE_FORMAT.
     */
    public static final String PROPERTY_DATE_FORMAT="DefaultDateFormat";

    /**
     * The property description.
     */
    public static final String DN_DESCRIPTION = "TargetDescription";

    /**
     * The Constant DN_PROPERTY_CLASS_DESCRIPTION.
     */
    public static final String DN_PROPERTY_CLASS_DESCRIPTION = "TargetClassDescription";
    
    /**
     * The property folder.
     */
    public static final String PROPERTY_FOLDER = "TargetFolder";
    
    
    /**
     * The Constant PROPERTY_INCLUDE_COMPANY.
     */
    public static final String PROPERTY_INCLUDE_COMPANY="includeCompanyInfo";

    /**
     * The Constant PROPERTY_INCLUDE_USER.
     */
    public static final String PROPERTY_INCLUDE_USER="includeUserInfo";

    /**
     * The property name.
     */
    public static final String PROPERTY_NAME = "TargetName";

    /**
     * The Constant DN_PROPERTY_CLASS_NAME.
     */
    public static final String DN_PROPERTY_CLASS_NAME = "TargetClassName";
    
    /**
     * The property name space.
     */
    public static final String PROPERTY_NAME_SPACE = "TargetNameSpace";
    
    /**
     * The property package.
     */
    public static final String DN_PROPERTY_PACKAGE = "TargetPackage";
    
    /**
     * The property user.
     */
    public static final String PROPERTY_USER = "CurrentUser";
    
    /**
     * The Constant PROPERTY_USER_DISPLAY_NAME.
     */
    public static final String PROPERTY_USER_DISPLAY_NAME="CurrentUserDisplayName";
    
    /**
     * The Constant PROPERTY_USER_EMAIL.
     */
    public static final String PROPERTY_USER_EMAIL="CurrentUserEmail";
    
    /**
     * The Constant PROPERTY_VERTX_VERSION.
     */
    public static final String PROPERTY_VERTX_VERSION ="vertxDefaultVersion";
    
    /**
     * The Constant VERTX_DOCKER_DEFAULT_IMAGE_NAME.
     * Used for storing the default docker image name  
     */
    public static final String VERTX_DOCKER_DEFAULT_IMAGE_NAME="Vertx_Default_DockerImage_Name";
    
    /**
     * The Constant VERTX_GENERATE_DOCKER_DEFAULT.
     * Used for storing the default if a dockerfile should be created or not 
     */
    public static final String VERTX_GENERATE_DOCKER_DEFAULT="Vertx_Generate_Dockerfile_Default";
    
    /**
     * The Constant VERTX_USE_FAT_JAR_DEFAULT.
     */
    public static final String VERTX_USE_FAT_JAR_DEFAULT="Vertx_Use_Fat_Jar_Default";
     /**
     * The Constant VERTX_VERSION.
     */
    public static final String VERTX_VERSION="Vertx_Version";
    
}
