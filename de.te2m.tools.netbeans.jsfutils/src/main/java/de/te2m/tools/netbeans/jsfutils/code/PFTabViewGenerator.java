/*
* PFTabViewGenerator.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.jsfutils project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.netbeans.jsfutils.code;

import java.util.Collections;
import java.util.List;

import javax.swing.text.JTextComponent;

import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.codegen.CodeGenerator;
import org.netbeans.spi.editor.codegen.CodeGeneratorContextProvider;
import org.openide.util.Lookup;

import de.te2m.tools.netbeans.jsfutils.Templates;

/**
 * The Class PFTabViewGenerator.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
public class PFTabViewGenerator extends AbstractCodeGenerator implements CodeGenerator {

    /**
     * Instantiates a new PF tab view generator.
     *
     * @param context containing JTextComponent and possibly other items
     * registered by {@link CodeGeneratorContextProvider}
     */
    private PFTabViewGenerator(Lookup context) { // Good practice is not to save Lookup outside ctor
        textComp = context.lookup(JTextComponent.class);
    }

    /* (non-Javadoc)
     * @see de.te2m.tools.netbeans.code.AbstractCodeGenerator#getContent()
     */
    @Override
    protected String getContent() {
        return Templates.PF_TAB_VIEW;
    }

    /**
     * The Class Factory.
     *
     * @author ffischer
     * @version 1.0
     * @since 1.0
     */
    @MimeRegistration(mimeType = "text/html", service = CodeGenerator.Factory.class)
    public static class Factory implements CodeGenerator.Factory {

        /* (non-Javadoc)
         * @see org.netbeans.spi.editor.codegen.CodeGenerator.Factory#create(org.openide.util.Lookup)
         */
        @Override
        public List<? extends CodeGenerator> create(Lookup context) {
            return Collections.singletonList(new PFTabViewGenerator(context));
        }
    }

    /**
     * The name which will be inserted inside Insert Code dialog.
     *
     * @return the display name
     */
    @Override
    public String getDisplayName() {
        return "PrimeFaces Tab View";
    }

}
