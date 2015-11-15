/*
* VertxWizardIterator.java
*
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules
* (https://github.com/fafischer/te2m.de-netbeans).
*
 */
package de.te2m.tools.netbeans.vertx.wizards.project;

import de.te2m.tools.netbeans.vertx.internal.model.PomInfo;
import de.te2m.tools.netbeans.vertx.wizards.TemplateKeys;
import java.awt.Component;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.zip.ZipInputStream;
import javax.swing.JComponent;
import javax.swing.event.ChangeListener;
import org.netbeans.api.project.ProjectManager;
import org.netbeans.api.templates.TemplateRegistration;
import org.netbeans.spi.project.ui.support.ProjectChooser;
import org.netbeans.spi.project.ui.templates.support.Templates;
import org.openide.WizardDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
import org.openide.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

// TODO define position attribute
/**
 * The Class VertxWizardIterator.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
@TemplateRegistration(folder = "Project/vertx.io", displayName = "#Vertx_displayName", description = "VertxDescription.html", iconBase = "de/te2m/tools/netbeans/vertx/icons/logo16.png", content = "pom.xml.template", scriptEngine = "freemarker")
@Messages("Vertx_displayName=Create a new Vertx.io 3.1 Project")
public class VertxWizardIterator implements WizardDescriptor./*Progress*/InstantiatingIterator {

    /**
     * Creates the iterator.
     *
     * @return the vertx wizard iterator
     */
    public static VertxWizardIterator createIterator() {
        return new VertxWizardIterator();
    }


    /**
     * Write file.
     *
     * @param str the str
     * @param fo the fo
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private static void writeFile(ZipInputStream str, FileObject fo) throws IOException {
        OutputStream out = fo.getOutputStream();
        try {
            FileUtil.copy(str, out);
        } finally {
            out.close();
        }
    }

    /**
     * The index.
     */
    private int index;

    /**
     * The panels.
     */
    private WizardDescriptor.Panel[] panels;

    /**
     * The wiz.
     */
    private WizardDescriptor wiz;

    /**
     * Instantiates a new vertx wizard iterator.
     */
    public VertxWizardIterator() {
    }

    // If nothing unusual changes in the middle of the wizard, simply:
    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Iterator#addChangeListener(javax.swing.event.ChangeListener)
     */
    @Override
    public final void addChangeListener(ChangeListener l) {
    }

    /**
     * Creates the panels.
     *
     * @return the wizard descriptor. panel[]
     */
    private WizardDescriptor.Panel[] createPanels() {
        return new WizardDescriptor.Panel[]{
            new VertxWizardProjectPanel(),new VertxWizardMavenPanel(),new VertxWizardPackagingPanel()};
    }

    /**
     * Creates the steps.
     *
     * @return the string[]
     */
    private String[] createSteps() {
        return new String[]{
            NbBundle.getMessage(VertxWizardIterator.class, "LBL_CreateProjectStep"),
            NbBundle.getMessage(VertxWizardIterator.class, "LBL_CreateMavenStep"),
            NbBundle.getMessage(VertxWizardIterator.class, "LBL_CreatePackagingStep")
        };
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Iterator#current()
     */
    @Override
    public WizardDescriptor.Panel current() {
        return panels[index];
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Iterator#hasNext()
     */
    @Override
    public boolean hasNext() {
        return index < panels.length - 1;
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Iterator#hasPrevious()
     */
    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.InstantiatingIterator#initialize(org.openide.WizardDescriptor)
     */
    @Override
    public void initialize(WizardDescriptor wiz) {
        this.wiz = wiz;
        index = 0;
        panels = createPanels();
        // Make sure list of steps is accurate.
        String[] steps = createSteps();
        for (int i = 0; i < panels.length; i++) {
            Component c = panels[i].getComponent();
            if (steps[i] == null) {
                // Default step name to component name of panel.
                // Mainly useful for getting the name of the target
                // chooser to appear in the list of steps.
                steps[i] = c.getName();
            }
            if (c instanceof JComponent) { // assume Swing components
                JComponent jc = (JComponent) c;
                // Step #.
                // TODO if using org.openide.dialogs >= 7.8, can use WizardDescriptor.PROP_*:
                jc.putClientProperty("WizardPanel_contentSelectedIndex", new Integer(i));
                // Step name (actually the whole list for reference).
                jc.putClientProperty("WizardPanel_contentData", steps);
            }
        }
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.InstantiatingIterator#instantiate()
     */
    @Override
    public Set/*<FileObject>*/ instantiate(/*ProgressHandle handle*/) throws IOException {
        // Replace with lookup to options
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        // Replace with lookup to options
        String username = System.getProperty("user.name");
        Map<String, Object> params = new HashMap<>();
        PomInfo pInfo = new PomInfo();
        pInfo.setName((String) wiz.getProperty(TemplateKeys.PROPERTY_NAME));
        pInfo.setDescription((String) wiz.getProperty(TemplateKeys.PROPERTY_DESCRIPTION));
        pInfo.setGroupID((String) wiz.getProperty(TemplateKeys.MVN_GROUP_ID));
        pInfo.setArtifactID((String) wiz.getProperty(TemplateKeys.MVN_ARTIFACT_ID));
        pInfo.setVersion((String) wiz.getProperty(TemplateKeys.MVN_VERSION));
        pInfo.setUserID(username);
        params.put(TemplateKeys.PROPERTY_CREATION_DATE, sdf.format(new Date()));
        params.put(TemplateKeys.PKG_CREATE_FAT_JAR, wiz.getProperty(TemplateKeys.PKG_CREATE_FAT_JAR));
        params.put(TemplateKeys.POM_INFO, pInfo);
        Set<FileObject> resultSet = new LinkedHashSet<>();
        File dirF = FileUtil.normalizeFile((File) wiz.getProperty("projdir"));
        dirF.mkdirs();

        FileObject template = Templates.getTemplate(wiz);
        DataObject dTemplate = DataObject.find(template);
        FileObject dir = FileUtil.toFileObject(dirF);

        // Always open top dir as a project:
        resultSet.add(dir);

        DataObject dobj = dTemplate.createFromTemplate(DataFolder.findFolder(dir), "pom.xml", params);

        //Obtain a FileObject:
        FileObject createdFile = dobj.getPrimaryFile();

        resultSet.add(createdFile);

        File parent = dirF.getParentFile();
        if (parent != null && parent.exists()) {
            ProjectChooser.setProjectsFolder(parent);
        }

        if (ProjectManager.getDefault().isProject(dir)) {
            resultSet.add(dir);
        }

        return resultSet;
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Iterator#name()
     */
    @Override
    public String name() {
        return MessageFormat.format("{0} of {1}",
                new Object[]{new Integer(index + 1), new Integer(panels.length)});
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Iterator#nextPanel()
     */
    @Override
    public void nextPanel() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index++;
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Iterator#previousPanel()
     */
    @Override
    public void previousPanel() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        index--;
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Iterator#removeChangeListener(javax.swing.event.ChangeListener)
     */
    @Override
    public final void removeChangeListener(ChangeListener l) {
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.InstantiatingIterator#uninitialize(org.openide.WizardDescriptor)
     */
    @Override
    public void uninitialize(WizardDescriptor wiz) {
        this.wiz.putProperty("projdir", null);
        this.wiz.putProperty("name", null);
        this.wiz = null;
        panels = null;
    }

}
