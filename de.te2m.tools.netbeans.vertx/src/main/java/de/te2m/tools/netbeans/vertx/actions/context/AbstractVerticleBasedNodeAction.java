/*
* AbstractVerticleBasedNodeAction.java
*   
* Copyright 2009 - 2016 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
*/
package de.te2m.tools.netbeans.vertx.actions.context;

import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.util.TreePathScanner;
import de.te2m.tools.netbeans.vertx.wizards.Te2mWizardBase;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.swing.text.Document;
import org.netbeans.api.java.source.CompilationController;
import org.netbeans.api.java.source.CompilationInfo;
import org.netbeans.api.java.source.JavaSource;
import org.netbeans.api.project.Project;
//import org.netbeans.modules.maven.api.NbMavenProject;
import org.openide.loaders.DataObject;
import org.openide.awt.StatusDisplayer;
import org.openide.filesystems.FileObject;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.HelpCtx;
import org.openide.util.actions.NodeAction;

/**
 * The Class AbstractVerticleBasedNodeAction.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
public abstract class AbstractVerticleBasedNodeAction extends NodeAction {

    /**
     * The te.
     */
    private TypeElement te;

    /**
     * Instantiates a new abstract verticle based node action.
     */
    public AbstractVerticleBasedNodeAction() {
        super();
    }

    /**
     * Determines the selected class.
     *
     * @param dObj the d obj
     * @throws IllegalArgumentException the illegal argument exception
     */
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

    /* (non-Javadoc)
     * @see org.openide.util.actions.NodeAction#isEnabled()
     */
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

    /**
     * Execute wizard.
     *
     * @param currentnode the currentnode
     */
    protected abstract void executeWizard(Node currentnode);

    /**
     * The Class MemberVisitor.
     *
     * @author ffischer
     * @version 1.0
     * @since 1.0
     */
    private class MemberVisitor extends TreePathScanner<Void, Void> {

        /**
         * The info.
         */
        private CompilationInfo info;

        /**
         * The type element.
         */
        private TypeElement typeElement;

        /**
         * Instantiates a new member visitor.
         *
         * @param info the info
         */
        public MemberVisitor(CompilationInfo info) {
            this.info = info;
        }

        /* (non-Javadoc)
         * @see com.sun.source.util.TreeScanner#visitClass(com.sun.source.tree.ClassTree, java.lang.Object)
         */
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

        /**
         * Gets the type element.
         *
         * @return the type element
         */
        public TypeElement getTypeElement() {
            return typeElement;
        }

    }

    /* (non-Javadoc)
     * @see org.openide.util.actions.NodeAction#enable(org.openide.nodes.Node[])
     */
    @Override
    protected boolean enable(Node[] nodes) {
        // TODO Solve friend dependency issues to maven module and try again :-/
        //boolean isMvn = false;
        if (null != nodes) {
            for (Node currentnode : nodes) {
                DataObject dObj = currentnode.getLookup().lookup(DataObject.class);
                if (null != dObj) {
                    determineSelectedClass(dObj);
                }
                Project pro = Te2mWizardBase.findProjectThatOwnsNode(currentnode);
                
                /*                
                if( null!=pro && pro instanceof NbMavenProject )
                {
                    isMvn=true;
                    NbMavenProject mvnPro = (NbMavenProject)pro;
                    System.out.println("Works :-)");
                }

                 */
            }
        }
        /*
        if (isMvn) {
            return isEnabled();
        }
        
        return false;
         */
        return isEnabled();
    }

    /* (non-Javadoc)
     * @see org.openide.util.actions.SystemAction#getHelpCtx()
     */
    @Override
    public HelpCtx getHelpCtx() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Gets the type element.
     *
     * @return the type element
     */
    public TypeElement getTypeElement() {
        return te;
    }

    /* (non-Javadoc)
     * @see org.openide.util.actions.NodeAction#performAction(org.openide.nodes.Node[])
     */
    @Override
    protected void performAction(Node[] nodes) {
        // context.

        if (null != nodes) {
            for (Node currentnode : nodes) {
                DataObject dObj = currentnode.getLookup().lookup(DataObject.class);
                //Project pRoot = currentnode.getLookup().lookup(Project.class);
                Project pRoot = Te2mWizardBase.findProjectThatOwnsNode(currentnode);
                if (null != dObj) {
                    determineSelectedClass(dObj);
                    FileObject fo = dObj.getPrimaryFile();

                    JavaSource jsource = JavaSource.forFileObject(fo);

                    if (jsource == null) {
                        StatusDisplayer.getDefault().setStatusText("Not a Java file: " + fo.getPath());
                    } else {
                        StatusDisplayer.getDefault().setStatusText("Hurray! A Java file: " + fo.getPath());

                        executeWizard(currentnode);
                    }

                }
            }
        }
    }
}
