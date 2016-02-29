/*
* Te2mWizardBase.java
*   
* Copyright 2009 - 2016 Frank Fischer (email: frank@te2m.de)
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
import de.te2m.tools.netbeans.vertx.options.VertxPanel;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.openide.WizardDescriptor;
import org.openide.filesystems.FileObject;
import static org.openide.util.Exceptions.printStackTrace;
import static org.openide.util.NbPreferences.forModule;
import java.util.StringTokenizer;
import org.openide.filesystems.FileUtil;
import static java.lang.System.getProperty;
import java.util.Set;
import org.netbeans.api.project.FileOwnerQuery;
import org.netbeans.api.project.Project;
import static org.openide.loaders.DataFolder.findFolder;
import org.openide.loaders.DataObject;
import static org.openide.loaders.DataObject.find;
import org.openide.nodes.Node;

/**
 * The Class Te2mWizardBase.
 *
 * @author ffischer
 */
public class Te2mWizardBase implements TemplateKeys {

    /**
     * Gets the sub dir.
     *
     * @param baseDir the base dir
     * @param name the name
     * @param createIfMissing the create if missing
     * @return the sub dir
     */
    public static FileObject getSubDir(FileObject baseDir, String name, boolean createIfMissing) {

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
    public static void initializeCommonProperties(Map<String, Object> params) {
        SimpleDateFormat sdf = new SimpleDateFormat(forModule(FormattingPanel.class).get(PROPERTY_DATE_FORMAT, "dd.MM.yyyy"));
        String defaultVersion = forModule(MavenPanel.class).get(DN_VERTX_VERSION, "1.0-SNAPSHOT");
        String companyName = forModule(CommonPanel.class).get(PROPERTY_COMPANY, "");
        String vertxDefaultVersion = forModule(VertxPanel.class).get(VERTX_VERSION, "3.2.1");
        String username = forModule(CommonPanel.class).get(PROPERTY_USER, getProperty("user.name"));
        String userDisplayName = forModule(CommonPanel.class).get(PROPERTY_USER_DISPLAY_NAME, getProperty("user.name"));
        params.put(PROPERTY_USER, username);
        params.put(PROPERTY_USER_DISPLAY_NAME, userDisplayName);
        params.put(PROPERTY_VERTX_VERSION, vertxDefaultVersion);
        params.put(PROPERTY_COMPANY, companyName);
        params.put(DN_CREATION_DATE, sdf.format(new Date()));
        params.put(DN_VERTX_VERSION, defaultVersion);
    }

    /**
     * Initialize the POM Info container. This container will be used in
     * FreeMarker templates. It will be initialized based on the several
     * Netbeans Options and the data provided by the current wizard.
     *
     * @param params the params
     * @param wiz the wizard descriptor
     */
    public static void initializePomInfo(Map<String, Object> params, WizardDescriptor wiz) {
        PomInfo pInfo = new PomInfo();
        if (null != wiz) {
            pInfo.setName((String) wiz.getProperty(PROPERTY_NAME));
            pInfo.setDescription((String) wiz.getProperty(DN_DESCRIPTION));
            pInfo.setGroupID((String) wiz.getProperty(DN_MVN_GROUP_ID));
            pInfo.setArtifactID((String) wiz.getProperty(DN_MVN_ARTIFACT_ID));
            pInfo.setVersion((String) wiz.getProperty(DN_VERTX_VERSION));
        }
        pInfo.setUserID(forModule(CommonPanel.class).get(PROPERTY_USER, getProperty("user.name")));
        pInfo.setUserEmail(forModule(CommonPanel.class).get(PROPERTY_USER_EMAIL, ""));
        pInfo.setUserDisplayName(forModule(CommonPanel.class).get(PROPERTY_USER_DISPLAY_NAME, getProperty("user.name")));
        pInfo.setCompanyName(forModule(CommonPanel.class).get(PROPERTY_COMPANY, ""));
        pInfo.setCompanyURL(forModule(CommonPanel.class).get(PROPERTY_COMPANY_URL, "http://example.com"));
        pInfo.setIncludeCompany(forModule(GeneratePanel.class).getBoolean(PROPERTY_INCLUDE_COMPANY, true));
        pInfo.setIncludeUser(forModule(GeneratePanel.class).getBoolean(PROPERTY_INCLUDE_USER, true));
        params.put(DN_POM_INFO, pInfo);
    }

    /**
     * Lookup sub dir.
     *
     * @param baseDir the base dir
     * @param name the name
     * @return the file object
     */
    public static FileObject lookupSubDir(FileObject baseDir, String name) {

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

    /**
     * Gets the FileObject of the templates base folder.
     *
     * @return the template folder
     */
    public static FileObject getTemplateFolder() {
        FileObject fo = FileUtil.getConfigFile("Templates"); // NOI18N

        if (fo != null && fo.isFolder()) {

            return fo;

        }
        return null;
    }

    /**
     * Gets the file object for an template by its name and folder.
     *
     * @param name the name
     * @param folder the folder
     * @return the template by name and folder
     */
    public static FileObject getTemplateByNameAndFolder(String name, String folder) {
        FileObject fo = FileUtil.getConfigFile("Templates"); // NOI18N

        FileObject tmplFolder = getTemplateFolder(fo, folder);

        if (null != tmplFolder && tmplFolder.isFolder()) {
            return tmplFolder.getFileObject(name);
        } else {
            return null;
        }

    }

    /**
     * Gets the template folder.
     *
     * @param base the base
     * @param folder the folder
     * @return the template folder
     */
    public static FileObject getTemplateFolder(FileObject base, String folder) {
        if (null == base) {
            return null;
        }

        if (!base.isFolder()) {
            return null;
        }

        if (null == folder || folder.trim().length() == 0) {
            return null;
        }

        if (folder.contains("/")) {
            // Folder name contains subfolders

            StringTokenizer st = new StringTokenizer(folder, "/");

            String nextSubfolderName = st.nextToken();

            String remainingFolderName = folder.substring(nextSubfolderName.length() + 1);

            FileObject subfolder = base.getFileObject(nextSubfolderName);

            return getTemplateFolder(subfolder, remainingFolderName);

        } else {

            FileObject fo = base.getFileObject(folder);

            return fo;
        }

    }

    /**
     * Recursively searches the node hierarchy for the project that owns a node.
     *
     * @param node a node to test for a Project in its or its ancestor's lookup.
     * @return the Project that owns the node, or null if not found
     */
    public static Project findProjectThatOwnsNode(Node node) {
        if (node != null) {
            Project project = node.getLookup().lookup(Project.class);
            if (project == null) {
                DataObject dataObject = node.getLookup().lookup(DataObject.class);
                if (dataObject != null) {
                    project = FileOwnerQuery.getOwner(dataObject.getPrimaryFile());
                }
            }
            return (project == null) ? findProjectThatOwnsNode(node.getParentNode()) : project;
        } else {
            return null;
        }
    }

    public static DataObject generateDockerFile(FileObject projectBaseDirFO, Map<String, Object> params, boolean fatJar) throws IOException {
        FileObject fo;
        if (fatJar) {
            fo = getTemplateByNameAndFolder(TemplateIDs.VERTX_DOCKER_FAT_JAR, TemplateIDs.TEMPLATE_GROUP_VERTX);
        } else {
            fo = getTemplateByNameAndFolder(TemplateIDs.VERTX_DOCKER_FILE, TemplateIDs.TEMPLATE_GROUP_VERTX);
        }
        if (null != fo) {
            FileObject dockerDir = lookupSubDir(projectBaseDirFO, "src/main/docker");
            DataObject currentTemplateDO = find(fo);
            DataObject dockerObj = currentTemplateDO.createFromTemplate(findFolder(dockerDir), "Dockerfile", params);
            return dockerObj;
            //System.out.println(">>>>>  "+i+"  >>>>> "+currentTemplate.getNameExt());
        }
        return null;
    }

}
