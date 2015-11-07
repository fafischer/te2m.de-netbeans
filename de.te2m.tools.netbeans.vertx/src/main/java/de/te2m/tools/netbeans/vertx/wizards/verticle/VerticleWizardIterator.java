/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.te2m.tools.netbeans.vertx.wizards.verticle;

import de.te2m.tools.netbeans.vertx.wizards.TemplateKeys;
import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.event.ChangeListener;
import org.netbeans.api.java.project.JavaProjectConstants;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.api.project.SourceGroup;
import org.netbeans.api.project.Sources;
import org.netbeans.api.templates.TemplateRegistration;
import org.netbeans.spi.project.ui.templates.support.Templates;
import org.openide.WizardDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

// TODO define position attribute
@TemplateRegistration(folder = "Vertx.io", displayName = "#VerticleWizardIterator_displayName", iconBase = "de/te2m/tools/netbeans/vertx/icons/logo16.png", description = "verticle.html",
        content = "Verticle.java.template", scriptEngine = "freemarker")
@Messages("VerticleWizardIterator_displayName=Verticle")
public final class VerticleWizardIterator implements WizardDescriptor.InstantiatingIterator<WizardDescriptor> {

    private int index;

    private WizardDescriptor wizard;
    private List<WizardDescriptor.Panel<WizardDescriptor>> panels;

    private List<WizardDescriptor.Panel<WizardDescriptor>> getPanels() {
        if (panels == null) {
            panels = new ArrayList<WizardDescriptor.Panel<WizardDescriptor>>();
            panels.add(new VerticleWizardPanel1());
            String[] steps = createSteps();
            for (int i = 0; i < panels.size(); i++) {
                Component c = panels.get(i).getComponent();
                if (steps[i] == null) {
                    // Default step name to component name of panel. Mainly
                    // useful for getting the name of the target chooser to
                    // appear in the list of steps.
                    steps[i] = c.getName();
                }
                if (c instanceof JComponent) { // assume Swing components
                    JComponent jc = (JComponent) c;
                    jc.putClientProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, i);
                    jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DATA, steps);
                    jc.putClientProperty(WizardDescriptor.PROP_AUTO_WIZARD_STYLE, true);
                    jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DISPLAYED, true);
                    jc.putClientProperty(WizardDescriptor.PROP_CONTENT_NUMBERED, true);
                }
            }
        }
        return panels;
    }

    @Override
    public Set<?> instantiate() throws IOException {

        Map<String, Object> params = new HashMap<String, Object>();

        String fName = (String) wizard.getProperty(TemplateKeys.PROPERTY_NAME);

        String packName = (String) wizard.getProperty(TemplateKeys.PROPERTY_PACKAGE);

        params.put(TemplateKeys.PROPERTY_NAME, fName);
        params.put(TemplateKeys.PROPERTY_PACKAGE, packName);
        params.put(TemplateKeys.PROPERTY_DECRIPTION, wizard.getProperty(TemplateKeys.PROPERTY_DECRIPTION));
        params.put(TemplateKeys.PROPERTY_USER, System.getProperty("user.name"));
        params.put(TemplateKeys.PROPERTY_CREATION_DATE, new Date());
        //Get the template and convert it:
        FileObject template = Templates.getTemplate(wizard);
        DataObject dTemplate = DataObject.find(template);

        String tmpl = dTemplate.getPrimaryFile().asText();

        Project pRoot = Templates.getProject(wizard);

        FileObject mainJavaRoot = null;

        Sources sources = ProjectUtils.getSources(pRoot);

        SourceGroup[] sgs = sources.getSourceGroups(JavaProjectConstants.SOURCES_TYPE_JAVA);

        if (sgs.length > 0) {
            mainJavaRoot = sgs[0].getRootFolder();
        } else {
            mainJavaRoot = lookupSubDir(pRoot.getProjectDirectory(), "src/main/java");
        }

        String folder = null != wizard.getProperty(TemplateKeys.PROPERTY_PACKAGE) ? (String) wizard.getProperty(TemplateKeys.PROPERTY_PACKAGE) : "";

        FileObject res = lookupSubDir(mainJavaRoot, folder.replace(".", "/"));

        DataObject dobj = dTemplate.createFromTemplate(DataFolder.findFolder(res), fName + ".java", params);

        //Obtain a FileObject:
        FileObject createdFile = dobj.getPrimaryFile();

        return Collections.singleton(createdFile);
    }

    @Override
    public void initialize(WizardDescriptor wizard) {
        this.wizard = wizard;
    }

    @Override
    public void uninitialize(WizardDescriptor wizard) {
        panels = null;
    }

    @Override
    public WizardDescriptor.Panel<WizardDescriptor> current() {
        return getPanels().get(index);
    }

    @Override
    public String name() {
        return index + 1 + ". from " + getPanels().size();
    }

    @Override
    public boolean hasNext() {
        return index < getPanels().size() - 1;
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public void nextPanel() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index++;
    }

    @Override
    public void previousPanel() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        index--;
    }

    // If nothing unusual changes in the middle of the wizard, simply:
    @Override
    public void addChangeListener(ChangeListener l) {
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
    }
    // If something changes dynamically (besides moving between panels), e.g.
    // the number of panels changes in response to user input, then use
    // ChangeSupport to implement add/removeChangeListener and call fireChange
    // when needed

    // You could safely ignore this method. Is is here to keep steps which were
    // there before this wizard was instantiated. It should be better handled
    // by NetBeans Wizard API itself rather than needed to be implemented by a
    // client code.
    private String[] createSteps() {
        String[] beforeSteps = (String[]) wizard.getProperty("WizardPanel_contentData");
        assert beforeSteps != null : "This wizard may only be used embedded in the template wizard";
        String[] res = new String[(beforeSteps.length - 1) + panels.size()];
        for (int i = 0; i < res.length; i++) {
            if (i < (beforeSteps.length - 1)) {
                res[i] = beforeSteps[i];
            } else {
                res[i] = panels.get(i - beforeSteps.length + 1).getComponent().getName();
            }
        }
        return res;
    }

    /**
     * Gets the sub dir.
     *
     * @param baseDir the base dir
     * @param name the name
     * @param createIfMissing the create if missing
     * @return the sub dir
     */
    private FileObject getSubDir(FileObject baseDir, String name, boolean createIfMissing) {

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
     * Lookup sub dir.
     *
     * @param baseDir the base dir
     * @param name the name
     * @return the file object
     */
    private FileObject lookupSubDir(FileObject baseDir, String name) {

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
