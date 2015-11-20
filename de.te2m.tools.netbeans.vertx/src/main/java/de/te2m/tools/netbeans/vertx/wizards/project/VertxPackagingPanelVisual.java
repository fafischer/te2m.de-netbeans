/*
* VertxPackagingPanelVisual.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
 */
package de.te2m.tools.netbeans.vertx.wizards.project;

import de.te2m.tools.netbeans.vertx.wizards.TemplateKeys;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import org.openide.WizardDescriptor;
import org.openide.WizardValidationException;

/**
 * The Class VertxPanelVisual.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
public class VertxPackagingPanelVisual extends JPanel implements DocumentListener {

    /**
     * The Constant PROP_PROJECT_NAME.
     */
    public static final String PROP_PROJECT_NAME = "projectName";

    /**
     * The panel.
     */
    private VertxWizardPackagingPanel panel;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * The create fat jar check box.
     */
    private javax.swing.JCheckBox createFatJarCheckBox;
    // End of variables declaration//GEN-END:variables

    /**
     * Instantiates a new vertx panel visual.
     *
     * @param panel the panel
     */
    public VertxPackagingPanelVisual(VertxWizardPackagingPanel panel) {
        initComponents();
        this.panel = panel;
        // Register listener on the textFields to make the automatic updates
        //projectNameTextField.getDocument().addDocumentListener(this);
        //projectLocationTextField.getDocument().addDocumentListener(this);
    }

    /* (non-Javadoc)
     * @see javax.swing.JComponent#addNotify()
     */
    @Override
    public void addNotify() {
        super.addNotify();
        //same problem as in 31086, initial focus on Cancel button
        //projectNameTextField.requestFocus();
    }

    // Implementation of DocumentListener --------------------------------------
    /* (non-Javadoc)
     * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent)
     */
    public void changedUpdate(DocumentEvent e) {
        updateTexts(e);
        
    }

    /**
     * J check box1 action performed.
     *
     * @param evt the evt
     */
    private void createFatJarCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createFatJarCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_createFatJarCheckBoxActionPerformed

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        createFatJarCheckBox = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(createFatJarCheckBox, org.openide.util.NbBundle.getMessage(VertxPackagingPanelVisual.class, "VertxPackagingPanelVisual.createFatJarCheckBox.text")); // NOI18N
        createFatJarCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createFatJarCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(createFatJarCheckBox)
                .addContainerGap(527, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(createFatJarCheckBox)
                .addContainerGap(252, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /* (non-Javadoc)
     * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent)
     */
    public void insertUpdate(DocumentEvent e) {
        updateTexts(e);
    }

    /**
     * Read.
     *
     * @param settings the settings
     */
    void read(WizardDescriptor settings) {
        Boolean value = (Boolean) settings.getProperty(TemplateKeys.PKG_CREATE_FAT_JAR);
        if (value == null) {
            value = Boolean.FALSE;
        }
        
        createFatJarCheckBox.setSelected(value);
    }

    /* (non-Javadoc)
     * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
     */
    public void removeUpdate(DocumentEvent e) {
        updateTexts(e);
    }

    /**
     * Store.
     *
     * @param d the d
     */
    void store(WizardDescriptor d) {
        Boolean value = createFatJarCheckBox.isSelected();
        
        d.putProperty(TemplateKeys.PKG_CREATE_FAT_JAR, value);
    }

    /**
     * Handles changes in the Project name and project directory,.
     *
     * @param e the e
     */
    private void updateTexts(DocumentEvent e) {
        
        Document doc = e.getDocument();
        
        panel.fireChangeEvent(); // Notify that the panel changed
    }

    /**
     * Valid.
     *
     * @param wizardDescriptor the wizard descriptor
     * @return true, if successful
     */
    boolean valid(WizardDescriptor wizardDescriptor) {
        
        wizardDescriptor.putProperty("WizardPanel_errorMessage", "");
        return true;
    }

    /**
     * Validate.
     *
     * @param d the d
     * @throws WizardValidationException the wizard validation exception
     */
    void validate(WizardDescriptor d) throws WizardValidationException {
        // nothing to validate
    }
    
}
