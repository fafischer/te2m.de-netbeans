package de.te2m.tools.netbeans.vertx.actions.context.docker;

import de.te2m.tools.netbeans.vertx.actions.context.AbstractVerticleBasedNodeAction;
import de.te2m.tools.netbeans.vertx.wizards.Te2mWizardBase;
import de.te2m.tools.netbeans.vertx.wizards.TemplateKeys;
import static de.te2m.tools.netbeans.vertx.wizards.TemplateKeys.DN_PROPERTY_CLASS_NAME;
import static de.te2m.tools.netbeans.vertx.wizards.TemplateKeys.DN_PROPERTY_PACKAGE;
import de.te2m.tools.netbeans.vertx.wizards.testcase.VerticleTestWizardPanel1;
import java.awt.Component;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import static org.netbeans.api.java.project.JavaProjectConstants.SOURCES_HINT_TEST;
import org.netbeans.api.project.Project;
import static org.netbeans.api.project.ProjectUtils.getSources;
import org.netbeans.api.project.SourceGroup;
import org.netbeans.api.project.Sources;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.cookies.OpenCookie;
import org.openide.filesystems.FileObject;
import static org.openide.loaders.DataFolder.findFolder;
import org.openide.loaders.DataObject;
import static org.openide.loaders.DataObject.find;
import org.openide.nodes.Node;

@ActionID(
        category = "Tools",
        id = "de.te2m.tools.netbeans.vertx.actions.context.docker.DockerAction"
)
@ActionRegistration(
        iconBase = "de/te2m/tools/netbeans/vertx/icons/logo16.png",
        displayName = "#CTL_DockerAction",
        lazy = true
) 
@ActionReference(path = "Loaders/text/x-java/Actions", position = 300)
public final class DockerAction extends AbstractVerticleBasedNodeAction {

    public DockerAction() {
        super();
    }


    public String getName() {
        return "Create Dockerfile for Verticle";
    }



    protected void executeWizard(Node currentnode) {
        DataObject dObj = currentnode.getLookup().lookup(DataObject.class);
        //Project pRoot = currentnode.getLookup().lookup(Project.class);
        Project pRoot = Te2mWizardBase.findProjectThatOwnsNode(currentnode);
        String baseName = getTypeElement().getQualifiedName().toString();

        List<WizardDescriptor.Panel<WizardDescriptor>> panels = new ArrayList<WizardDescriptor.Panel<WizardDescriptor>>();
        panels.add(new VerticleTestWizardPanel1());
        //panels.add(new DemoWizardPanel2());
        String[] steps = new String[panels.size()];
        for (int i = 0; i < panels.size(); i++) {
            Component c = panels.get(i).getComponent();
            // Default step name to component name of panel. 
            steps[i] = c.getName();
            if (c instanceof JComponent) { // assume Swing components 
                JComponent jc = (JComponent) c;
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, i);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DATA, steps);
                jc.putClientProperty(WizardDescriptor.PROP_AUTO_WIZARD_STYLE, true);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DISPLAYED, true);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_NUMBERED, true);
            }
            WizardDescriptor wiz = new WizardDescriptor(new WizardDescriptor.ArrayIterator<WizardDescriptor>(panels));
            // {0} will be replaced by WizardDesriptor.Panel.getComponent().getName() 
            wiz.setTitleFormat(new MessageFormat("{0}"));
            wiz.setTitle("Create Testcase for Verticle");
            // Add Parameters
            if (null != baseName && baseName.trim().length() > 0) {
                String className = baseName.substring(baseName.lastIndexOf(".") + 1);

                wiz.putProperty(TemplateKeys.DN_PROPERTY_CLASS_NAME, className);

                wiz.putProperty(TemplateKeys.DN_PROPERTY_PACKAGE, baseName.substring(0, baseName.lastIndexOf(".")));

            }

            Object status = DialogDisplayer.getDefault().notify(wiz);

            if (status.equals(WizardDescriptor.FINISH_OPTION)) {
                try {
                    Map<String, Object> params = new HashMap<>();

                    String fName = (String) wiz.getProperty(DN_PROPERTY_CLASS_NAME);

                    String packName = (String) wiz.getProperty(DN_PROPERTY_PACKAGE);

                    params.put(DN_PROPERTY_CLASS_NAME, fName);
                    params.put(DN_PROPERTY_PACKAGE, packName);

                    DataObject dobj = Te2mWizardBase.generateDockerFile(pRoot.getProjectDirectory(), params,  true);

                    dobj.getLookup().lookup(OpenCookie.class).open();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } else {

            }
        }

    }
}
