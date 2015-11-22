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
import de.te2m.tools.netbeans.vertx.options.MavenPanel;
import java.io.IOException;
import static java.lang.System.getProperty;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.openide.WizardDescriptor;
import org.openide.filesystems.FileObject;
import static org.openide.util.Exceptions.printStackTrace;
import static org.openide.util.NbPreferences.forModule;

/**
 * The Class AbstractTe2mWizard.
 *
 * @author ffischer
 */
public class AbstractTe2mWizard implements TemplateKeys{

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

        for (FileObject subDir : subDirs) {
            if (subDir.isFolder() && name.equals(subDir.getName())) {
                return subDir;
            }
        }

        if (createIfMissing) {
            try {
                return baseDir.createFolder(name);
            } catch (IOException ex) {
                printStackTrace(ex);
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
        SimpleDateFormat sdf = new SimpleDateFormat(forModule(FormattingPanel.class).get(PROPERTY_DATE_FORMAT, "dd.MM.yyyy"));
        String defaultVersion = forModule(MavenPanel.class).get(MVN_VERSION, "1.0-SNAPSHOT");
        String companyName = forModule(CommonPanel.class).get(PROPERTY_COMPANY, "");
        String username = forModule(CommonPanel.class).get(PROPERTY_USER, getProperty("user.name"));
        String userDisplayName = forModule(CommonPanel.class).get(PROPERTY_USER_DISPLAY_NAME, getProperty("user.name"));
        params.put(PROPERTY_USER, username);
        params.put(PROPERTY_USER_DISPLAY_NAME, userDisplayName);
        params.put(PROPERTY_COMPANY, companyName);
        params.put(PROPERTY_CREATION_DATE, sdf.format(new Date()));
        params.put(MVN_VERSION, defaultVersion);
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
            pInfo.setName((String) wiz.getProperty(PROPERTY_NAME));
            pInfo.setDescription((String) wiz.getProperty(PROPERTY_DESCRIPTION));
            pInfo.setGroupID((String) wiz.getProperty(MVN_GROUP_ID));
            pInfo.setArtifactID((String) wiz.getProperty(MVN_ARTIFACT_ID));
            pInfo.setVersion((String) wiz.getProperty(MVN_VERSION));
        }
        pInfo.setUserID(forModule(CommonPanel.class).get(PROPERTY_USER, getProperty("user.name")));
        pInfo.setUserEmail(forModule(CommonPanel.class).get(PROPERTY_USER_EMAIL, ""));
        pInfo.setUserDisplayName(forModule(CommonPanel.class).get(PROPERTY_USER_DISPLAY_NAME, getProperty("user.name")));
        pInfo.setCompanyName(forModule(CommonPanel.class).get(PROPERTY_COMPANY, ""));
        pInfo.setCompanyURL(forModule(CommonPanel.class).get(PROPERTY_COMPANY_URL, "http://example.com"));
        pInfo.setIncludeCompany(forModule(GeneratePanel.class).getBoolean(PROPERTY_INCLUDE_COMPANY, true));
        pInfo.setIncludeUser(forModule(GeneratePanel.class).getBoolean(PROPERTY_INCLUDE_USER, true));
        params.put(POM_INFO, pInfo);
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
