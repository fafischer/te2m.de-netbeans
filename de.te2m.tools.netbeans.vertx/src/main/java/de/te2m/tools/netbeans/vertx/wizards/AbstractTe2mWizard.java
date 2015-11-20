/*
* AbstractTe2mWizard.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
 */
package de.te2m.tools.netbeans.vertx.wizards;

import de.te2m.tools.netbeans.vertx.internal.model.PomInfo;
import de.te2m.tools.netbeans.vertx.options.CommonPanel;
import de.te2m.tools.netbeans.vertx.options.FormattingPanel;
import de.te2m.tools.netbeans.vertx.options.GeneratePanel;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.openide.WizardDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.NbPreferences;

/**
 * The Class AbstractTe2mWizard.
 *
 * @author ffischer
 */
public class AbstractTe2mWizard {

    /**
     * Gets the sub dir.
     *
     * @param baseDir the base dir
     * @param name the name
     * @param createIfMissing the create if missing
     * @return the sub dir
     */
    protected FileObject getSubDir(FileObject baseDir, String name, boolean createIfMissing) {

        FileObject[] subDirs = baseDir.getChildren();

        for (int i = 0; i < subDirs.length; i++) {
            FileObject subDir = subDirs[i];

            if (subDir.isFolder() && name.equals(subDir.getName())) {
                return subDir;
            }

        }

        if (createIfMissing) {
            try {
                return baseDir.createFolder(name);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

        return null;
    }

    /**
     * Initialize common properties.
     *
     * @param params the params
     */
    protected void initializeCommonProperties(Map<String, Object> params) {
        SimpleDateFormat sdf = new SimpleDateFormat(NbPreferences.forModule(FormattingPanel.class).get(TemplateKeys.PROPERTY_DATE_FORMAT, "dd.MM.yyyy"));
        String defaultVersion = NbPreferences.forModule(CommonPanel.class).get(TemplateKeys.PROPERTY_VERSION, "1.0-SNAPSHOT");
        String companyName = NbPreferences.forModule(CommonPanel.class).get(TemplateKeys.PROPERTY_COMPANY, "");
        String username = NbPreferences.forModule(CommonPanel.class).get(TemplateKeys.PROPERTY_USER, System.getProperty("user.name"));
        String userDisplayName = NbPreferences.forModule(CommonPanel.class).get(TemplateKeys.PROPERTY_USER_DISPLAY_NAME, System.getProperty("user.name"));
        params.put(TemplateKeys.PROPERTY_USER, username);
        params.put(TemplateKeys.PROPERTY_USER_DISPLAY_NAME, userDisplayName);
        params.put(TemplateKeys.PROPERTY_COMPANY, companyName);
        params.put(TemplateKeys.PROPERTY_CREATION_DATE, sdf.format(new Date()));
        params.put(TemplateKeys.PROPERTY_VERSION, defaultVersion);
    }

    /**
     * Initialize the POM Info container.
     * This container will be used in FreeMarker templates.
     * It will be initialized based on the several Netbeans Options and the data provided by the current wizard.
     * @param params the params
     * @param wiz the wizard descriptor
     */
    protected void initializePomInfo(Map<String, Object> params, WizardDescriptor wiz) {
        PomInfo pInfo = new PomInfo();
        if (null != wiz) {
            pInfo.setName((String) wiz.getProperty(TemplateKeys.PROPERTY_NAME));
            pInfo.setDescription((String) wiz.getProperty(TemplateKeys.PROPERTY_DESCRIPTION));
            pInfo.setGroupID((String) wiz.getProperty(TemplateKeys.MVN_GROUP_ID));
            pInfo.setArtifactID((String) wiz.getProperty(TemplateKeys.MVN_ARTIFACT_ID));
            pInfo.setVersion((String) wiz.getProperty(TemplateKeys.MVN_VERSION));
        }
        pInfo.setUserID(NbPreferences.forModule(CommonPanel.class).get(TemplateKeys.PROPERTY_USER, System.getProperty("user.name")));
        pInfo.setUserDisplayName(NbPreferences.forModule(CommonPanel.class).get(TemplateKeys.PROPERTY_USER_DISPLAY_NAME, System.getProperty("user.name")));
        pInfo.setCompanyName(NbPreferences.forModule(CommonPanel.class).get(TemplateKeys.PROPERTY_COMPANY, ""));
        pInfo.setCompanyURL(NbPreferences.forModule(CommonPanel.class).get(TemplateKeys.PROPERTY_COMPANY_URL, "http://example.com"));
        pInfo.setIncludeCompany(NbPreferences.forModule(GeneratePanel.class).getBoolean(TemplateKeys.PROPERTY_INCLUDE_COMPANY, true));
        pInfo.setIncludeUser(NbPreferences.forModule(GeneratePanel.class).getBoolean(TemplateKeys.PROPERTY_INCLUDE_USER, true));
        params.put(TemplateKeys.POM_INFO, pInfo);
    }

    /**
     * Lookup sub dir.
     *
     * @param baseDir the base dir
     * @param name the name
     * @return the file object
     */
    protected FileObject lookupSubDir(FileObject baseDir, String name) {

        if (null != name && name.trim().length() > 0) {
            int pos = name.indexOf("/");
            if (pos != -1) {

                String newBaseName = name.substring(0, pos);

                FileObject newBase = getSubDir(baseDir, newBaseName, true);

                return lookupSubDir(newBase, name.substring(pos + 1));
            } else {
                return getSubDir(baseDir, name, true);
            }
        } else {
            return baseDir;
        }
    }

}
