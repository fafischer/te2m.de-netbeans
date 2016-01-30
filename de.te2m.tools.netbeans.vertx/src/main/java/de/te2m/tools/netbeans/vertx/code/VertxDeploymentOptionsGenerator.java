/*
* VertxDeploymentOptionsGenerator.java
*   
* Copyright 2009 - 2016 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
*/
package de.te2m.tools.netbeans.vertx.code;

import static de.te2m.tools.netbeans.vertx.Templates.DEPLOYMENT_OPTIONS;
import static java.util.Collections.singletonList;
import java.util.List;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.codegen.CodeGenerator;
import org.netbeans.spi.editor.codegen.CodeGeneratorContextProvider;
import org.openide.util.Lookup;

/**
 * The Class PFTabGenerator.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
public class VertxDeploymentOptionsGenerator extends AbstractCodeGenerator implements CodeGenerator {

    /**
     * The Class Factory.
     *
     * @author ffischer
     * @version 1.0
     * @since 1.0
     */
    @MimeRegistration(mimeType = "text/x-java", service = CodeGenerator.Factory.class)
    public static class Factory implements CodeGenerator.Factory {

        /* (non-Javadoc)
         * @see org.netbeans.spi.editor.codegen.CodeGenerator.Factory#create(org.openide.util.Lookup)
         */
        @Override
        public List<? extends CodeGenerator> create(Lookup context) {
            return singletonList(new VertxDeploymentOptionsGenerator(context));
        }
    }

    /**
     * Instantiates a new PF tab generator.
     *
     * @param context containing JTextComponent and possibly other items
     * registered by {@link CodeGeneratorContextProvider}
     */
    private VertxDeploymentOptionsGenerator(Lookup context) { // Good practice is not to save Lookup outside ctor
        textComp = context.lookup(JTextComponent.class);
    }

    /* (non-Javadoc)
     * @see de.te2m.tools.netbeans.code.AbstractCodeGenerator#getContent()
     */
    @Override
    protected String getContent() {
        return DEPLOYMENT_OPTIONS;
    }

    /**
     * The name which will be inserted inside Insert Code dialog.
     *
     * @return the display name
     */
    @Override
    public String getDisplayName() {
        return "vertx.io Deployment Options";
    }

}
