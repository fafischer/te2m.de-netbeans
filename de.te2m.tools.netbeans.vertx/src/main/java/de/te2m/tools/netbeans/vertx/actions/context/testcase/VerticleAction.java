package de.te2m.tools.netbeans.vertx.actions.context.testcase;

import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.util.TreePathScanner;
import de.te2m.tools.netbeans.vertx.actions.context.AbstractVerticleBasedAction;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.swing.Action;
import javax.swing.text.Document;
import org.netbeans.api.java.source.CompilationController;
import org.netbeans.api.java.source.CompilationInfo;
import org.netbeans.api.java.source.JavaSource;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.awt.StatusDisplayer;
import org.openide.filesystems.FileObject;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

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
            //StatusDisplayer.getDefault().setStatusText("Hurray! A Java file: " + fo.getPath());

            try {
                jsource.runUserActionTask((CompilationController p) -> {
                    p.toPhase(JavaSource.Phase.ELEMENTS_RESOLVED);
                    CompilationUnitTree tree = p.getCompilationUnit();
                    new MemberVisitor(p).scan(p.getCompilationUnit(), null);
                    Document document = p.getDocument();
                    if (document != null) {
                        StatusDisplayer.getDefault().setStatusText("Hurray, the Java file is open!");
                    } else {
                        StatusDisplayer.getDefault().setStatusText("The Java file is closed!");
                    }
                }, true);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }

        }
    }

    @Override
    public HelpCtx getHelpCtx() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class MemberVisitor extends TreePathScanner<Void, Void> {

        private CompilationInfo info;

        private TypeElement typeElement;

        public MemberVisitor(CompilationInfo info) {
            this.info = info;
        }

        @Override
        public Void visitClass(ClassTree t, Void v) {
            Element el = info.getTrees().getElement(getCurrentPath());
            if (el == null) {
                StatusDisplayer.getDefault().setStatusText("Cannot resolve class!");
            } else {
                TypeElement te = (TypeElement) el;

                typeElement = te;

                List<? extends Element> enclosedElements = te.getEnclosedElements();
                InputOutput io = IOProvider.getDefault().getIO("Analysis of " + info.getFileObject().getName(), true);
                for (int i = 0; i < enclosedElements.size(); i++) {
                    Element enclosedElement = (Element) enclosedElements.get(i);
                    if (null != enclosedElement.getKind()) {
                        switch (enclosedElement.getKind()) {
                            case CONSTRUCTOR:
                                io.getOut().println("Constructor: " + enclosedElement.getSimpleName());
                                break;
                            case METHOD:
                                io.getOut().println("Method: " + enclosedElement.getSimpleName());
                                break;
                            case FIELD:
                                io.getOut().println("Field: " + enclosedElement.getSimpleName());
                                break;
                            default:
                                io.getOut().println("Other: " + enclosedElement.getSimpleName());
                                break;
                        }
                    }
                }
                io.getOut().println("Superclass: " + te.getSuperclass().toString());
                io.getOut().close();
            }
            return null;
        }

        public TypeElement getTypeElement() {
            return typeElement;
        }

    }

    public String getName() {
        return "Do something based on a verticle";
    }

}
