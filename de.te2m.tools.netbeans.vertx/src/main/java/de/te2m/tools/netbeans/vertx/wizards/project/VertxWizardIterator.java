/*
* VertxWizardIterator.java
*   
* Copyright 2009 - 2016 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
 */
package de.te2m.tools.netbeans.vertx.wizards.project;

import de.te2m.tools.netbeans.vertx.wizards.Te2mWizardBase;
import de.te2m.tools.netbeans.vertx.wizards.TemplateIDs;
import de.te2m.tools.netbeans.vertx.wizards.TemplateKeys;
import de.te2m.tools.netbeans.vertx.wizards.VerticleWizardPanel;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import static java.text.MessageFormat.format;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.event.ChangeListener;
import org.netbeans.api.templates.TemplateRegistration;
import org.netbeans.api.templates.TemplateRegistrations;
import static org.netbeans.spi.project.ui.templates.support.Templates.getTemplate;
import org.openide.WizardDescriptor;
import org.openide.filesystems.FileObject;
import static org.openide.filesystems.FileUtil.normalizeFile;
import static org.openide.filesystems.FileUtil.toFileObject;
import static org.openide.loaders.DataFolder.findFolder;
import org.openide.loaders.DataObject;
import static org.openide.loaders.DataObject.find;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;


/**
 * The Class VertxWizardIterator.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
@TemplateRegistrations({
    @TemplateRegistration(folder = "Project/Vertx.io", displayName = "#Vertx_displayName", description = "VertxDescription.html", iconBase = "de/te2m/tools/netbeans/vertx/icons/logo16.png", content = "../" + TemplateIDs.VERTX_POM + ".template", scriptEngine = "freemarker")
})
@Messages("Vertx_displayName=Create a new Vertx.io 3.1 Project")
public class VertxWizardIterator extends Te2mWizardBase implements WizardDescriptor./*Progress*/InstantiatingIterator {

    /**
     * Creates the iterator.
     *
     * @return the vertx wizard iterator
     */
    public static VertxWizardIterator createIterator() {
        return new VertxWizardIterator();
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
            new VertxWizardProjectPanel(),
            new VerticleWizardPanel(),
            new VertxWizardMavenPanel(),
            new VertxWizardPackagingPanel()};
    }

    /**
     * Creates the steps.
     *
     * @return the string[]
     */
    private String[] createSteps() {
        return new String[]{
            NbBundle.getMessage(VertxWizardIterator.class, "LBL_CreateProjectStep"),
            NbBundle.getMessage(VertxWizardIterator.class, "LBL_CreateVerticleStep"),
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
                jc.putClientProperty("WizardPanel_contentSelectedIndex", i);
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

        // initialize reused values
        String className = (String) wiz.getProperty(DN_PROPERTY_CLASS_NAME);
        String pkgName = (String) wiz.getProperty(DN_PROPERTY_PACKAGE);
        Boolean createDocker = (Boolean) wiz.getProperty(GEN_CFG_CREATE_DOCKER);
        Boolean createFatJar = (Boolean) wiz.getProperty(GEN_CFG_CREATE_FAT_JAR);
        Boolean createVerticleTest = (Boolean) wiz.getProperty(GEN_CFG_CREATE_VERTICLE_TEST);

        // initialize parameters
        Map<String, Object> params = new HashMap<>();
        initializeCommonProperties(params);
        initializePomInfo(params, wiz);
        params.put(DN_PROPERTY_CLASS_NAME, className);
        params.put(DN_PROPERTY_PACKAGE, pkgName);
        params.put(DN_PROPERTY_CLASS_DESCRIPTION, wiz.getProperty(DN_PROPERTY_CLASS_DESCRIPTION));
        params.put(GEN_CFG_CREATE_DOCKER, createDocker);
        params.put(GEN_CFG_CREATE_FAT_JAR, createFatJar);
        params.put(TemplateKeys.DN_DOCKER_IMAGE_NAME, wiz.getProperty(TemplateKeys.DN_DOCKER_IMAGE_NAME));
        //params.put(PROPERTY_VERTX_VERSION, wiz.getProperty(VERTX_VERSION));
        Set<FileObject> resultSet = new LinkedHashSet<>();
        File projectBaseDir = normalizeFile((File) wiz.getProperty("projdir"));
        projectBaseDir.mkdirs();

        FileObject template = getTemplate(wiz);
        DataObject dTemplate = find(template);
        FileObject projectBaseDirFO = toFileObject(projectBaseDir);

        // Always open top projectBaseDirFO as a project:
        resultSet.add(projectBaseDirFO);

        DataObject dobj = dTemplate.createFromTemplate(findFolder(projectBaseDirFO), "pom.xml", params);

        //Obtain a FileObject:
        FileObject createdFile = dobj.getPrimaryFile();

        resultSet.add(createdFile);

        FileObject mainJavaRoot = lookupSubDir(projectBaseDirFO, "src/main/java");
        
        String folder = null != pkgName ? pkgName : "";

        FileObject res;

        FileObject fo = getTemplateByNameAndFolder("BaseVerticle.java", TemplateIDs.TEMPLATE_GROUP_VERTX);

        if (null != fo) {
            res = lookupSubDir(mainJavaRoot, folder.replace(".", "/"));
            DataObject currentTemplateDO = find(fo);
            DataObject classDO = currentTemplateDO.createFromTemplate(findFolder(res), className + ".java", params);
            resultSet.add(classDO.getPrimaryFile());
        }

        if (Boolean.TRUE.equals(createVerticleTest)) {
            
            FileObject mainTestRoot = lookupSubDir(projectBaseDirFO, "src/test/java");
            
            fo = getTemplateByNameAndFolder("VerticleUnitTest.java", TemplateIDs.TEMPLATE_GROUP_VERTX);

            if (null != fo) {
                res = lookupSubDir(mainTestRoot, folder.replace(".", "/"));
                DataObject currentTemplateDO = find(fo);
                DataObject classDO = currentTemplateDO.createFromTemplate(findFolder(res), className + "Test.java", params);
                resultSet.add(classDO.getPrimaryFile());
            }
        }
        if (Boolean.TRUE.equals(createDocker) && Boolean.TRUE.equals(createFatJar)) {
            DataObject result = generateDockerFile(projectBaseDirFO, params,true);
            if(null!=result)
            {
                resultSet.add(result.getPrimaryFile());
            }
        }

        return resultSet;
    }



    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Iterator#name()
     */
    @Override
    public String name() {
        return format("{0} of {1}",
                new Object[]{index + 1, panels.length});
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
