package de.te2m.tools.netbeans.vertx.actions.context.testcase;

import de.te2m.tools.netbeans.vertx.actions.context.AbstractVerticleBasedAction;
import java.awt.event.ActionEvent;
import org.netbeans.api.java.source.JavaSource;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.awt.StatusDisplayer;
import org.openide.filesystems.FileObject;
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
public final class VerticleAction extends AbstractVerticleBasedAction {

    public VerticleAction() {
        super();
    }

    public VerticleAction(DataObject context) {
        super(context);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {

        // context.
        FileObject fo = getContext().getPrimaryFile();

        JavaSource jsource = JavaSource.forFileObject(fo);

        if (jsource == null) {
            StatusDisplayer.getDefault().setStatusText("Not a Java file: " + fo.getPath());
        } else {
            StatusDisplayer.getDefault().setStatusText("Hurray! A Java file: " + fo.getPath());
        }
    }

    @Override
    public HelpCtx getHelpCtx() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public String getName() {
        return "Do something based on a verticle";
    }

}
