/*
* AbstractCodeGenerator.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.jsfutils project which is a sub project of temtools 
* (http://temtools.sf.net).
* 
*/
package de.te2m.tools.netbeans.vertx.code;

import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;

import org.openide.util.Exceptions;

/**
 * The Class AbstractCodeGenerator.
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
     * This will be invoked when user chooses this Generator from Insert Code
     * dialog.
     */
    public void invoke() {
        try {
            Caret caret = textComp.getCaret();
            int dot = caret.getDot();
            textComp.getDocument().insertString(dot, getContent(), null);
        } catch (BadLocationException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    /**
     * Gets the content.
     *
     * @return the content
     */
    protected abstract String getContent();

}
