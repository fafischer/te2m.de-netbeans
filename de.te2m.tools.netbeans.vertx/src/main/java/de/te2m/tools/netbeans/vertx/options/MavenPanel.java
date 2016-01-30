/*
* MavenPanel.java
*   
* Copyright 2009 - 2016 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
*/
package de.te2m.tools.netbeans.vertx.options;

import static de.te2m.tools.netbeans.vertx.wizards.TemplateKeys.MVN_ARTIFACT_ID;
import static de.te2m.tools.netbeans.vertx.wizards.TemplateKeys.MVN_GROUP_ID;
import static de.te2m.tools.netbeans.vertx.wizards.TemplateKeys.MVN_VERSION;
import static org.openide.util.NbPreferences.forModule;

/**
 * The Class MavenPanel.
 * Option panel used for handling Maven defaults.
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
    /**
     * The artifact id text field.
     */
    private javax.swing.JTextField artifactIDTextField;
    
    /**
     * The default version text.
     */
    private javax.swing.JTextField defaultVersionText;
    
    /**
     * The group id text field.
     */
    private javax.swing.JTextField groupIDTextField;
    
    /**
     * The j label1.
     */
    private javax.swing.JLabel jLabel1;
    
    /**
     * The j label2.
     */
    private javax.swing.JLabel jLabel2;
    
    /**
     * The j label3.
     */
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
        jLabel1 = new javax.swing.JLabel();
        groupIDTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        artifactIDTextField = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(MavenPanel.class, "MavenPanel.jLabel3.text")); // NOI18N

        defaultVersionText.setText(org.openide.util.NbBundle.getMessage(MavenPanel.class, "MavenPanel.defaultVersionText.text")); // NOI18N
        defaultVersionText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defaultVersionTextActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(MavenPanel.class, "MavenPanel.jLabel1.text")); // NOI18N

        groupIDTextField.setText(org.openide.util.NbBundle.getMessage(MavenPanel.class, "MavenPanel.groupIDTextField.text")); // NOI18N
        groupIDTextField.setToolTipText(org.openide.util.NbBundle.getMessage(MavenPanel.class, "MavenPanel.groupIDTextField.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(MavenPanel.class, "MavenPanel.jLabel2.text")); // NOI18N

        artifactIDTextField.setText(org.openide.util.NbBundle.getMessage(MavenPanel.class, "MavenPanel.artifactIDTextField.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(19, 19, 19)
                        .addComponent(groupIDTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(artifactIDTextField)
                            .addComponent(defaultVersionText))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(groupIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(artifactIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(defaultVersionText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(180, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Load the option values.
     * If no value is already available then default values will be used (if available)
     */
    void load() {
        defaultVersionText.setText(forModule(MavenPanel.class).get(MVN_VERSION, "1.0-SNAPSHOT"));
        artifactIDTextField.setText(forModule(MavenPanel.class).get(MVN_ARTIFACT_ID, ""));
        groupIDTextField.setText(forModule(MavenPanel.class).get(MVN_GROUP_ID, ""));
    }

    /**
     * Store the option values.
     */
    void store() {
        forModule(MavenPanel.class).put(MVN_VERSION, defaultVersionText.getText());
        forModule(MavenPanel.class).put(MVN_ARTIFACT_ID, artifactIDTextField.getText());
        forModule(MavenPanel.class).put(MVN_GROUP_ID, groupIDTextField.getText());
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
