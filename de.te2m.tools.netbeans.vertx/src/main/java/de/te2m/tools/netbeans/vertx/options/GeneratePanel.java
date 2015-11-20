/*
* GeneratePanel.java
*   
* Copyright 2009 - 2015 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
*/
package de.te2m.tools.netbeans.vertx.options;

import de.te2m.tools.netbeans.vertx.wizards.TemplateKeys;
import org.openide.util.NbPreferences;

/**
 * The Class GeneratePanel.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
public final class GeneratePanel extends javax.swing.JPanel {

    /**
     * The controller.
     */
    private final GenerateOptionsPanelController controller;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * The include company info check box.
     */
    private javax.swing.JCheckBox includeCompanyInfoCheckBox;

    /**
     * The include user check box.
     */
    private javax.swing.JCheckBox includeUserCheckBox;
    // End of variables declaration//GEN-END:variables

    /**
     * Instantiates a new generate panel.
     *
     * @param controller the controller
     */
    GeneratePanel(GenerateOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        // TODO listen to changes in form fields and call controller.changed()
    }

    /**
     * Include user check box action performed.
     *
     * @param evt the evt
     */
    private void includeUserCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_includeUserCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_includeUserCheckBoxActionPerformed

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        includeUserCheckBox = new javax.swing.JCheckBox();
        includeCompanyInfoCheckBox = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(includeUserCheckBox, org.openide.util.NbBundle.getMessage(GeneratePanel.class, "GeneratePanel.includeUserCheckBox.text")); // NOI18N
        includeUserCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                includeUserCheckBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(includeCompanyInfoCheckBox, org.openide.util.NbBundle.getMessage(GeneratePanel.class, "GeneratePanel.includeCompanyInfoCheckBox.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(includeUserCheckBox)
                    .addComponent(includeCompanyInfoCheckBox))
                .addContainerGap(346, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(includeUserCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(includeCompanyInfoCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Load.
     */
    void load() {
        includeUserCheckBox.setSelected(NbPreferences.forModule(GeneratePanel.class).getBoolean(TemplateKeys.PROPERTY_INCLUDE_COMPANY, true));
        includeCompanyInfoCheckBox.setSelected(NbPreferences.forModule(GeneratePanel.class).getBoolean(TemplateKeys.PROPERTY_INCLUDE_USER, true));
    }

    /**
     * Store.
     */
    void store() {
        NbPreferences.forModule(GeneratePanel.class).putBoolean(TemplateKeys.PROPERTY_INCLUDE_COMPANY, includeCompanyInfoCheckBox.isSelected());
        NbPreferences.forModule(GeneratePanel.class).putBoolean(TemplateKeys.PROPERTY_INCLUDE_USER, includeUserCheckBox.isSelected());
    }
    
    /**
     * Valid.
     *
     * @return true, if successful
     */
    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }
}
