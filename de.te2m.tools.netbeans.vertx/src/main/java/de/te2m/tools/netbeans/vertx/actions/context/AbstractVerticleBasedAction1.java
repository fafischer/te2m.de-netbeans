package de.te2m.tools.netbeans.vertx.actions.context;

import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.util.TreePathScanner;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.swing.text.Document;
import org.netbeans.api.java.source.CompilationController;
import org.netbeans.api.java.source.CompilationInfo;
import org.netbeans.api.java.source.JavaSource;
import org.openide.loaders.DataObject;
import org.openide.awt.StatusDisplayer;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;

public abstract class AbstractVerticleBasedAction1 implements ActionListener {

    private final DataObject context;
    TypeElement te;

    public AbstractVerticleBasedAction1(DataObject context) {
        this.context = context;
        determineClassUnderCarret();
    }

    protected final void determineClassUnderCarret() throws IllegalArgumentException {
        // context.
        FileObject fo = context.getPrimaryFile();

        JavaSource jsource = JavaSource.forFileObject(fo);

        if (jsource == null) {
            StatusDisplayer.getDefault().setStatusText("Not a Java file: " + fo.getPath());
        } else {
            //StatusDisplayer.getDefault().setStatusText("Hurray! A Java file: " + fo.getPath());

            try {
                jsource.runUserActionTask((CompilationController p) -> {
                    p.toPhase(JavaSource.Phase.ELEMENTS_RESOLVED);
                    CompilationUnitTree tree = p.getCompilationUnit();
                    MemberVisitor scanner = new MemberVisitor(p);
                    scanner.scan(p.getCompilationUnit(), null);
                    te = scanner.getTypeElement();
                    Document document = p.getDocument();
                }, true);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }

        }
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
                /*                
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
                 */
            }
            return null;
        }

        public TypeElement getTypeElement() {
            return typeElement;
        }

    }

}
