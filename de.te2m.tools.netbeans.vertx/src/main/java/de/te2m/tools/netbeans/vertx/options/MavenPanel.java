/*
* CommonPanel.java
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
 * The Class CommonPanel.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
public final class MavenPanel extends javax.swing.JPanel {

    /**
     * The controller.
     */
    private final MavenPanelController controller;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField defaultVersionText;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

    /**
     * Instantiates a new common panel.
     *
     * @param controller the controller
     */
    MavenPanel(MavenPanelController controller) {
        this.controller = controller;
        initComponents();
        // TODO listen to changes in form fields and call controller.changed()
    }

    /**
     * Default version text action performed.
     *
     * @param evt the evt
     */
    private void defaultVersionTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_defaultVersionTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_defaultVersionTextActionPerformed

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        defaultVersionText = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(MavenPanel.class, "MavenPanel.jLabel3.text")); // NOI18N

        defaultVersionText.setText(org.openide.util.NbBundle.getMessage(MavenPanel.class, "MavenPanel.defaultVersionText.text")); // NOI18N
        defaultVersionText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defaultVersionTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(defaultVersionText, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(defaultVersionText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(238, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Load.
     */
    void load() {
        defaultVersionText.setText(NbPreferences.forModule(MavenPanel.class).get(TemplateKeys.PROPERTY_VERSION, "1.0-SNAPSHOT"));
    }

    /**
     * Store.
     */
    void store() {
        NbPreferences.forModule(MavenPanel.class).put(TemplateKeys.PROPERTY_VERSION, defaultVersionText.getText());
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
