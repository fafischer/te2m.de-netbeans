/*
* DockerfileVisualPanel1.java
*   
* Copyright 2009 - 2016 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
*/
package de.te2m.tools.netbeans.vertx.wizards.dockerfile;

import javax.swing.JPanel;

/**
 * The Class DockerfileVisualPanel1.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
public final class DockerfileVisualPanel1 extends JPanel {

    /**
     * The description text area.
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea descriptionTextArea;
    
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
    
    /**
     * The j scroll pane1.
     */
    private javax.swing.JScrollPane jScrollPane1;
    
    /**
     * The name text field.
     */
    private javax.swing.JTextField nameTextField;
    
    /**
     * The package text field.
     */
    private javax.swing.JTextField packageTextField;
    // End of variables declaration//GEN-END:variables

    /**
     * Creates new form VerticleVisualPanel1.
     */
    public DockerfileVisualPanel1() {
        initComponents();
    }
    
    /* (non-Javadoc)
     * @see java.awt.Component#getName()
     */
    @Override
    public String getName() {
        return "Step #1";
    }
    
    /**
     * Gets the target description.
     *
     * @return the target description
     */
    public String getTargetDescription() {
        return descriptionTextArea.getText();
    }
    
    /**
     * Gets the target name.
     *
     * @return the target name
     */
    public String getTargetName() {
        return nameTextField.getText();
    }
    
    /**
     * Gets the target folder.
     *
     * @return the target folder
     */
    public String getTargetPackage() {
        return packageTextField.getText();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        packageTextField = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(DockerfileVisualPanel1.class, "DockerfileVisualPanel1.jLabel1.text")); // NOI18N

        nameTextField.setText(org.openide.util.NbBundle.getMessage(DockerfileVisualPanel1.class, "DockerfileVisualPanel1.nameTextField.text")); // NOI18N
        nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextFieldActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(DockerfileVisualPanel1.class, "DockerfileVisualPanel1.jLabel2.text")); // NOI18N

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setRows(5);
        jScrollPane1.setViewportView(descriptionTextArea);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(DockerfileVisualPanel1.class, "DockerfileVisualPanel1.jLabel3.text")); // NOI18N

        packageTextField.setText(org.openide.util.NbBundle.getMessage(DockerfileVisualPanel1.class, "DockerfileVisualPanel1.packageTextField.text")); // NOI18N
        packageTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packageTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                    .addComponent(packageTextField)
                    .addComponent(nameTextField))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(packageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(127, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * Package text field action performed.
     *
     * @param evt the evt
     */
    private void packageTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_packageTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_packageTextFieldActionPerformed

    /**
     * Name text field action performed.
     *
     * @param evt the evt
     */
    private void nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextFieldActionPerformed
}
