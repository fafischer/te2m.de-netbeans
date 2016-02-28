package de.te2m.tools.netbeans.vertx.actions.context.testcase;

import de.te2m.tools.netbeans.vertx.actions.context.AbstractVerticleBasedNodeAction;
import java.awt.event.ActionEvent;
import org.netbeans.api.java.source.JavaSource;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.awt.StatusDisplayer;
import org.openide.filesystems.FileObject;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
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
public final class VerticleAction extends AbstractVerticleBasedNodeAction {

    public VerticleAction() {
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
                    FileObject fo = dObj.getPrimaryFile();

                    JavaSource jsource = JavaSource.forFileObject(fo);

                    if (jsource == null) {
                        StatusDisplayer.getDefault().setStatusText("Not a Java file: " + fo.getPath());
                    } else {
                        StatusDisplayer.getDefault().setStatusText("Hurray! A Java file: " + fo.getPath());
                    }

                }
            }
        }
    }

}
