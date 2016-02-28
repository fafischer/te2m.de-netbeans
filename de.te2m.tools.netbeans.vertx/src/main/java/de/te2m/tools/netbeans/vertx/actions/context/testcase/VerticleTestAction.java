package de.te2m.tools.netbeans.vertx.actions.context.testcase;

import de.te2m.tools.netbeans.vertx.actions.context.AbstractVerticleBasedNodeAction;
import de.te2m.tools.netbeans.vertx.wizards.Te2mWizardBase;
import de.te2m.tools.netbeans.vertx.wizards.TemplateKeys;
import static de.te2m.tools.netbeans.vertx.wizards.TemplateKeys.DN_DESCRIPTION;
import static de.te2m.tools.netbeans.vertx.wizards.TemplateKeys.DN_PROPERTY_CLASS_DESCRIPTION;
import static de.te2m.tools.netbeans.vertx.wizards.TemplateKeys.DN_PROPERTY_CLASS_NAME;
import static de.te2m.tools.netbeans.vertx.wizards.TemplateKeys.DN_PROPERTY_PACKAGE;
import static de.te2m.tools.netbeans.vertx.wizards.TemplateKeys.PROPERTY_NAME;
import de.te2m.tools.netbeans.vertx.wizards.testcase.VerticleTestWizardPanel1;
import java.awt.Component;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComponent;
import static org.netbeans.api.java.project.JavaProjectConstants.SOURCES_HINT_TEST;
import org.netbeans.api.java.source.JavaSource;
import org.netbeans.api.project.Project;
import static org.netbeans.api.project.ProjectUtils.getSources;
import org.netbeans.api.project.SourceGroup;
import org.netbeans.api.project.Sources;
import static org.netbeans.spi.project.ui.templates.support.Templates.getProject;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.awt.StatusDisplayer;
import org.openide.cookies.OpenCookie;
import org.openide.filesystems.FileObject;
import static org.openide.loaders.DataFolder.findFolder;
import static org.openide.loaders.DataObject.find;
import org.openide.nodes.Node;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Tools",
        id = "de.te2m.tools.netbeans.vertx.actions.context.DockerAction1"
)
@ActionRegistration(
        iconBase = "de/te2m/tools/netbeans/vertx/icons/logo16.png",
        displayName = "#CTL_DockerAction1"
)
@ActionReference(path = "Loaders/text/x-java/Actions", position = 200)
@Messages("CTL_DockerAction1=Create Dockerfile / 2")
public final class VerticleTestAction extends AbstractVerticleBasedNodeAction {

    public VerticleTestAction() {
        super();
    }

    public String getName() {
        return "Create Testcase for Verticle";
    }

    @Override
    protected void performAction(Node[] nodes) {
        // context.

        if (null != nodes) {
            for (Node currentnode : nodes) {
                DataObject dObj = currentnode.getLookup().lookup(DataObject.class);
                if (null != dObj) {
                    determineSelectedClass(dObj);
                    FileObject fo = dObj.getPrimaryFile();

                    JavaSource jsource = JavaSource.forFileObject(fo);

                    if (jsource == null) {
                        StatusDisplayer.getDefault().setStatusText("Not a Java file: " + fo.getPath());
                    } else {
                        StatusDisplayer.getDefault().setStatusText("Hurray! A Java file: " + fo.getPath());

                        executeWizard(dObj);
                    }

                }
            }
        }
    }

    protected void executeWizard(DataObject mainObject) {
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

                wiz.putProperty(TemplateKeys.DN_PROPERTY_CLASS_NAME, className + "Test");

                wiz.putProperty(TemplateKeys.DN_PROPERTY_PACKAGE, baseName.substring(0, baseName.lastIndexOf(".") + 1));

            }
            
            Object status = DialogDisplayer.getDefault().notify(wiz);
            
            if (status.equals(WizardDescriptor.FINISH_OPTION)) {
                try {
                    Map<String, Object> params = new HashMap<>();

                    String fName = (String) wiz.getProperty(PROPERTY_NAME);

                    String packName = (String) wiz.getProperty(DN_PROPERTY_PACKAGE);

                    params.put(DN_PROPERTY_CLASS_NAME, fName);
                    params.put(DN_PROPERTY_PACKAGE, packName);
                    params.put(DN_PROPERTY_CLASS_DESCRIPTION, wiz.getProperty(DN_DESCRIPTION));

                    //Get the template and convert it:
                    FileObject template = Te2mWizardBase.getTemplateByNameAndFolder("VerticleUnitTest.java", "Vertx.io");
                    DataObject dTemplate = find(template);

                    Project pRoot = mainObject.getLookup().lookup(Project.class);
//getProject(wiz);

                    FileObject mainJavaRoot = null;

                    Sources sources = getSources(pRoot);

                    SourceGroup[] sgs = sources.getSourceGroups(SOURCES_HINT_TEST);

                    if (sgs.length > 0) {
                        mainJavaRoot = sgs[0].getRootFolder();
                    } else {
                        mainJavaRoot = Te2mWizardBase.lookupSubDir(pRoot.getProjectDirectory(), "src/test/java");
                    }

                    String folder = null != wiz.getProperty(DN_PROPERTY_PACKAGE) ? (String) wiz.getProperty(DN_PROPERTY_PACKAGE) : "";

                    FileObject res = Te2mWizardBase.lookupSubDir(mainJavaRoot, folder.replace(".", "/"));

                    DataObject dobj = dTemplate.createFromTemplate(findFolder(res), fName + "Test.java", params);
                    
                    dobj.getLookup().lookup(OpenCookie.class).open();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
            else
            {
                
            }
        }
        

    }
}
