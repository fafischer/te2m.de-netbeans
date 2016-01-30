/*
* VertxPanel.java
*   
* Copyright 2009 - 2016 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
*/
package de.te2m.tools.netbeans.vertx.options;

import de.te2m.tools.netbeans.vertx.wizards.TemplateKeys;
import static de.te2m.tools.netbeans.vertx.wizards.TemplateKeys.VERTX_USE_FAT_JAR_DEFAULT;
import static de.te2m.tools.netbeans.vertx.wizards.TemplateKeys.VERTX_VERSION;
import static org.openide.util.NbPreferences.forModule;

/**
 * The Class MavenPanel.
 * Option panel used for handling Maven defaults.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
public final class VertxPanel extends javax.swing.JPanel {

    /**
     * The controller.
     */
    private final VertxPanelController controller;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField defaultVersionText;
    private javax.swing.JCheckBox dockerfileDefaultCheckBox;
    private javax.swing.JCheckBox fatJarCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField dockerImageNameTextField;
    // End of variables declaration//GEN-END:variables
    /**
     * Instantiates a new common panel.
     *
     * @param controller the controller
     */
    VertxPanel(VertxPanelController controller) {
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
        fatJarCheckBox = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dockerfileDefaultCheckBox = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        dockerImageNameTextField = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(VertxPanel.class, "VertxPanel.jLabel3.text")); // NOI18N

        defaultVersionText.setText(org.openide.util.NbBundle.getMessage(VertxPanel.class, "VertxPanel.defaultVersionText.text")); // NOI18N
        defaultVersionText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                defaultVersionTextActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(fatJarCheckBox, org.openide.util.NbBundle.getMessage(VertxPanel.class, "VertxPanel.fatJarCheckBox.text")); // NOI18N
        fatJarCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fatJarCheckBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(VertxPanel.class, "VertxPanel.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(VertxPanel.class, "VertxPanel.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(dockerfileDefaultCheckBox, org.openide.util.NbBundle.getMessage(VertxPanel.class, "VertxPanel.dockerfileDefaultCheckBox.text")); // NOI18N
        dockerfileDefaultCheckBox.setToolTipText(org.openide.util.NbBundle.getMessage(VertxPanel.class, "VertxPanel.dockerfileDefaultCheckBox.toolTipText")); // NOI18N
        dockerfileDefaultCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dockerfileDefaultCheckBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(VertxPanel.class, "VertxPanel.jLabel4.text")); // NOI18N

        dockerImageNameTextField.setText(org.openide.util.NbBundle.getMessage(VertxPanel.class, "VertxPanel.dockerImageNameTextField.text")); // NOI18N
        dockerImageNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dockerImageNameTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dockerImageNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                    .addComponent(defaultVersionText)
                    .addComponent(fatJarCheckBox)
                    .addComponent(dockerfileDefaultCheckBox))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(defaultVersionText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fatJarCheckBox)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(dockerfileDefaultCheckBox))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dockerImageNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(126, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Fat jar check box action performed.
     *
     * @param evt the evt
     */
    private void fatJarCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fatJarCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fatJarCheckBoxActionPerformed

    private void dockerfileDefaultCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dockerfileDefaultCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dockerfileDefaultCheckBoxActionPerformed

    private void dockerImageNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dockerImageNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dockerImageNameTextFieldActionPerformed

    /**
     * Load the option values.
     * If no value is already available then default values will be used (if available)
     */
    void load() {
        defaultVersionText.setText(forModule(VertxPanel.class).get(VERTX_VERSION, "3.1.0"));
        fatJarCheckBox.setSelected(forModule(VertxPanel.class).getBoolean(VERTX_USE_FAT_JAR_DEFAULT, true));
        dockerfileDefaultCheckBox.setSelected(forModule(VertxPanel.class).getBoolean(TemplateKeys.VERTX_GENERATE_DOCKER_DEFAULT, true));
        dockerImageNameTextField.setText(forModule(VertxPanel.class).get(TemplateKeys.VERTX_DOCKER_DEFAULT_IMAGE_NAME,""));
    }

    /**
     * Store the option values.
     */
    void store() {
        forModule(VertxPanel.class).put(VERTX_VERSION, defaultVersionText.getText());
        forModule(VertxPanel.class).putBoolean(VERTX_USE_FAT_JAR_DEFAULT, fatJarCheckBox.isSelected());
        forModule(VertxPanel.class).putBoolean(TemplateKeys.VERTX_GENERATE_DOCKER_DEFAULT, dockerfileDefaultCheckBox.isSelected());
        forModule(VertxPanel.class).put(TemplateKeys.VERTX_DOCKER_DEFAULT_IMAGE_NAME, dockerImageNameTextField.getText());
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
