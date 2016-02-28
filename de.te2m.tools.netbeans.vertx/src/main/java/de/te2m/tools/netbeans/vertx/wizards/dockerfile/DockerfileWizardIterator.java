/*
* DockerfileWizardIterator.java
*   
* Copyright 2009 - 2016 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
*/
package de.te2m.tools.netbeans.vertx.wizards.dockerfile;
import de.te2m.tools.netbeans.vertx.wizards.Te2mWizardBase;
import de.te2m.tools.netbeans.vertx.wizards.TemplateIDs;
import static de.te2m.tools.netbeans.vertx.wizards.TemplateKeys.PROPERTY_NAME;
import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.event.ChangeListener;
import static org.netbeans.api.java.project.JavaProjectConstants.SOURCES_HINT_TEST;
import org.netbeans.api.project.Project;
import static org.netbeans.api.project.ProjectUtils.getSources;
import org.netbeans.api.project.SourceGroup;
import org.netbeans.api.project.Sources;
import org.netbeans.api.templates.TemplateRegistration;
import static org.netbeans.spi.project.ui.templates.support.Templates.getProject;
import static org.netbeans.spi.project.ui.templates.support.Templates.getTemplate;
import org.openide.WizardDescriptor;
import static org.openide.WizardDescriptor.PROP_AUTO_WIZARD_STYLE;
import static org.openide.WizardDescriptor.PROP_CONTENT_DATA;
import static org.openide.WizardDescriptor.PROP_CONTENT_DISPLAYED;
import static org.openide.WizardDescriptor.PROP_CONTENT_NUMBERED;
import static org.openide.WizardDescriptor.PROP_CONTENT_SELECTED_INDEX;
import org.openide.filesystems.FileObject;
import static org.openide.loaders.DataFolder.findFolder;
import org.openide.loaders.DataObject;
import static org.openide.loaders.DataObject.find;
import org.openide.util.NbBundle.Messages;
import static de.te2m.tools.netbeans.vertx.wizards.TemplateKeys.DN_PROPERTY_PACKAGE;
import static de.te2m.tools.netbeans.vertx.wizards.TemplateKeys.DN_DESCRIPTION;

// TODO define position attribute
/**
 * The Class DockerfileWizardIterator.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
@TemplateRegistration(folder = "Vertx.io", displayName = "#VerticleWizardIterator_displayName", iconBase = "de/te2m/tools/netbeans/vertx/icons/logo16.png", description = "Dockerfile.html", content = "../" + TemplateIDs.VERTX_DOCKER + ".template", scriptEngine = "freemarker")
@Messages("VerticleWizardIterator_displayName=Dockerfile")
public final class DockerfileWizardIterator extends Te2mWizardBase implements WizardDescriptor.InstantiatingIterator<WizardDescriptor> {

    /**
     * The index.
     */
    private int index;

    /**
     * The wizard.
     */
    private WizardDescriptor wizard;

    /**
     * The panels.
     */
    private List<WizardDescriptor.Panel<WizardDescriptor>> panels;

    // If nothing unusual changes in the middle of the wizard, simply:
    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Iterator#addChangeListener(javax.swing.event.ChangeListener)
     */
    @Override
    public void addChangeListener(ChangeListener l) {
    }

    // You could safely ignore this method. Is is here to keep steps which were
    // there before this wizard was instantiated. It should be better handled
    // by NetBeans Wizard API itself rather than needed to be implemented by a
    // client code.
    /**
     * Creates the steps.
     *
     * @return the string[]
     */
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

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Iterator#current()
     */
    @Override
    public WizardDescriptor.Panel<WizardDescriptor> current() {
        return getPanels().get(index);
    }

    /**
     * Gets the panels.
     *
     * @return the panels
     */
    private List<WizardDescriptor.Panel<WizardDescriptor>> getPanels() {
        if (panels == null) {
            panels = new ArrayList<>();
            panels.add(new DockerfileWizardPanel1());
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
                    jc.putClientProperty(PROP_CONTENT_SELECTED_INDEX, i);
                    jc.putClientProperty(PROP_CONTENT_DATA, steps);
                    jc.putClientProperty(PROP_AUTO_WIZARD_STYLE, true);
                    jc.putClientProperty(PROP_CONTENT_DISPLAYED, true);
                    jc.putClientProperty(PROP_CONTENT_NUMBERED, true);
                }
            }
        }
        return panels;
    }



    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Iterator#hasNext()
     */
    @Override
    public boolean hasNext() {
        return index < getPanels().size() - 1;
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
    public void initialize(WizardDescriptor wizard) {
        this.wizard = wizard;
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.InstantiatingIterator#instantiate()
     */
    @Override
    public Set<?> instantiate() throws IOException {

        Map<String, Object> params = new HashMap<>();

        String fName = (String) wizard.getProperty(PROPERTY_NAME);

        String packName = (String) wizard.getProperty(DN_PROPERTY_PACKAGE);

        params.put(PROPERTY_NAME, fName);
        params.put(DN_PROPERTY_PACKAGE, packName);
        params.put(DN_DESCRIPTION, wizard.getProperty(DN_DESCRIPTION));

        initializeCommonProperties(params);
        //Get the template and convert it:
        FileObject template = getTemplate(wizard);
        DataObject dTemplate = find(template);

        String tmpl = dTemplate.getPrimaryFile().asText();

        Project pRoot = getProject(wizard);

        FileObject mainJavaRoot = null;

        Sources sources = getSources(pRoot);

        SourceGroup[] sgs = sources.getSourceGroups(SOURCES_HINT_TEST);

        if (sgs.length > 0) {
            mainJavaRoot = sgs[0].getRootFolder();
        } else {
            mainJavaRoot = lookupSubDir(pRoot.getProjectDirectory(), "src/test/java");
        }

        String folder = null != wizard.getProperty(DN_PROPERTY_PACKAGE) ? (String) wizard.getProperty(DN_PROPERTY_PACKAGE) : "";

        FileObject res = lookupSubDir(mainJavaRoot, folder.replace(".", "/"));

        DataObject dobj = dTemplate.createFromTemplate(findFolder(res), fName + "Test.java", params);

        //Obtain a FileObject:
        FileObject createdFile = dobj.getPrimaryFile();

        return singleton(createdFile);
    }

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.Iterator#name()
     */
    @Override
    public String name() {
        return index + 1 + ". from " + getPanels().size();
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
    public void removeChangeListener(ChangeListener l) {
    }
    // If something changes dynamically (besides moving between panels), e.g.
    // the number of panels changes in response to user input, then use
    // ChangeSupport to implement add/removeChangeListener and call fireChange
    // when needed

    /* (non-Javadoc)
     * @see org.openide.WizardDescriptor.InstantiatingIterator#uninitialize(org.openide.WizardDescriptor)
     */
    @Override
    public void uninitialize(WizardDescriptor wizard) {
        panels = null;
    }

}
