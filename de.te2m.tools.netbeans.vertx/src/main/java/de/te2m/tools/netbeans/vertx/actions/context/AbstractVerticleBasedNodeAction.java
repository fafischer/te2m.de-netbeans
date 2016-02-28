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
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.HelpCtx;
import org.openide.util.actions.NodeAction;

public abstract class AbstractVerticleBasedNodeAction extends NodeAction  {

    private TypeElement te;

    public AbstractVerticleBasedNodeAction() {
        super();
    }


    protected final void determineSelectedClass(DataObject dObj) throws IllegalArgumentException {
        if (null != dObj) {

            FileObject fo = dObj.getPrimaryFile();

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
    }

    @Override
    public boolean isEnabled() {

        if (null == te) {
            return false;
        }
        if (null != te.getSuperclass() && "io.vertx.core.AbstractVerticle".equals(te.getSuperclass().toString())) {
            return true;
        } else {
            return false;
        }

        //return super.isEnabled(); 
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
                typeElement = (TypeElement) el;
            }
            return null;
        }

        public TypeElement getTypeElement() {
            return typeElement;
        }

    }


    @Override
    protected boolean enable(Node[] nodes) {

        if (null != nodes) {
            for (Node currentnode : nodes) {
                DataObject dObj = currentnode.getLookup().lookup(DataObject.class);
                if (null != dObj) {
                    determineSelectedClass(dObj);
                }
            }
        }

        return isEnabled();
    }
    
    
        @Override
    public HelpCtx getHelpCtx() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
