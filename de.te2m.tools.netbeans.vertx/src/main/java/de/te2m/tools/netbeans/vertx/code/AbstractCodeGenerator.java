/*
* AbstractCodeGenerator.java
*   
* Copyright 2009 - 2016 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
*/
package de.te2m.tools.netbeans.vertx.code;

import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;
import static org.openide.util.Exceptions.printStackTrace;

/**
 * The Class AbstractCodeGenerator.
 * Abstract super class for all code generators.
 *
 * @author ffischer
 */
public abstract class AbstractCodeGenerator {

    /**
     * The text comp.
     */
    JTextComponent textComp;

    /**
     * Instantiates a new abstract code generator.
     */
    public AbstractCodeGenerator() {
    }

    /**
     * Gets the content.
     *
     * @return the content
     */
    protected abstract String getContent();

    /**
     * This will be invoked when user chooses this Generator from Insert Code
     * dialog.
     */
    public void invoke() {
        try {
            Caret caret = textComp.getCaret();
            int dot = caret.getDot();
            textComp.getDocument().insertString(dot, getContent(), null);
        } catch (BadLocationException ex) {
            printStackTrace(ex);
        }
    }

}
