/*
* VertxMavenPanelVisual.java
*   
* Copyright 2009 - 2016 Frank Fischer (email: frank@te2m.de)
*
* This file is part of the de.te2m.tools.netbeans.vertx project which is a sub project of the te2m.de Netbeans modules 
* (https://github.com/fafischer/te2m.de-netbeans).
* 
*/
package de.te2m.tools.netbeans.vertx.wizards.project;

import de.te2m.tools.netbeans.vertx.options.MavenPanel;
import de.te2m.tools.netbeans.vertx.wizards.TemplateKeys;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.openide.WizardDescriptor;
import org.openide.WizardValidationException;
import org.openide.util.NbPreferences;

/**
 * The Class VertxPanelVisual.
 *
 * @author ffischer
 * @version 1.0
 * @since 1.0
 */
public class VertxMavenPanelVisual extends JPanel implements DocumentListener {

    /**
     * The Constant PROP_PROJECT_NAME.
     */
    public static final String PROP_PROJECT_NAME = "projectName";

    /**
     * The panel.
     */
    private VertxWizardMavenPanel panel;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * The artifact id text field.
     */
    private javax.swing.JTextField artifactIDTextField;
    
    /**
     * The created folder label.
     */
    private javax.swing.JLabel createdFolderLabel;
    
    /**
     * The group id text field.
     */
    private javax.swing.JTextField groupIDTextField;
    
    /**
     * The project location label.
     */
    private javax.swing.JLabel projectLocationLabel;
    
    /**
     * The project name label.
     */
    private javax.swing.JLabel projectNameLabel;
    
    /**
     * The version text field.
     */
    private javax.swing.JTextField versionTextField;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Instantiates a new vertx panel visual.
     *
     * @param panel the panel
     */
    public VertxMavenPanelVisual(VertxWizardMavenPanel panel) {
        initComponents();
        this.panel = panel;
        // Register listener on the textFields to make the automatic updates
        groupIDTextField.getDocument().addDocumentListener(this);
        artifactIDTextField.getDocument().addDocumentListener(this);
    }
    
    /* (non-Javadoc)
     * @see javax.swing.JComponent#addNotify()
     */
    @Override
    public void addNotify() {
        super.addNotify();
        //same problem as in 31086, initial focus on Cancel button
        groupIDTextField.requestFocus();
    }
    

    /**
     * Artifact id text field action performed.
     *
     * @param evt the evt
     */
    private void artifactIDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_artifactIDTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_artifactIDTextFieldActionPerformed

    /* (non-Javadoc)
     * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent)
     */
    @Override
    public void changedUpdate(DocumentEvent e) {
        panel.fireChangeEvent();
    }

    /**
     * Gets the project name.
     *
     * @return the project name
     */
    public String getProjectName() {
        return this.groupIDTextField.getText();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        projectNameLabel = new javax.swing.JLabel();
        groupIDTextField = new javax.swing.JTextField();
        projectLocationLabel = new javax.swing.JLabel();
        artifactIDTextField = new javax.swing.JTextField();
        createdFolderLabel = new javax.swing.JLabel();
        versionTextField = new javax.swing.JTextField();

        projectNameLabel.setLabelFor(groupIDTextField);
        org.openide.awt.Mnemonics.setLocalizedText(projectNameLabel, org.openide.util.NbBundle.getMessage(VertxMavenPanelVisual.class, "VertxMavenPanelVisual.projectNameLabel.text")); // NOI18N

        projectLocationLabel.setLabelFor(artifactIDTextField);
        org.openide.awt.Mnemonics.setLocalizedText(projectLocationLabel, org.openide.util.NbBundle.getMessage(VertxMavenPanelVisual.class, "VertxMavenPanelVisual.projectLocationLabel.text")); // NOI18N

        artifactIDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                artifactIDTextFieldActionPerformed(evt);
            }
        });

        createdFolderLabel.setLabelFor(versionTextField);
        org.openide.awt.Mnemonics.setLocalizedText(createdFolderLabel, org.openide.util.NbBundle.getMessage(VertxMavenPanelVisual.class, "VertxMavenPanelVisual.createdFolderLabel.text")); // NOI18N

        versionTextField.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(projectNameLabel)
                    .addComponent(projectLocationLabel)
                    .addComponent(createdFolderLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(artifactIDTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                    .addComponent(groupIDTextField)
                    .addComponent(versionTextField))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(projectNameLabel)
                    .addComponent(groupIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(projectLocationLabel)
                    .addComponent(artifactIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createdFolderLabel)
                    .addComponent(versionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(216, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    /* (non-Javadoc)
     * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent)
     */
    @Override
    public void insertUpdate(DocumentEvent e) {
        panel.fireChangeEvent();
    }


    /**
     * Read.
     *
     * @param settings the settings
     */
    void read(WizardDescriptor settings) {

        String groupID = (String) settings.getProperty(TemplateKeys.DN_MVN_GROUP_ID);
        if(null==groupID||groupID.trim().length()==0)
        {
            groupID = NbPreferences.forModule(MavenPanel.class).get(TemplateKeys.DN_MVN_GROUP_ID, "");
        }
        
        this.groupIDTextField.setText(groupID);
        
        String artifactID = (String) settings.getProperty(TemplateKeys.DN_MVN_ARTIFACT_ID);
        if(null==artifactID||artifactID.trim().length()==0)
        {
            artifactID = NbPreferences.forModule(MavenPanel.class).get(TemplateKeys.DN_MVN_ARTIFACT_ID, "");
        }
        this.artifactIDTextField.setText(artifactID);
        
        String version = (String) settings.getProperty(TemplateKeys.DN_VERTX_VERSION);
        
        if(null==version||version.trim().length()==0)
        {
            version = NbPreferences.forModule(MavenPanel.class).get(TemplateKeys.DN_VERTX_VERSION, "1.0-SNAPSHOT");
        }
        
        this.versionTextField.setText(version);
    }

    /* (non-Javadoc)
     * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
     */
    @Override
    public void removeUpdate(DocumentEvent e) {
        panel.fireChangeEvent();
    }

    /**
     * Store.
     *
     * @param d the d
     */
    void store(WizardDescriptor d) {
        String groupID = groupIDTextField.getText().trim();
        String artifactID = artifactIDTextField.getText().trim();
        String version = versionTextField.getText().trim();


        d.putProperty(TemplateKeys.DN_MVN_ARTIFACT_ID,artifactID);
        d.putProperty(TemplateKeys.DN_MVN_GROUP_ID,groupID);
        d.putProperty(TemplateKeys.DN_VERTX_VERSION,version);
    }

    /**
     * Valid.
     *
     * @param wizardDescriptor the wizard descriptor
     * @return true, if successful
     */
    boolean valid(WizardDescriptor wizardDescriptor) {

        if (groupIDTextField.getText().length() == 0) {
            // TODO if using org.openide.dialogs >= 7.8, can use WizardDescriptor.PROP_ERROR_MESSAGE:
            wizardDescriptor.putProperty("WizardPanel_errorMessage",
                    "Group ID must not be empty.");
            return false; // Display name not specified
        }
        if (artifactIDTextField.getText().length() == 0) {
            // TODO if using org.openide.dialogs >= 7.8, can use WizardDescriptor.PROP_ERROR_MESSAGE:
            wizardDescriptor.putProperty("WizardPanel_errorMessage",
                    "Artifact ID must not be empty.");
            return false; // Display name not specified
        }
        if (versionTextField.getText().length() == 0) {
            // TODO if using org.openide.dialogs >= 7.8, can use WizardDescriptor.PROP_ERROR_MESSAGE:
            wizardDescriptor.putProperty("WizardPanel_errorMessage",
                    "Version must not be empty.");
            return false; // Display name not specified
        }

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
